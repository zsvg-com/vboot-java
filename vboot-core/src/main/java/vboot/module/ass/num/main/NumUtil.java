package vboot.module.ass.num.main;


import vboot.common.util.lang.XdateUtil;

public class NumUtil {
    // 利用给定的流水位生成第一个流水号
    public static String getFirstSerialNum(int zlength) {
        String serialNum = "";
        //流水号为4里下面取3
        for (int i = 0; i < zlength - 1; i++) {
            serialNum = serialNum + "0";
        }
        serialNum = serialNum + "1";
        return serialNum;
    }

    //根据当前的流水号，生成下一个流水号
    public static String getNextSerialNum(String curSerialNum) {
        curSerialNum = "1" + curSerialNum;
        Integer icurSerialNum = Integer.parseInt(curSerialNum);
        icurSerialNum++;
        curSerialNum = icurSerialNum + "";
        curSerialNum = curSerialNum.substring(1);
        return curSerialNum;
    }

    public static String getNum(String numNow, String hintType) {
        String numNext = "";
        if("yyyymmxx".equals(hintType)){
            if(numNow==null){
                numNext= XdateUtil.getYYYYMM()+getFirstSerialNum(2);
            }else{
                String yyyymm=numNow.substring(0,6);
                if(XdateUtil.getYYYYMM().equals(yyyymm)){
                    numNext= yyyymm+getNextSerialNum(numNow.substring(numNow.length() -2));
                }else{
                    numNext= XdateUtil.getYYYYMM()+getFirstSerialNum(2);
                }
            }
        }else if("xxxxxx".equals(hintType)){
            if(numNow==null){
                numNext= getFirstSerialNum(6);
            }else if(numNow.length()!=6){
                numNext= getFirstSerialNum(6);
            }else{
                numNext= getNextSerialNum(numNow);
            }
        }
        return numNext;
    }
}
