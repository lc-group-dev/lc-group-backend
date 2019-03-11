package org.whu.cs.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author:Lucas
 * @description:
 * @Date:2019/3/11
 **/

public class DateUtil {
    public static String date = "yyyy-MM-dd";
    public static String dateTime = "yyyy-MM-dd HH:mm:ss";
    public static String day = "dd";
    public static String month = "MM";
    public static String year = "yyyy";




    /**
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, day);

        return cal.getTime();
    }

    /**
     * @param date
     * @param hour
     * @return Date
     * @Description: 增加时间
     */
    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            //	System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }

    /**
     * 格式化时间，转换成int类型形如 20140705
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Integer formatDate2Int(Date date, String pattern) {
        try {
            String result = formatDate(date, pattern);
            return Integer.parseInt(result);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 用户自己定义日期和格式，进行格式化
     *
     * @param date    用户指定的日期
     * @param pattern 用户指定的时间格式
     * @return 返回指定的格式化后的时间字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null || StringUtils.isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat datePattern = new SimpleDateFormat(pattern);

        return datePattern.format(date);
    }

    public static Date formatDateEndTime(Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(cal.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return cal.getTime();
    }

    public static Date formatDateStartTime(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return cal.getTime();
    }

    public static String formatMonday(Date date, String format) {
        return formatDate(getMonday(date), format);
    }

    public static Date getMonday(Date date) {
        return DateUtil.getOneWeekDate(date).get(0);
    }

    /**
     * 获取本周星期一和星期日的时间和日期
     *
     * @param date
     * @return
     */
    public static List<Date> getOneWeekDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        if (startCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
            startCalendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);

        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        endCalendar.setTime(startDate);
        //endCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        endCalendar.add(Calendar.DAY_OF_YEAR, 6);
        rtnList.add(endCalendar.getTime());
        return rtnList;
    }

    public static List<Date> getDate(int value) throws Exception {
        List<Date> rtnList = new ArrayList<Date>();
        Date nowDate = new Date();
        switch (value) {
            case 1:// 全部
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String startDateStr = "2016-01-01 12:00:00";
                String endDateStr = "2099-01-01 12:00:00";
                Date startDate = sd.parse(startDateStr);
                Date endDate = sd.parse(endDateStr);
                rtnList.add(startDate);
                rtnList.add(endDate);
                break;
            case 2:// 近一周
                rtnList = DateUtil.getOneWeekDate(nowDate);
                break;
            case 3:// 近二周
                rtnList = DateUtil.getTwoWeekDate(nowDate);
                break;
            case 4:// 近三周
                rtnList = DateUtil.getThreeWeekDate(nowDate);
                break;
            case 5:// 近一月
                rtnList = DateUtil.getOneMonthDate(nowDate);
                break;
            case 6:// 近二月
                rtnList = DateUtil.getTwoMonthDate(nowDate);
                break;
            case 7:// 近三月
                rtnList = DateUtil.getTHreeMonthDate(nowDate);
                break;
        }
        return rtnList;
    }

    /**
     * 近二周
     *
     * @param date
     * @return
     */
    public static List<Date> getTwoWeekDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DAY_OF_YEAR, -14);
        if (startCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
            startCalendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);
        rtnList.add(date);
        return rtnList;
    }

    /**
     * 近三周
     *
     * @param date
     * @return
     */
    public static List<Date> getThreeWeekDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DAY_OF_YEAR, -21);
        if (startCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
            startCalendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);
        rtnList.add(date);
        return rtnList;
    }

    /**
     * 近一月
     *
     * @param date
     * @return
     */
    public static List<Date> getOneMonthDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DAY_OF_YEAR, -30);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);
        rtnList.add(date);
        return rtnList;
    }

    /**
     * 近二月
     *
     * @param date
     * @return
     */
    public static List<Date> getTwoMonthDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DAY_OF_YEAR, -60);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);
        rtnList.add(date);
        return rtnList;
    }

    /**
     * 近三月
     *
     * @param date
     * @return
     */
    public static List<Date> getTHreeMonthDate(Date date) {
        List<Date> rtnList = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        startCalendar.add(Calendar.DAY_OF_YEAR, -90);
        Date startDate = startCalendar.getTime();
        rtnList.add(startDate);
        rtnList.add(date);
        return rtnList;
    }

    public static Date getDate(Long timeMillis) {
        if (timeMillis == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return calendar.getTime();
    }

    public static String getDateStr(Date date) {
        String dateStr = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dateStr += year + (month < 10 ? "0" + month : "" + month)
                + (day < 10 ? "0" + day : "" + day);
        return dateStr;
    }

    /**
     * @param startDt
     * @param endDt
     * @return int
     * @Description:获取两个日期 相隔多少天
     * @author chen.huang
     * @date 2016年12月8日 下午2:08:46
     */
    public static int getIntervalDays(Date startDt, Date endDt) {
        long intervalMilli = endDt.getTime() - startDt.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * @param second
     * @return int
     * @Description: 秒转分
     */
    public static int getLongToMinute(long second) {
        return new Long(second / 60).intValue();
    }

    /**
     * @param dt
     * @return String
     * @Description:
     */
    public static String getOherWeekStringOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 设置一天中最后时刻即23点59分59秒999毫秒
     *
     * @param date
     * @return
     */
    public static Date getOneDayEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        date = cal.getTime();
        return date;
    }

    /**
     * 设置一天中最后时刻即23点59分59秒999毫秒
     *
     * @param date
     * @return
     */
    public static Date getOneDayStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 01);
        cal.set(Calendar.MILLISECOND, 01);
        date = cal.getTime();
        return date;
    }

    /**
     * @param date
     * @return Date
     * @Description:获取 上一周星期日 的日期
     * @author chen.huang
     * @date 2017年2月10日 上午11:21:56
     */
    public static Date getPreWeekSunday(Date date) {
        Date mondayDate = DateUtil.getPreWeekMonday(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(mondayDate);
        cal.add(Calendar.DAY_OF_MONTH, 6);
        return cal.getTime();
    }

    /**
     * 获取 上一周星期一 的日期
     *
     * @param date
     * @return
     * @Description
     * @date 2016年9月5日
     * @author chen.huang
     */
    public static Date getPreWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateUtil.clearHour(cal);
        return cal.getTime();
    }

    public static void clearHour(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获得相对星期几的日期。如relative=0,compareDay=Calendar.SUNDAY,nowDate=new Date(),则获取当前星期天的日期。
     *
     * @param relativeNum
     * @param compareDay
     * @param nowDate
     * @return
     */
    public static Date getRelativeWeekDate(int relativeNum, int compareDay, Date nowDate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int nowDay = cal.get(Calendar.DAY_OF_WEEK);
        int caculateDay = nowDay == Calendar.SUNDAY ? 7 : nowDay - 1;
        int compareCaculateDay = compareDay == Calendar.SUNDAY ? 7 : compareDay - 1;
        int restDays = compareCaculateDay - caculateDay;
        int totalRestDays = 7 * relativeNum + restDays;
        cal.add(Calendar.DATE, totalRestDays);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date retDate = cal.getTime();
        return retDate;
    }

    /**
     * 获取相对周的一周结束日期。如相对数为0，则为本周星期日的日期
     *
     * @param relativeNum
     * @param date
     * @return
     */
    public static Date getRelativeWeekEndDay(int relativeNum, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int currentRestDays = day == Calendar.SUNDAY ? 0 : 7 - day + 1;
        int totalRestDays = 7 * relativeNum + currentRestDays;
        cal.add(Calendar.DATE, totalRestDays);
        Date retDate = cal.getTime();
        return retDate;
    }

    public static Float getSecondToHour(Integer second) {
        int hour, minute;
        hour = second / 3600;
        minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        String t = "0";
        if (hour != 0) {
            t = "" + hour;
        }
        if (minute != 0) {
            t += "." + minute;
        }

        if (second != 0) {
            t += "." + second;
        }
        return Float.parseFloat(t);
    }

    public static String getStrTimeStamp_hour_minute(Date startDate, Date endDate) {
        Long t = endDate.getTime() - startDate.getTime();
        long minute = (long) t / (1000 * 60);
        int hour = 0;
        if (minute > 60) {
            hour = (int) minute / 60;
            minute = (int) minute % 60;
        }
        if (minute == 0) {
            return hour > 0 ? "" + hour + "小时" : "0分钟";
        } else {
            return hour > 0 ? "" + hour + "小时" + minute + "分钟" : minute + "分钟";
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @Description 获取两个时间短 相隔的分钟数
     */
    public static int getTimeStamp_minute(Date startDate, Date endDate) {
        Double betweenMinute = getTimeStamp_minute_double(startDate, endDate);
        return DoubleUtil.round(betweenMinute);
    }

    public static double getTimeStamp_minute_double(Date startDate, Date endDate) {
        Long t = endDate.getTime() - startDate.getTime();
        Double betweenMinute = (double) t / (1000 * 60);
        return betweenMinute;
    }

    /**
     * @param dt
     * @return String
     * @Description:
     */
    public static String getWeekStringOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }

    public static String getSecondToHourString(Integer p_second) {
        StringBuffer rtnStr = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MILLISECOND, p_second * 1000);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour != 0) {
            rtnStr.append(hour + "小时");
        }
        if (minute != 0) {
            rtnStr.append(minute + "分钟");
        }
        return rtnStr.toString();

    }

    /**
     * parseDate
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, date);
    }

    /**
     * parseDate
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

