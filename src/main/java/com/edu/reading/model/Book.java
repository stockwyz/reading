package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book  extends BaseModel implements Serializable {

    /**
     * 出版商-课本用
     *
     * @mbggenerated
     */
    private String publisher;

    /**
     * 所属单元名称-课本用
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * 课本标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 图片-背景URL
     *
     * @mbggenerated
     */
    private String pic;

    /**
     * 音频文件URL
     *
     * @mbggenerated
     */
    private String audio;

    /**
     * 上传教师ID-绘本用
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 学校ID--绘本用
     *
     * @mbggenerated
     */
    private Long schoolId;

    /**
     * 班级ID--绘本用
     *
     * @mbggenerated
     */
    private Long classId;

    /**
     * 年级
     *
     * @mbggenerated
     */
    private String grade;

    /**
     * 学期 0上学期 1下学期
     *
     * @mbggenerated
     */
    private Integer term;

    /**
     * 是否发布 0.未发布 1已发布
     *
     * @mbggenerated
     */
    private Integer published;

    /**
     * 课本内容
     *
     * @mbggenerated
     */
    private String content;

    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", publisher=").append(publisher);
        sb.append(", unit=").append(unit);
        sb.append(", title=").append(title);
        sb.append(", pic=").append(pic);
        sb.append(", audio=").append(audio);
        sb.append(", userId=").append(userId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", classId=").append(classId);
        sb.append(", grade=").append(grade);
        sb.append(", term=").append(term);
        sb.append(", published=").append(published);
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