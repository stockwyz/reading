package com.edu.reading.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivityBook extends BaseModel implements Serializable {

    /**
     * 学生ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 课本绘本id
     *
     * @mbggenerated
     */
    private Long bookId;

    private Long activityId;

    /**
     * 综合得分
     *
     * @mbggenerated
     */
    private BigDecimal score;

    /**
     * 0 课堂参与  1活动参与
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
        sb.append(", userId=").append(userId);
        sb.append(", bookId=").append(bookId);
        sb.append(", activityId=").append(activityId);
        sb.append(", score=").append(score);
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