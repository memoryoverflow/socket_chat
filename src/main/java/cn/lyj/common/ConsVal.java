package cn.lyj.common;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 11:39
 */
public interface ConsVal
{
    /**
     * socket 连接成功
     */
    int SOCKECT_RES_CONNECT=0;

    /**
     * socket 消息发送成功
     */
    int SOCKECT_RES_SEND_SUCCESS=1;

    /**
     * socket 消息接收
     */
    int SOCKECT_RES_REC_SUCCESS=2;

    /**
     * 新用户加入
     */
    int SOCKECT_RES_REC_USER_LOGIN=3;

    /**
     * 用户退出
     */
    int SOCKECT_RES_REC_USER_LOGOUT=4;
}
