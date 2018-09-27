package com.jingyou.jybase.common.util;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class StringUtil {
    /**
     * 功能描述：是否为空白
     * 不包括空格
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.length()==0;
    }

    /**
     * 功能描述：是否为空白,包括null和空格
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String toString(String[] ids){
        if(ObjectUtil.isNull(ids))
            return null;
        if(ids.length == 0)
            return null;
        StringBuffer sb = new StringBuffer();
        for(String id : ids){
            sb.append("'");
            sb.append(id);
            sb.append("',");
        }
        String idstr = sb.toString();
        return idstr.substring(0,idstr.length()-1);
    }
}
