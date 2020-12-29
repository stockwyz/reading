package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseModel implements Serializable {

    /**
     * 微信openid
     *
     * @mbggenerated
     */
    private String openid;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 头像图片路径
     *
     * @mbggenerated
     */
    private String avatar;

    /**
     * 性别 1.男 2.女
     *
     * @mbggenerated
     */
    private String gendar;

    /**
     * 个性签名
     *
     * @mbggenerated
     */
    private String motto;

    /**
     * 积分--待定
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * 等级--待定
     *
     * @mbggenerated
     */
    private Integer studyLevel;

    /**
     * 1.学生 2.教师
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 所属学校id
     *
     * @mbggenerated
     */
    private Long schoolId;

    /**
     * 所属班级id
     *
     * @mbggenerated
     */
    private Long classId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", nickname=").append(nickname);
        sb.append(", name=").append(name);
        sb.append(", avatar=").append(avatar);
        sb.append(", gendar=").append(gendar);
        sb.append(", motto=").append(motto);
        sb.append(", score=").append(score);
        sb.append(", studyLevel=").append(studyLevel);
        sb.append(", type=").append(type);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", classId=").append(classId);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}