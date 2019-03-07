package org.whu.cs.bean;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "check_day_info")

public class CheckDayInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "记录id")
    private Long info_id;

    @ApiModelProperty(value = "个人LeetCode主页地址，eg：https://leetcode.com/alexlj/")

    private String address;

    @ApiModelProperty(value = "用户名，eg： alexlj")
    private String username;

    // 头像url
    @Column(nullable = true)
    private String avatar;

    // 个人主页
    private String website;

    // 提交通过率
    @Column ( nullable = true )

    private double acceptanceRate ;

    // 查卡日期 格式 '2019-03-01'
    private String date;

    // 是否打卡 已打卡: 1, 未打卡: 0
    @Column ( nullable = true )
    private int isChecked ;

    // 创建时间
    private Date gmt_create;

    // 修改时间
    private Date gmt_modified;

    // 今日刷题数
    @Column ( nullable = true )
    private int solvedProblemToday ;

    // 今日提交次数
    @Column ( nullable = true )
    private int submissionToday ;

    // 总提交次数
    @Column ( nullable = true )
    private int submission ;

    // 刷题数
    @Column ( nullable = true )
    private int solvedQuestion ;

    // 提交次数
    @Column ()
    private int acceptedSubmission;

    public Long getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Long info_id) {
        this.info_id = info_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(double acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public int getSolvedProblemToday() {
        return solvedProblemToday;
    }

    public void setSolvedProblemToday(int solvedProblemToday) {
        this.solvedProblemToday = solvedProblemToday;
    }

    public int getSubmissionToday() {
        return submissionToday;
    }

    public void setSubmissionToday(int submissionToday) {
        this.submissionToday = submissionToday;
    }

    public int getSubmission() {
        return submission;
    }

    public void setSubmission(int submission) {
        this.submission = submission;
    }

    public int getSolvedQuestion() {
        return solvedQuestion;
    }

    public void setSolvedQuestion(int solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    public int getAcceptedSubmission() {
        return acceptedSubmission;
    }

    public void setAcceptedSubmission(int acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }


}
