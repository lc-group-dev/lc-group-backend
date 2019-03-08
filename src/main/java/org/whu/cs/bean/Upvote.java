package org.whu.cs.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户点赞信息
 */
@Entity
public class Upvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upvoteID;

    // 点赞用户
    private Long fromMemberId;
    // 被点赞用户
    private Long toMemberId;
    // 点击次数（点赞可连击）
    private int clickTime;
    // 记录创建时间
    private Date createTime;
    // 记录修改时间
    private Date modifiedTime;
    // 点赞状态
    private int status;
    // 点赞日期，格式 yyyy-MM-dd
    private String date;

    /**
     * Gets upvote id.
     *
     * @return the upvote id
     */
    public Long getUpvoteID() {
        return upvoteID;
    }

    /**
     * Sets upvote id.
     *
     * @param upvoteID the upvote id
     */
    public void setUpvoteID(Long upvoteID) {
        this.upvoteID = upvoteID;
    }

    public Long getFromMemberId() {
        return fromMemberId;
    }

    public void setFromMemberId(Long fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public Long getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Long toMemberId) {
        this.toMemberId = toMemberId;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets click time.
     *
     * @return the click time
     */
    public int getClickTime() {
        return clickTime;
    }

    /**
     * Sets click time.
     *
     * @param clickTime the click time
     */
    public void setClickTime(int clickTime) {
        this.clickTime = clickTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
