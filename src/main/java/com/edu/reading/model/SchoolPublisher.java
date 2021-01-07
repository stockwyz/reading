package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolPublisher extends BaseModel implements Serializable {

    /**
     * 学校id
     *
     * @mbggenerated
     */
    private Long schoolId;

    /**
     * 教材出版商ID
     *
     * @mbggenerated
     */
    private Long publisherId;

    /**
     * 适用年级
     *
     * @mbggenerated
     */
    private String grade;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", grade=").append(grade);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}