package com.jingyou.jybase.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class JsonUtil {
    /**
     * 将json转为map
     * @param json
     * @return
     */
    public static Map<String,Object> json2Map(String json) {
        Map<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(json);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }

    public static String list2Json(Collection list){
        return JSONArray.fromObject(list).toString();
    }

    public static void main(String[] args) {
        String json = "{\"msg\":\"成功!\",\"msgcode\":\"001\",\"success\":\"1\",content:[{\"fqr\":\"习近平\",\"lclx\":\"三农\",\"rwbt\":\"新农村建设\",\"zy\":\"建设美丽新农村\",\"ddsj\":\"2016-12-30\",\"url\":\"http://www.baidu.com\"},{\"fqr\":\"李克强\",\"lclx\":\"经济\",\"rwbt\":\"中央经济会议\",\"zy\":\"哈哈\",\"ddsj\":\"2016-12-30\",\"url\":\"http://www.tmall.com\"}]}";
        Map<String, Object> data = json2Map(json);

        List tasks = (List)JsonUtil.json2Map(json).get("content");

        for(int i=0;i<tasks.size();i++){
            System.out.println(((Map)tasks.get(i)).get("fqr"));
        }

        System.out.println("########################");
        for(Map.Entry entry : data.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
