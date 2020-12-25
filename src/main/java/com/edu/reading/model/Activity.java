package com.edu.reading.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity extends BaseModel implements Serializable {

    /**
     * 活动标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 活动标题图片路径
     *
     * @mbggenerated
     */
    private String titlePic;

    /**
     * 活动主图路径
     *
     * @mbggenerated
     */
    private String mainPic;

    /**
     * 学校id
     *
     * @mbggenerated
     */
    private Long schoolId;

    /**
     * 年级id
     *
     * @mbggenerated
     */
    private Long gradeId;

    /**
     * 活动开始时间
     *
     * @mbggenerated
     */
    private Date startTime;

    /**
     * 活动结束时间
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * 是否结束 0.进行中 1.已完成
     *
     * @mbggenerated
     */
    private Integer finished;

    /**
     * 类型 1.英语活动 2.语文活动
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 是否发布 1.已发布 2.未发布
     *
     * @mbggenerated
     */
    private Integer published;

    /**
     * 活动简介
     *
     * @mbggenerated
     */
    private String intro;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", titlePic=").append(titlePic);
        sb.append(", mainPic=").append(mainPic);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", gradeId=").append(gradeId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", finished=").append(finished);
        sb.append(", type=").append(type);
        sb.append(", published=").append(published);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}