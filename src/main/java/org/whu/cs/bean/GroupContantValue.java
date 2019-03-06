package org.whu.cs.bean;

public class GroupContantValue {
    // 是否打卡
    private static final int checked = 1;
    private static final int not_checked = 0;

    // 记录开始时间
    private static final String start_of_date = "2019-03-05";

    // 用户状态
    private static final int normal = 0;
    private static final int deleted = 1;
    private static final int locked = 2;


    public static int getChecked() {
        return checked;
    }

    public static int getNot_checked() {
        return not_checked;
    }

    public static String getStart_of_date() {
        return start_of_date;
    }

    public static int getNormal() {
        return normal;
    }

    public static int getDeleted() {
        return deleted;
    }

    public static int getLocked() {
        return locked;
    }
}
