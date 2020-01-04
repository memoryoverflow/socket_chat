package cn.lyj.core.mapper;

import cn.lyj.core.entity.Message;
import cn.lyj.core.entity.MessageDto;
import cn.lyj.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 16:50
 */
public interface MessageMapper extends BaseMapper<Message>
{
    List<Message> listHis(@Param("meId") String meId, @Param("talkToUserId") String talkToUserId, @Param("page") Page<Message> page);

    Integer selectOneByMeAndTalkUserId(@Param("meId") String meId, @Param("talkToUserId") String talkToUserId);

    @Select(value = "select count(id) from tb_message where me_id=#{fromUserId} and talk_user_id=#{meId} and status=0")
    Integer selectCount(@Param("meId") String meId, @Param("fromUserId") String fromUserId);
}
