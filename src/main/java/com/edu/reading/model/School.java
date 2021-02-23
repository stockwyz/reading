package com.edu.reading.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends BaseModel implements Serializable {
    /**
     * 学校名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 学校地址
     *
     * @mbggenerated
     */
    private String adress;

    /**
     * 所属区域
     *
     * @mbggenerated
     */
    private Long areaId;

    /**
     * 类型 0.小学 1初中 2.高中
     *
     * @mbggenerated
     */
    private String type;

    private String website;

    private String intro;

    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", adress=").append(adress);
        sb.append(", areaId=").append(areaId);
        sb.append(", type=").append(type);
        sb.append(", website=").append(website);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}