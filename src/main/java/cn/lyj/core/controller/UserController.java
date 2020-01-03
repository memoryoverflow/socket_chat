package cn.lyj.core.controller;


import cn.lyj.common.exception.MyException;
import cn.lyj.common.utils.HttpHeaderUtil;
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
        return success(thisService.findList(param, page()));
    }

    @GetMapping("/myFrieds")
    public R myFrieds(@RequestParam("meId") Integer meId)
    {
        return success(thisService.getMyFrieds(meId));
    }

    @PostMapping("/addFreind")
    public R addFreind(@RequestParam("meId") Integer meId, @RequestParam("friendId") Integer friendId)
    {
        return result(new UserFriend(meId, friendId).insert());
    }

    @PostMapping("/save")
    public R insertSave(@Valid User entity)
    {
        String loginKey = entity.getLoginKey();
        int count = thisService.count(new QueryWrapper<User>().lambda().eq(User::getLoginKey, loginKey));
        if (count > 0)
        {
            throw new MyException("登陆名已存在");
        }
        count = thisService.count(new QueryWrapper<User>().lambda().eq(User::getName, entity.getName()));
        if (count > 0)
        {
            throw new MyException("用户名已存在");
        }
        String ip = HttpHeaderUtil.getIp();
        entity.setIp(ip);
        entity.setAddress(HttpHeaderUtil.getIpAddress());
    return result(thisService.save(entity));
    }

    @PutMapping("/update")
    public R editSave(User entity)
    {
        return result(thisService.updateById(entity));
    }

    @GetMapping("/selectById/{id}")
    public R selectById(@PathVariable("id") String id)
    {
        return success(thisService.getById(id));
    }

    @DeleteMapping("/{ids}/remove")
    public R delete(@PathVariable("ids") List<Integer> ids)
    {
        return result(thisService.removeByIds(ids));
    }
}

