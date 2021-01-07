package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Directory extends BaseModel implements Serializable {

    /**
     * 出版商ID
     *
     * @mbggenerated
     */
    private Long publisherId;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 父目录
     *
     * @mbggenerated
     */
    private Long parentId;

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
     * 1.英语 2.语文
     *
     * @mbggenerated
     */
    private Integer subject;

    /**
     * 1.课本 2绘本
     *
     * @mbggenerated
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", grade=").append(grade);
        sb.append(", term=").append(term);
        sb.append(", subject=").append(subject);
        sb.append(", type=").append(type);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}