package com.jingyou.jybase.common.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/3.
 */
public class PropertiesUtil {
    private static PropertiesUtil instance = null;
    private static Properties props;
    private PropertiesUtil(String filePath){
        try {
            init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertiesUtil getInstance(String filePath){
        if(instance == null){
            instance = new PropertiesUtil(filePath);
        }
        return instance;
    }

    private void init(String filePath) throws Exception{
        FileInputStream in = null;
        try {
            props = new Properties();
            props.load(PropertiesUtil.class.getResourceAsStream(filePath));
        } catch(Exception e) {
            throw e;
        }finally{
            if(in != null){
                in.close();
            }
        }
    }

    /**
     * 根据Key读取Value
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) {
        if (props.containsKey(key)) {
            String value = props.getProperty(key);// 得到某一属性的值
            return value;
        } else {
            return "";
        }
    }
}
