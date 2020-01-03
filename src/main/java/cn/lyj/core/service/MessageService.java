package cn.lyj.core.service;

import cn.lyj.core.entity.Message;
import cn.lyj.core.entity.MessageDto;
import cn.lyj.core.mapper.MessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@Service
@Transactional
public class MessageService extends ServiceImpl<MessageMapper, Message>
{

    public IPage<Message> listHis(Integer meId, Integer talkUserId, Page<Message> page)
    {
        List<Message> records = baseMapper.listHis(meId,talkUserId,page);
        records.forEach(message -> {
            if (message.getTalkUserId().equals(meId)&&message.getStatus() == 0)
            {
                message.setStatus(1);
                message.updateById();
            }
        });

        return page.setRecords(records);
    }

    public boolean saveMsg(String userId, Integer toUserId, String content)
    {
        return super.save(new Message().setMeId(Integer.parseInt(userId)).setTalkUserId(toUserId).setMsg(content));
    }
}
