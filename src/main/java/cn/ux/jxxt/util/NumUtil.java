package cn.ux.jxxt.util;

import cn.ux.jxxt.domain.BackWork;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NumUtil {

    public static String generateNumber() {
        String no = "";
        int num[] = new int[8];
        int c = 0;
        for (int i = 0; i < 8; i++) {
            num[i] = new Random().nextInt(10);
            c = num[i];
            for (int j = 0; j < i; j++) {
                if (num[j] == c) {
                    i--;
                    break;
                }
            }
        }
        if (num.length > 0) {
            for (int i = 0; i < num.length; i++) {
                no += num[i];
            }
        }
        return no;
    }

    public static String createProjectNo(){
        String no = "";
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        String month = date.get(Calendar.MONTH) + 1 + "";
//        String strDate = date.get(Calendar.DAY_OF_MONTH) + "";
        if (month.length() >= 1 && month.length() < 2) {
            month = "0" + month;
        }
//        if (strDate.length() >= 1 && strDate.length() < 2) {
//            strDate = "0" + strDate;
//        }
        no = year + month + "-";
        return no;
    }

    public static String toDateString(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int msec = calendar.get(Calendar.MILLISECOND);
        return "" + year + month + day + msec;
    }

    public static synchronized String getnumber(String thisCode){

        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMM");
        String thisData = thisCode.substring(4, 8);
        //这个判断就是判断你数据取出来的最后一个业务单号是不是当月的
        if(!formatter.format(date).equals(thisData)){
            System.out.println("新的一月");
            thisData = formatter.format(date);
            //如果是新的一月的就直接变成0001
            id = "HHTG" + thisData + "80001";
        }else{
            System.out.println("当月");
            DecimalFormat df = new DecimalFormat("0000");

            //不是新的一月就累加
            id ="HHTG"+ formatter.format(date)+"8"
                    + df.format(1 + Integer.parseInt(thisCode.substring(9, 13)));
        }
        return id;
    }

    public static int DateNum(Timestamp stampOne,Timestamp stampTwo){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try{
            dateOne = format.parse(stampOne.toString());
            dateTwo = format.parse(stampTwo.toString());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return differentDaysByMillisecond(dateOne,dateTwo);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
