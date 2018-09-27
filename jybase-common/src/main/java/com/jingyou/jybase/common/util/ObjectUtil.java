package com.jingyou.jybase.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
public class ObjectUtil {

    private static final String EMPTY_STR = "";
    /**
     * 判断指定的对象是否为null。
     *
     * @param obj 要判断的对象实例
     * @return true：为null false：非null
     */
    public static boolean isNull(Object obj)
    {
        return obj == null;
    }

    /**
     * 判断指定的对象是否不为null。
     *
     * @param obj 要判断的对象实例
     * @return true：非null false：为null
     */
    public static boolean isNotNull(Object obj)
    {
        return !isNull(obj);
    }

    /**
     * 判断指定的对象数组是否为空。
     *
     * @param objs 要判断的对象数组实例
     * @return true：对象数组为空 false：对象数组非空
     */
    public static boolean isEmpty(Object[] objs)
    {
        return isNull(objs) || objs.length == 0;
    }

    /**
     * 判断指定的对象数组是否非空。
     *
     * @param objs 要判断的对象数组实例
     * @return true：对象数组非空 false：对象数组为空
     */
    public static boolean isNotEmpty(Object[] objs)
    {
        return !isEmpty(objs);
    }

    /**
     * 判断指定的集合类是否为空。<BR>
     * 为空的含义是指：该集合为null，或者该集合不包含任何元素。
     *
     * @param coll 要判断的集合实例
     * @return true：集合为空 false：集合非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断指定的集合类是否非空。 <BR>
     * 非空的含义是指：该集合非null并且该集合包含元素。
     *
     * @param coll 要判断的集合实例
     * @return true：集合非空 false：集合为空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * 判断指定的Map类是否为空。 <BR>
     * 为空的含义是指：该Map为null，或者该Map不包含任何元素。
     *
     * @param map 要判断的Map实例
     * @return true：Map为空 false：Map非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断指定的Map类是否非空。<BR>
     * 非空的含义是指：该Map非null并且该Map包含元素。
     *
     * @param map 要判断的Map实例
     * @return true：Map非空 false：Map为空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * 判断指定的CharSequence类是否为空。 <BR>
     * 为空的含义是指：该CharSequence为null，或者该CharSequence长度为0。<BR>
     * 对String类型，虽然也属于CharSequence，<BR>
     * 但因为通常字符串都有特殊的处理要求，所以使用方法isEmpty(String str);
     *
     * @param charSeq 要判断的CharSequence实例
     * @return true：CharSequence为空 false：CharSequence非空
     */
    public static boolean isEmpty(CharSequence charSeq)
    {
        return isNull(charSeq) || charSeq.length() == 0;
    }

    /**
     * 判断指定的CharSequence类是否非空。 <BR>
     * 非空的含义是指：该CharSequence非null并且该CharSequence包含元素。<BR>
     * 对String类型，虽然也属于CharSequence， <BR>
     * 但因为通常字符串都有特殊的处理要求，所以使用方法isNotEmpty(String str);
     *
     * @param charSeq 要判断的CharSequence实例
     * @return true：CharSequence非空 false：CharSequence为空
     */
    public static boolean isNotEmpty(CharSequence charSeq)
    {
        return !isEmpty(charSeq);
    }

    /**
     * 判断指定的String类是否为空。 <BR>
     * 为空的含义是指：该String为null，或者该String为空串""。
     *
     * @param str 要判断的String实例
     * @return true：String为空 false：String非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || EMPTY_STR.equals(str);
    }

    /**
     * 判断指定的String类是否非空。 <BR>
     * 非空的含义是指：该String非null并且该String不为空串。
     *
     * @param str 要判断的String实例
     * @return true：String非空 false：String为空
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
}
