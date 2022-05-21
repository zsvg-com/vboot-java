package vboot.common.util.lang;

import java.math.BigDecimal;
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

    /**
     * 获取金额
     * @param min
     * @param max
     * @return
     */
    public static BigDecimal getRandomBigDecimal(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();

        //生成随机数
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

        //返回保留两位小数的随机数。不进行四舍五入
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }

    public static int getRandom6(){
        int radom = new Random().nextInt(999999);
        if (radom < 100000) {
            radom += 100000;
        }
        return radom;
    }

}
