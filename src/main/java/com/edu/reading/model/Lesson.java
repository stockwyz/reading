package com.edu.reading.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value="课程实体",description="课程查询接口返回课程实体")
public class Lesson extends BaseModel implements Serializable {

    /**
     * 目录ID-课本用
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "目录ID-课本用")
    private Long directoryId;

    /**
     * 课本标题
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "课程标题")
    private String title;

    /**
     * 图片-背景URL
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "课程图片URL")
    private String pic;

    /**
     * 音频文件URL
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "课程音频URL")
    private String audio;

    /**
     * 上传教师ID-绘本用
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "上传教师ID-绘本用")
    private Long userId;

    /**
     * 学校ID--绘本用
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "上传教师所在学校ID-绘本用")
    private Long schoolId;

    /**
     * 班级ID--绘本用
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "上传教师所在班级ID-绘本用")
    private Long classId;

    /**
     * 年级--绘本用
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "绘本适用年级")
    private String grade;

    /**
     * 学期--绘本用  0上学期 1下学期
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "绘本适用学期--0上学期 1下学期")
    private String term;

    /**
     * 是否发布 0.未发布 1已发布
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "是否发布--0.未发布 1已发布")
    private String published;

    /**
     * 科目  1.数学 2.语文
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "课程学科--1.数学 2.语文")
    private String subject;

    /**
     * 类型--1.课本 2绘本
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "课程类型--1.课本 2绘本")
    private String type;

    /**
     * 课本内容
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "课文内容")
    private String content;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", directoryId=").append(directoryId);
        sb.append(", title=").append(title);
        sb.append(", pic=").append(pic);
        sb.append(", audio=").append(audio);
        sb.append(", userId=").append(userId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", classId=").append(classId);
        sb.append(", grade=").append(grade);
        sb.append(", term=").append(term);
        sb.append(", published=").append(published);
        sb.append(", subject=").append(subject);
        sb.append(", type=").append(type);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}