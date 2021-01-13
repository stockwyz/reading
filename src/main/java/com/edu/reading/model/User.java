package com.edu.reading.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value="更新个人信息参数体",description="更新个人信息参数体")
public class User extends BaseModel implements Serializable {

    /**
     * 微信openid
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "微信openid", required = true)
    private String openid;

    /**
     * 昵称
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "昵称", required = false)
    private String nickname;

    /**
     * 姓名
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "姓名", required = false)
    private String name;

    /**
     * 头像图片路径
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "头像图片路径", required = false)
    private String avatar;

    /**
     * 性别 1.男 2.女
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "性别--1.男 2.女", required = false)
    private String gendar;

    /**
     * 个性签名
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "个性签名", required = false)
    private String motto;

    /**
     * 积分--待定
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "积分--待定", required = false)
    private Integer score;

    /**
     * 等级--待定
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "等级--待定", required = false)
    private Integer studyLevel;

    /**
     * 1.学生 2.教师
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "用户类型-1.学生 2.教师", required = false)
    private Integer type;

    /**
     * 所属学校id
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "所属学校id", required = false)
    private Long schoolId;

    /**
     * 所属班级id
     *
     * @mbggenerated
     */
	@ApiModelProperty(value = "所属班级id", required = false)
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