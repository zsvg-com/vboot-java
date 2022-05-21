package vboot.common.util.lang;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XdateUtil {

    public static XMLGregorianCalendar dateToXmlDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DatatypeFactory dtf = null;
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
        }
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
        dateType.setYear(cal.get(Calendar.YEAR));
        //由于Calendar.MONTH取值范围为0~11,需要加1
        dateType.setMonth(cal.get(Calendar.MONTH) + 1);
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
        dateType.setMinute(cal.get(Calendar.MINUTE));
        dateType.setSecond(cal.get(Calendar.SECOND));
        return dateType;
    }

    public static Date xmlDate2Date(XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }

    public static String getYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date());
    }

    public static String getYYYYMM() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        return dateFormat.format(new Date());
    }

    public static String getMM() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return dateFormat.format(new Date());
    }

    public static String getDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(new Date());
    }

    public static Date strToDate(String strDate) {
        if (XstringUtil.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static Date strToDateBySap(String strDate) {
        if (XstringUtil.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static String getDate(int i) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(c.getTime());
    }

    public static String getSapDate(int i) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(c.getTime());
    }

    public static String getDate(String geshi) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(geshi);
        return dateFormat.format(new Date());
    }

    public static String getDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


    public static int getDifferDays(Date data1, Date date2) {
        if (null == data1 || null == date2) {
            return -1;

        }
        long intervalMilli = data1.getTime() - date2.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    public static int getDifferDays(String strDate1, String strDate2) {
        Date date1 = strToDate(strDate1);
        Date date2 = strToDate(strDate2);
        if (null == date1 || null == date2) {
            return -1;
        }
        long intervalMilli = date1.getTime() - date2.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    public static int getDifferDaysBySap(String strDate1, String strDate2) {
        Date date1 = strToDateBySap(strDate1);
        Date date2 = strToDateBySap(strDate2);
        if (null == date1 || null == date2) {
            return -1;
        }
        long intervalMilli = date1.getTime() - date2.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    public static String getStringDateAfterTian(String strDate, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(strToDateBySap(strDate));
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(c.getTime());
    }

    public static String getMonthFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());
    }

    public static String getMonthLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return  format.format(ca.getTime());
    }


}
