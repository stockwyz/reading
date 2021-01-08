package com.edu.reading.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.edu.reading.common.tree.TreeNode;
import com.edu.reading.common.tree.annotation.Child;
import com.edu.reading.common.tree.annotation.Children;
import com.edu.reading.common.tree.annotation.Expanded;
import com.edu.reading.common.tree.annotation.Leaf;
import com.edu.reading.common.tree.annotation.Parent;
import com.edu.reading.common.tree.annotation.SettingParent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Directory extends BaseModel implements TreeNode, Serializable {

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
    
    private String breadcrumb;
    
    @JsonIgnore
    private boolean reserved;
    
    @JsonIgnore
    private TreeNode parent;
    
    /**
     * 下级子结点
     */
    private List<TreeNode> children = new LinkedList<TreeNode>();
    
    /**
     * 是否为叶子结点
     */
    @JsonIgnore
    private boolean leaf = true;
    
    /**
     * 是否默认展开
     */
    @JsonIgnore
    private boolean expanded;   

    private static final long serialVersionUID = 1L;

    @Parent
    public Long getParentid() {
        return parentId;
    }

    public void setParentid(Long parentId) {
        this.parentId = parentId;
    }
    
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	@SettingParent
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}

	@Override
	@Children
	public List<TreeNode> getChildren() {
		// TODO Auto-generated method stub
		return this.children;
	}

	@Override
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	@Override
	public Integer getDisplayorder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		return this.leaf;
	}

	@Override
	@Leaf
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public boolean isExpanded() {
		return this.expanded;
	}
	
	@Expanded
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public boolean isReserved() {
		return this.reserved;
	}

	@Override
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	@Override
	public void setBreadcrumb(String bc) {
		this.breadcrumb = bc;
	}
	
	public String getBreadcrumb() {
		return breadcrumb;
	}

	@Child
	public void addChild(TreeNode child) {
		this.children.add(child);
	} 
    
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

	@Override
	public int compareTo(TreeNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLevelCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		TreeNode selfCloned = (TreeNode) super.clone();
		if (null != children && !children.isEmpty()) {
			List<TreeNode> childrenCloned = new ArrayList<TreeNode>();
			for (TreeNode child : children) {
				TreeNode childCloned = (TreeNode) child.clone();
				childCloned.setParent(selfCloned);
				childrenCloned.add(childCloned);
			}
			selfCloned.setChildren(childrenCloned);
		}
		return selfCloned;
	}
}