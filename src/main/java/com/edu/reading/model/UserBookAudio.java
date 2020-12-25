package com.edu.reading.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookAudio extends BaseModel implements Serializable {

    /**
     * 学生课本选择id
     *
     * @mbggenerated
     */
    private Long userBookId;

    /**
     * 音频文件URL
     *
     * @mbggenerated
     */
    private String audio;

    /**
     * 得分
     *
     * @mbggenerated
     */
    private BigDecimal score;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userBookId=").append(userBookId);
        sb.append(", audio=").append(audio);
        sb.append(", score=").append(score);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}