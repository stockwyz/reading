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
     * 年级:1=一年级,2=二年级,3=三年级,4=四年级,5=五年级,6=六年级,7=七年级（初一）,8=八年级（初二）,9=9年级（初三）,10=高一,11=高二,12=高三
     *
     * @mbggenerated
     */
    private String grade;

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
     * 是否结束:0=进行中,1=已结束
     *
     * @mbggenerated
     */
    private String finished;

    /**
     * 活动类型:1=英语,2=语文
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 是否发布:1=已发布,2=未发布
     *
     * @mbggenerated
     */
    private String published;

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
        sb.append(", grade=").append(grade);
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