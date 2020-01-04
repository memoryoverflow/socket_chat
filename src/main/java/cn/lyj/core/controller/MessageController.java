package cn.lyj.core.controller;


import cn.lyj.common.web.BaseController;
import cn.lyj.common.web.OrderBy;
import cn.lyj.common.web.R;
import cn.lyj.core.entity.Message;
import cn.lyj.core.entity.MessageDto;
import cn.lyj.core.service.MessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/message")
public class MessageController extends BaseController<Message>
{

    @Autowired
    MessageService thisService;


    @PostMapping("/list")
    public R list(@RequestParam("meId") String meId, @RequestParam("talkUserId") String talkUserId)
    {
        IPage<Message> iPage = thisService.listHis(meId, talkUserId, page(new OrderBy(OrderBy.Direction.DESC, "create_time")));
        List<Message> records = iPage.getRecords();
        List<Message> messages = new ArrayList<>();
        for (int i = records.size()-1; i >=0 ; i--)
        {
            messages.add(records.get(i));
        }
        iPage.setRecords(messages);
        return success(iPage);
    }


    @PostMapping("/save")
    public R insertSave(Message entity)
    {
        return result(thisService.save(entity));
    }


    @PutMapping("/update")
    public R editSave(Message entity)
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

