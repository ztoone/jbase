package com.jingyou.jybase.common.util;

/**
 * Created by zhongjy on 2016/6/12.
 */
public class NumberUtil {
    /**
     * 把double 类型数 d 保留regx位小数
     * @param num 需要转换的值
     * @param regx 小数位
     * @return
     */
    public static double roundDouble(double num, int regx) {
        double temp = num * Math.pow(10, regx);
        temp = Math.round(temp);
        temp = temp / Math.pow(10, regx);
        return temp;
    }

    /**
     * 计算百分比
     * @param num1 分子
     * @param num2 分母
     * @param regx 保留几位小数
     * @return
     */
    public static double percent(double num1,double num2,int regx){
        if(roundDouble(num2,regx) == 0)
            return 0;
        return roundDouble(num1/num2 * 100,regx);
    }

    /**
     * 校验一个字符串是否是整数
     * @param str
     * @return
     */
    public static boolean strIsInt(String str){
        if(str == null || str.equals(""))
            return false;
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
