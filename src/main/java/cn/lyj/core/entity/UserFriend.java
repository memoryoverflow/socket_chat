package cn.lyj.core.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value ="tb_user_friend")
public class UserFriend extends Model<UserFriend>
{
    private static final long serialVersionUID = 1L;



    @TableId(type = IdType.AUTO)
    private Integer id;


    private Integer meId;


    private Integer friendId;

    public UserFriend(Integer meId, Integer friendId)
    {
        this.meId = meId;
        this.friendId = friendId;
    }
}