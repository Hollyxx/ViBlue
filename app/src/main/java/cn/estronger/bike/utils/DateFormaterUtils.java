package cn.estronger.bike.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormaterUtils {
                                          
	 public static String data(String time) {
         SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss",
                         Locale.CHINA);
         Date date;
         String times = null;
         try {
                 date = sdr.parse(time);
                 long l = date.getTime();
                 String stf = String.valueOf(l);
                 times = stf.substring(0, 10);
         } catch (ParseException e) {
                 e.printStackTrace();
         }
         return times;
 }
	 
	 /**
      * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日"）
      * 
      * @param time
      * @return
      */
     public static String times(String time) {
             SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd");
             @SuppressWarnings("unused")
             long lcc = Long.valueOf(time);
             int i = Integer.parseInt(time);
             String times = sdr.format(new Date(i * 1000L));
             return times;

     }
     /**
      * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日"）
      * 
      * @param time
      * @return
      */
     public static String times1(String time) {
    	 SimpleDateFormat sdr = new SimpleDateFormat("MM/dd HH:mm");
    	 @SuppressWarnings("unused")
    	 long lcc = Long.valueOf(time);
    	 int i = Integer.parseInt(time);
    	 String times = sdr.format(new Date(i * 1000L));
    	 return times;
    	 
     }
     public static String times2(String time) {
    	 SimpleDateFormat sdr = new SimpleDateFormat("MM月dd日  HH:mm");
    	 @SuppressWarnings("unused")
    	 long lcc = Long.valueOf(time);
    	 int i = Integer.parseInt(time);
    	 String times = sdr.format(new Date(i * 1000L));
    	 return times;

     }

     /**
      * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"06月14日"）
      * 
      * @param time
      * @return
      */
     public static String dataNoTime(String time) {
             SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
             @SuppressWarnings("unused")
             long lcc = Long.valueOf(time);
             int i = Integer.parseInt(time);
             String times = sdr.format(new Date(i * 1000L));
             return times;

     }
     
     /**
      * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分"）
      * 
      * @param time
      * @return
      */
     public static String dateTime(String time) {
             SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             @SuppressWarnings("unused")
             long lcc = Long.valueOf(time);
             int i = Integer.parseInt(time);
             String times = sdr.format(new Date(i * 1000L));
             return times;
             
     }
     /**
      * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日"）
      * 
      * @param time
      * @return
      */
     public static String dateNoTime(String time) {
    	 SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd");
    	 @SuppressWarnings("unused")
    	 long lcc = Long.valueOf(time);
    	 int i = Integer.parseInt(time);
    	 String times = sdr.format(new Date(i * 1000L));
    	 return times;
    	 
     }
     
     /**
      * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
      * 
      * @param time
      * @return
      */
     public static String dataToString(String time) {
             SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                             Locale.CHINA);
             Date date;
             String times = null;
             try {
                     date = sdr.parse(time);
                     long l = date.getTime();
                     String stf = String.valueOf(l);
                     times = stf.substring(0, 10);
             } catch (ParseException e) {
                     e.printStackTrace();
             }
             return times;
     }
     public static String dataToString1(String time) {
    	 SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
    			 Locale.CHINA);
    	 Date date;
    	 String times = null;
    	 try {
    		 date = sdr.parse(time);
    		 long l = date.getTime();
    		 String stf = String.valueOf(l);
    		 times = stf.substring(0, 10);
    	 } catch (ParseException e) {
    		 e.printStackTrace();
    	 }
    	 return times;
     }
     
 	public static String getFriendlyLength(int lenMeter) {
 		if (lenMeter==0) {
			return "0";
		}else {
			float dis = (float) lenMeter / 1000;
			DecimalFormat fnum = new DecimalFormat("##0.00");
			String dstr = fnum.format(dis);
			return dstr;
		}
	}
     
}