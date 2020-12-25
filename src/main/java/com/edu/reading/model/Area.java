package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area extends BaseModel implements Serializable {

    /**
     * uuid
     *
     * @mbggenerated
     */
    private String uid;

    /**
     * 编码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 父级编码
     *
     * @mbggenerated
     */
    private String parentCode;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", code=").append(code);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", name=").append(name);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}