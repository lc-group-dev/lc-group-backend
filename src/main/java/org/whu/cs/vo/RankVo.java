package org.whu.cs.vo;

import lombok.Data;

/**
 * @author:Lucas
 * @description:总排行榜的vo
 * @Date:2019/3/8
 **/
@Data
public class RankVo {
    //    微信头像
    private String avatarUrl;
//    //    个人打卡总天数
//    private Integer checkDayNum;
    //    点赞次数
    private Integer clickTime;
    //    当前打卡天数
    private Integer currentCheckDayNum;
    //    名次
    private Integer rank;
    //    连续打卡天数
    private Integer continueCheckDay;
    //    个人刷题总数
    private Integer solvedQuestion;
    //    用户名
    private String userName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getClickTime() {
        return clickTime;
    }

    public void setClickTime(Integer clickTime) {
        this.clickTime = clickTime;
    }

    public Integer getCurrentCheckDayNum() {
        return currentCheckDayNum;
    }

    public void setCurrentCheckDayNum(Integer currentCheckDayNum) {
        this.currentCheckDayNum = currentCheckDayNum;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getContinueCheckDay() {
        return continueCheckDay;
    }

    public void setContinueCheckDay(Integer continueCheckDay) {
        this.continueCheckDay = continueCheckDay;
    }

    public Integer getSolvedQuestion() {
        return solvedQuestion;
    }

    public void setSolvedQuestion(Integer solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
