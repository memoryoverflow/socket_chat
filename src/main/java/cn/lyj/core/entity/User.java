package cn.lyj.core.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
@TableName(value ="tb_user")
public class User extends Model<User>
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "请填写姓名")
    private String name;

    @NotBlank(message = "请填写密码")
    private String password;

    @NotBlank(message = "请填写登陆账户")
    private String loginKey;


    private String ip;

    private String address;


    private Integer online;


}