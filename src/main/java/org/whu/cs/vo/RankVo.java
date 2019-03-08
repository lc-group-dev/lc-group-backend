package org.whu.cs.vo;

/**
 * @author:Lucas
 * @description:总排行榜的vo
 * @Date:2019/3/8
 **/
public class RankVo {
    //    个人打卡总天数
    private Integer checkDayNum;
    //    名次
    private Integer rank;
//    历史最高连续打卡天数
    private Integer recordCheckDayNum;
//    当前打卡天数
    private Integer currentCheckDayNum;
    //    个人刷题总数
    private Integer solvedQuestion;
    //    用户名
    private String userName;
}
