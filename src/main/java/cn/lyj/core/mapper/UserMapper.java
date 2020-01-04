package cn.lyj.core.mapper;

import cn.lyj.core.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> findList(@Param("map") Map<String,Object> map);
    List<User> findMyFrieds(@Param("meId") String meId);
}
