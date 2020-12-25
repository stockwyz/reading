package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityBook extends BaseModel implements Serializable {

    /**
     * 活动ID
     *
     * @mbggenerated
     */
    private Long activityId;

    /**
     * 课本ID
     *
     * @mbggenerated
     */
    private Long bookId;

    /**
     * 参与人数
     *
     * @mbggenerated
     */
    private Integer members;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", bookId=").append(bookId);
        sb.append(", members=").append(members);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}