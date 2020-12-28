package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classes extends BaseModel implements Serializable {

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 年级 1年级---高三(文)
     *
     * @mbggenerated
     */
    private String grade;

    /**
     * 学生人数
     *
     * @mbggenerated
     */
    private Integer members;

    /**
     * 学校ID
     *
     * @mbggenerated
     */
    private Long schoolId;
    
    /**
     * 班级图标
     *
     * @mbggenerated
     */
    private String icon;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", grade=").append(grade);
        sb.append(", members=").append(members);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}