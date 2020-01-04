package cn.lyj.sockect;

import cn.lyj.common.ConsVal;
import cn.lyj.common.utils.StringUtils;
import cn.lyj.common.web.R;
import cn.lyj.core.entity.RecData;
import cn.lyj.core.entity.ResData;
import cn.lyj.core.entity.User;
import cn.lyj.core.service.MessageService;
import cn.lyj.core.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 11:39
 */
@ServerEndpoint("/chat/{userId}")
@Component
public class SockectServer
{

    private static MessageService messageService;

    private static UserService userService;

    private static ApplicationContext app;

    public static void setApp(ApplicationContext app)
    {
        SockectServer.app = app;
        messageService = app.getBean(MessageService.class);
        userService = app.getBean(UserService.class);
    }

    private static Logger logger = LoggerFactory.getLogger(SockectServer.class);

    /**
     * <br>
     * 线程安全
     */
    private static ConcurrentHashMap<String, SockectServer> WEBSOCKECT_MAP = new ConcurrentHashMap<>();


    /**
     * <br>
     * 在线用户
     */
    private static volatile int onlineCount = 0;

    /**
     * <br>
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * <br>
     * 当前的用户凭证
     */
    private String userId = "";


    /**
     * 用户连接
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId)
    {
        this.session = session;
        //加入set中
        WEBSOCKECT_MAP.put(userId, this);
        //在线数加1
        changeOnlineUser(true);
        logger.info("有新用户登陆:" + userId + ",当前在线人数为" + getOnlineUser());
        this.userId = userId;
        try
        {
            sendMessage(R.success("连接成功"));

            // 用户上线
            userService.updateById(new User().setId(userId).setOnline(1));

            logger.info("连接成功");
            noticeOnineUser(true);
        } catch (IOException | EncodeException e)
        {
            e.printStackTrace();
            logger.error("websocket IO异常");
        }
    }


    /**
     * 通知在线的所有人 有用户登陆
     */
    private void noticeOnineUser(boolean flag)
    {
        Integer code = ConsVal.SOCKECT_RES_REC_USER_LOGOUT;
        if (flag)
        {
            code = ConsVal.SOCKECT_RES_REC_USER_LOGIN;
        }

        for (Map.Entry<String, SockectServer> map : WEBSOCKECT_MAP.entrySet())
        {
            String sId = map.getKey();
            SockectServer sockectServer = map.getValue();
            if (!sId.equals(userId))
            {
                try
                {
                    Map<String, Map<String, Object>> myFrieds = userService.getMyFrieds(sId);
                    List<User> list = userService.findList(new HashMap<>());
                    Map<String, Object> data = new HashMap<>();
                    data.put("myfriends", myFrieds);
                    data.put("users", list);
                    R success = R.success(code, userService.getById(sId).getName());
                    success.setData(data);

                    sockectServer.sendMessage(sockectServer.session, success);
                } catch (IOException e)
                {
                    e.printStackTrace();
                } catch (EncodeException e)
                {
                    e.printStackTrace();
                }
            }
        }
        ;
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error)
    {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException
    {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessage(Object message) throws IOException, EncodeException
    {
        this.sendMessage(JSON.toJSONString(message));
    }

    private void sendMessage(Session session, String message) throws IOException
    {
        session.getBasicRemote().sendText(message);
    }

    private void sendMessage(Session session, Object message) throws IOException, EncodeException
    {
        sendMessage(session, JSON.toJSONString(message));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        logger.info("收到来自用户 " + userId + " 的信息:" + message);
        try
        {
            RecData recData = recMsg(message);
            String toUserId = recData.getToUserId();

            SockectServer server = WEBSOCKECT_MAP.get(String.valueOf(toUserId));
            boolean saveMsg = messageService.saveMsg(userId, toUserId, recData.getContent());

            // 用户不在线
            if (server != null)
            {
                ResData resData = new ResData(userId, recData.getContent());
                if (saveMsg)
                {
                    // 给那个人推送消息
                    Map<String, Map<String, Object>> myFrieds = userService.getMyFrieds(toUserId);
                    resData.setData(JSON.toJSONString(myFrieds));
                    R success = R.success(2, resData);
                    server.sendMessage(server.session, success);
                }
            }


            if (saveMsg)
            {
                // 响应发消息端
                sendMessage(R.success(1, "消息发送成功"));
            }
            else
            {
                sendMessage(R.error("消息发送失败"));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (EncodeException e)
        {
            e.printStackTrace();
        }
    }

    private RecData recMsg(String msg)
    {
        if (StringUtils.isBlank(msg))
        {
            return new RecData();
        }
        return JSON.parseObject(msg, RecData.class);
    }


    /**
     * <br>
     * 连接关闭
     */
    @OnClose
    public void onClose()
    {
        WEBSOCKECT_MAP.remove(this.userId);
        changeOnlineUser(false);
        noticeOnineUser(false);
        userService.updateById(new User().setId(userId).setOnline(0));
        logger.info("有用户退出！当前在线人数为" + getOnlineUser());
    }

    private synchronized void changeOnlineUser(boolean flag)
    {
        if (flag)
        {
            ++onlineCount;
        }
        else
        {
            --onlineCount;
        }
    }

    private static Integer getOnlineUser()
    {
        return onlineCount;
    }

}
