package com.abc.module.wc.match.main;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import vboot.common.util.lang.XnumberUtil;

import java.io.IOException;
import java.math.BigDecimal;

public class Dataxx {

    public static final String MY_URL2 = "http://match.sports.sina.com.cn/football/player.php?id=14937";
    public static final String MY_URL = "http://info.sports.sina.com.cn/api/live/opta_lineup.php?format=json&_t=0.7390982927490362&id=o_958029&callback=fun_786506334825271269";

    public static void getRealAddressByIP() {
        String my_url="http://info.sports.sina.com.cn/api/live/opta_lineup.php?format=json&_t=0.7390982927490362&id=o_"+"958029"+"&callback=fun_786506334825271269";
        String str = HttpUtil.createGet(my_url).execute().body();
        String xx = str.split("fun_786506334825271269")[1];
        xx = xx.substring(1, xx.length() - 13);
        JSONObject json = JSONObject.parseObject(xx);

//        JsonObject returnData = new JsonParser().parse(xx).getAsJsonObject();
        JSONObject json2 = (JSONObject) json.get("result");
        JSONObject json3 = (JSONObject) json2.get("data");
        System.out.println(json3);
//        JSONArray array = (JSONArray) json3.get("event");
    }



    public static void main(String[] args) throws IOException {
//        getRealAddressByIP();
//        get2();
        System.out.println(XnumberUtil.getRandomBigDecimal(new BigDecimal(10),new BigDecimal(50)));
        System.out.println(XnumberUtil.getRandomBigDecimal(new BigDecimal(30),new BigDecimal(100)));
        System.out.println(XnumberUtil.getRandomBigDecimal(new BigDecimal(30),new BigDecimal(100)));
        System.out.println(XnumberUtil.getRandomBigDecimal(new BigDecimal(30),new BigDecimal(100)));
//        System.out.println((int)(Math.random()*50) + 50);
//        System.out.println((int)(Math.random()*50) + 50);

    }


}
