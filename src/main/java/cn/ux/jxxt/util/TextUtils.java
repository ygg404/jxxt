package cn.ux.jxxt.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextUtils {

    /**
     * 判断获取的内容是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }


    /**
     * vue特殊日期计算
     *
     * @param date
     * @return
     */
    public static String VueDateToString(String date) {
        String newDate = "";
        if (!date.contains("T")) {
            return date;
        }
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = date.replace("Z", " UTC");//是空格+UTC
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
            Date d = format.parse(date);
            newDate = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 保存float后两位数
     *
     * @return
     */
    public static String saveTwoNum(float num) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(num);
    }

    /**
     * 随机生成三位数
     *
     * @param num
     * @return
     */
    public static String getThreeNum(String num) {
        int i = (int) (Math.random() * 900 + 100);
        String threeNum = Integer.toString(i);
        return num + threeNum;
    }

    /**
     * String转timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp stringToTimeStamp(String date) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(date);
            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 计算两个日期相差时间
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static long getDateNum(String startDate, String endDate) throws Exception {
        long day = 0;
        Date start_date = new Date();
        Date end_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (startDate != null) {
            start_date = sdf.parse(startDate);
        }
        if (endDate != null) {
            end_date = sdf.parse(endDate);
        }

        day = (end_date.getTime() - start_date.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }
}
