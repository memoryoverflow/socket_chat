package cn.lyj.common.web;

import cn.lyj.common.utils.StringUtils;
import cn.lyj.common.web.R;

import java.util.HashMap;
import java.util.Map;

import cn.lyj.common.exception.Error;
import cn.lyj.common.web.OrderBy;
import cn.lyj.common.utils.ServletUtils;

import java.lang.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * <p>
 * 分页字段 当前页：current 每页多少：size
 * <p>
 * 不带分页参数 默认 第一页 每页 15条
 * <p>
 * 排序字段：默认根据Id 降序
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public abstract class BaseController<T>
{

    /**
     * 返回成功
     */
    protected R success()
    {
        return R.success();
    }

    /**
     * 返回失败消息
     */
    protected R error()
    {
        return R.error();
    }

    /**
     * 返回成功消息
     */
    protected R successMsg(String message)
    {
        return R.success(message);
    }

    /**
     * 返回成功消息
     */
    protected R success(Object data)
    {
        return R.success(data);
    }

    /**
     * 返回失败消息
     */
    protected R error(String message)
    {
        return R.error(message);
    }

    /**
     * 根据修改搜影响的行数返回结果
     */
    protected R result(boolean flag)
    {
        return flag == true ? success() : error();
    }

    protected R result(boolean flag, String msg)
    {
        return flag == true ? successMsg(msg) : error();
    }

    protected R result(int row)
    {
        return row > 0 ? success() : error();
    }

    protected R result(int row, String msg)
    {
        return row > 0 ? successMsg(msg) : error();
    }

    protected Map<String, Object> getMap()
    {
        return new HashMap<>(16);
    }

    protected Map<String, Object> getMap(String key, Object val)
    {
        Map<String, Object> map = getMap();
        map.put(key, val);
        return map;
    }

    protected Map<Object, Object> getMap(Object key, Object val)
    {
        Map<Object, Object> map = new HashMap<>();
        map.put(key, val);
        return map;
    }

    /**
     * 分页数据 默认使用更新时间降序
     */
    protected Page<T> page(OrderBy orderBy)
    {
        return getPage(orderBy);
    }

    protected Page<T> page(OrderBy orderBy, int current, int size)
    {
        Page<T> page = getPage(orderBy);
        page.setSize(size);
        page.setCurrent(current);
        return page;
    }

    /**
     * 自定义分页数据 默认使用更新时间降序
     */
    protected Page<T> page()
    {
        //  update_time  create_time
        //return getPage(new OrderBy(OrderBy.Direction.DESC, "create_time"));
        return getPage(new OrderBy(OrderBy.Direction.ASC, "id"));
    }

    /**
     * <br>
     * 自定义分页条件
     */
    private Page<T> getPage(OrderBy orderBy)
    {
        Integer size = ServletUtils.getParamInteger("size");

        if (size != null && size == -1)
        {
            size = Integer.MAX_VALUE;
        }

        Integer pageNum = ServletUtils.getParamInteger("current");
        Page<T> page = new Page<>(pageNum == null ? 0 : pageNum, size == null ? 15 : size);
        if (StringUtils.isNotNull(orderBy))
        {
            if (orderBy.getDirection().isAscending())
            {
                page.setAsc(orderBy.getColumns());
            }
            else
            {
                page.setDesc(orderBy.getColumns());
            }
        }
        return page;
    }

    protected Page pageMap(OrderBy orderBy)
    {
        Integer size = ServletUtils.getParamInteger("size");

        if (size != null && size == -1)
        {
            size = Integer.MAX_VALUE;
        }

        Integer pageNum = ServletUtils.getParamInteger("current");
        Page<Map<String, Object>> page = new Page<>(pageNum == null ? 0 : pageNum, size == null ? 15 : size);
        if (orderBy.getDirection().isAscending())
        {
            page.setAsc(orderBy.getColumns());
        }
        else
        {
            page.setDesc(orderBy.getColumns());
        }
        return page;
    }

    protected Page pageObj(OrderBy orderBy,Class zlass)
    {
        Integer size = ServletUtils.getParamInteger("size");

        if (size != null && size == -1)
        {
            size = Integer.MAX_VALUE;
        }

        Integer pageNum = ServletUtils.getParamInteger("current");
        Page<Map<String, Object>> page = new Page<>(pageNum == null ? 0 : pageNum, size == null ? 15 : size);
        if (StringUtils.isNotNull(orderBy))
        {
            if (orderBy.getDirection().isAscending())
            {
                page.setAsc(orderBy.getColumns());
            }
            else
            {
                page.setDesc(orderBy.getColumns());
            }
        }
        return page;
    }

}
