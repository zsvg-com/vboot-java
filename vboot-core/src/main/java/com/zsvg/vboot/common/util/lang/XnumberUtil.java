package com.zsvg.vboot.common.util.lang;

import java.util.Random;

public class XnumberUtil {
    public static double tsize = 1024L * 1024;

    public static double gsize = 1024L;


    public static String tranWellSize(Long space) {

        if (space > tsize) {
            return String.format("%.2f", space / tsize) + "T";
        } else if (space > gsize) {
            return String.format("%.2f", space / gsize) + "G";
        } else if(space>0){
            return space + "M";
        }else{
            return "0";
        }
    }

    public static int getRandom6(){
        int radom = new Random().nextInt(999999);
        if (radom < 100000) {
            radom += 100000;
        }
        return radom;
    }

}
