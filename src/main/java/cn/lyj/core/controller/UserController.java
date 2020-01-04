package cn.lyj.core.controller;


import cn.lyj.common.exception.MyException;
import cn.lyj.common.utils.HttpHeaderUtil;
import cn.lyj.common.utils.StringUtils;
import cn.lyj.common.web.OrderBy;
import cn.lyj.core.entity.UserFriend;
import cn.lyj.core.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lyj.common.web.R;
import cn.lyj.common.web.BaseController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import cn.lyj.core.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User>
{

    @Autowired
    UserService thisService;

    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> param)
    {
        return success(thisService.findList(param));
    }

    @GetMapping("/myFrieds")
    public R myFrieds(@RequestParam("meId") String meId)
    {
        return success(thisService.getMyFrieds(meId));
    }

    @PostMapping("/addFreind")
    public R addFreind(@RequestParam("meId") String meId, @RequestParam("friendId") String friendId)
    {
        if (meId.equals(friendId))
        {
            throw new MyException("不能添加自己为好友");
        }
        UserFriend userFriend = new UserFriend().selectOne(new QueryWrapper<UserFriend>().lambda().eq(UserFriend::getMeId, meId).eq(UserFriend::getFriendId, friendId));
        if (StringUtils.isNull(userFriend))
        {
            new UserFriend(friendId, meId).insert();
            return result(new UserFriend(meId, friendId).insert());
        }
        throw new MyException("你们已经是好友了");
    }

    @PostMapping("/save")
    public R insertSave(@Valid User entity)
    {
        User user = thisService.getOne(new QueryWrapper<User>().lambda().eq(User::getName, entity.getName()).select(User::getId, User::getOnline));
        if (StringUtils.isNotNull(user))
        {
            if (user.getOnline() == 1)
            {
                throw new MyException("当前账户被使用了");
            }
            return success(entity.getId());
        }
        String ip = HttpHeaderUtil.getIp();
        entity.setIp(ip);
        entity.setAddress(HttpHeaderUtil.getIpAddress());
        entity.setOnline(1);
        thisService.save(entity);
        return success(entity.getId());
    }

    @PutMapping("/update")
    public R editSave(User entity)
    {
        return result(thisService.updateById(entity));
    }

    @GetMapping("/selectById/{id}")
    public R selectById(@PathVariable("id") String id)
    {
        User user = thisService.getById(id);
        if (StringUtils.isNull(user))
        {
            throw new MyException("用户不存在");
        }
        return success(user);
    }

    @DeleteMapping("/{ids}/remove")
    public R delete(@PathVariable("ids") List<Integer> ids)
    {
        return result(thisService.removeByIds(ids));
    }
}

