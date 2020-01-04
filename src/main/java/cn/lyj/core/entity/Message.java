package cn.lyj.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 16:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "tb_message")
public class Message  extends Model<Message> implements Comparable<Message>
{
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String meId;

    private String talkUserId;

    private String msg;

    private Integer type;

    private Integer parentId;

    private Date createTime;

    private Integer status;

    @Override
    public int compareTo(Message o)
    {
        return this.id-o.id;
    }
}
