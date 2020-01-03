package cn.lyj.core.service;

import cn.lyj.core.entity.User;
import cn.lyj.core.mapper.MessageMapper;
import cn.lyj.core.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UserService extends ServiceImpl<UserMapper, User>
{

    @Autowired
    MessageMapper messageMapper;

    public Map<Integer, Map<String, Object>> getMyFrieds(Integer meId)
    {
        List<User> myFrieds = baseMapper.findMyFrieds(meId);
        Map<Integer, Map<String, Object>> map = new HashMap<>();
        myFrieds.stream().forEach(user -> {
            Integer count = messageMapper.selectCount(meId, user.getId());
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("count", count);
            hashMap.put("friend", user);
            map.put(user.getId(), hashMap);
        });
        return map;
    }

    public Page<User> findList(Map<String, Object> map, Page<User> page)
    {
        return page.setRecords(baseMapper.findList(map, page));
    }
}
