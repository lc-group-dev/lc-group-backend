package org.whu.cs.bean;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;


/**
 * 存储用户每日的打卡信息，数据由爬虫获取
 */
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
    @Column(nullable = true)

    private double acceptanceRate;

    // 查卡日期 格式 '2019-03-01'
    private String date;

    // 是否打卡 已打卡: 1, 未打卡: 0
    @Column(nullable = true)
    private int isChecked;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date modifiedTime;

    // 今日刷题数
    @Column(nullable = true)
    private int solvedProblemToday;

    // 今日提交次数
    @Column(nullable = true)
    private int submissionToday;

    // 总提交次数
    @Column(nullable = true)
    private int submission;

    // 刷题数
    @Column(nullable = true)
    private int solvedQuestion;

    // 提交次数
    @Column()
    private int acceptedSubmission;

    /**
     * Gets info id.
     *
     * @return the info id
     */
    public Long getInfo_id() {
        return info_id;
    }

    /**
     * Sets info id.
     *
     * @param info_id the info id
     */
    public void setInfo_id(Long info_id) {
        this.info_id = info_id;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets avatar.
     *
     * @param avatar the avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets website.
     *
     * @param website the website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets acceptance rate.
     *
     * @return the acceptance rate
     */
    public double getAcceptanceRate() {
        return acceptanceRate;
    }

    /**
     * Sets acceptance rate.
     *
     * @param acceptanceRate the acceptance rate
     */
    public void setAcceptanceRate(double acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets is checked.
     *
     * @return the is checked
     */
    public int getIsChecked() {
        return isChecked;
    }

    /**
     * Sets is checked.
     *
     * @param isChecked the is checked
     */
    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * Gets gmt create.
     *
     * @return the gmt create
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets gmt create.
     *
     * @param createTime the gmt create
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets gmt modified.
     *
     * @return the gmt modified
     */
    public Date getGmt_modified() {
        return modifiedTime;
    }

    /**
     * Sets gmt modified.
     *
     * @param gmt_modified the gmt modified
     */
    public void setGmt_modified(Date gmt_modified) {
        this.modifiedTime = gmt_modified;
    }

    /**
     * Gets solved problem today.
     *
     * @return the solved problem today
     */
    public int getSolvedProblemToday() {
        return solvedProblemToday;
    }

    /**
     * Sets solved problem today.
     *
     * @param solvedProblemToday the solved problem today
     */
    public void setSolvedProblemToday(int solvedProblemToday) {
        this.solvedProblemToday = solvedProblemToday;
    }

    /**
     * Gets submission today.
     *
     * @return the submission today
     */
    public int getSubmissionToday() {
        return submissionToday;
    }

    /**
     * Sets submission today.
     *
     * @param submissionToday the submission today
     */
    public void setSubmissionToday(int submissionToday) {
        this.submissionToday = submissionToday;
    }

    /**
     * Gets submission.
     *
     * @return the submission
     */
    public int getSubmission() {
        return submission;
    }

    /**
     * Sets submission.
     *
     * @param submission the submission
     */
    public void setSubmission(int submission) {
        this.submission = submission;
    }

    /**
     * Gets solved question.
     *
     * @return the solved question
     */
    public int getSolvedQuestion() {
        return solvedQuestion;
    }

    /**
     * Sets solved question.
     *
     * @param solvedQuestion the solved question
     */
    public void setSolvedQuestion(int solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    /**
     * Gets accepted submission.
     *
     * @return the accepted submission
     */
    public int getAcceptedSubmission() {
        return acceptedSubmission;
    }

    /**
     * Sets accepted submission.
     *
     * @param acceptedSubmission the accepted submission
     */
    public void setAcceptedSubmission(int acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }


}
