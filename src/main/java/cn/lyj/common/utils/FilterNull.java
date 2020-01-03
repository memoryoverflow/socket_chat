package cn.lyj.common.utils;

import cn.lyj.common.exception.Error;
import cn.lyj.common.exception.MyException;
import java.util.Collection;

/**
 * <br>
 * 校验非空 并且跑出异常
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public class FilterNull
{
    /**
     * @param obj
     * @param tip
     */
    public static void isNull(final Object obj, String tip)
    {
        if (obj == null)
        {
            throw new MyException(Error.参数为空异常, tip);
        }
    }

    public static void isNull(final Object obj, Error error, String tip)
    {
        if (obj == null)
        {
            throw new MyException(error, tip);
        }
    }

    public static void isNotNull(final Object obj, Error error, String tip)
    {
        if (obj != null)
        {
            throw new MyException(error, tip);
        }
    }

    public static void isNull(final Object obj, Error error)
    {
        isNull(obj, error, "");
    }


    public static void isBlank(final CharSequence cs, String tip)
    {
        isNull(cs, tip);
        if (cs.length() == 0)
        {
            throw new MyException(Error.参数为空异常, tip);
        }
    }


    public static void isBlank(final CharSequence cs, Error error, String tip)
    {
        isNull(cs, error, tip);
        if (cs.length() == 0)
        {
            throw new MyException(error, tip);
        }
    }

    public static void isBlank(final CharSequence cs, Error error)
    {
        isNull(cs, error);
        if (cs.length() == 0)
        {
            throw new MyException(error);
        }
    }

    public static void isBlank(String tip, CharSequence... cs)
    {
        for (CharSequence sequence : cs)
        {
            isBlank(sequence, tip);
        }
    }

    public static void isEmpty(Collection coll, String tip)
    {
        isNull(coll, tip);
        if (coll.isEmpty())
        {
            throw new MyException(Error.参数为空异常, tip);
        }
    }


    public static void main(String[] args)
    {
        isBlank("参数为空", "渣渣们花果山", " ", "");
    }


}
