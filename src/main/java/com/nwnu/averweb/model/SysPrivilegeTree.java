package com.nwnu.averweb.model;


import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SysPrivilegeTree {
    
    
    /**
     * 主键
     * Id
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编码
     * privilegecode
     */
    private String privilegecode;

    /**
     * 角色名称
     * privilegename
     */
    private String privilegename;

    /**
     * 父编码
     * parentcode
     */
    private String parentcode;

    /**
     * 地址
     * uri
     */
    private String uri;

    /**
     * 是否显示
     * isshow
     */
    private String isshow;

    /**
     * 显示顺序
     * sequence
     */
    private Integer sequence;

    /**
     * 备注
     * remark
     */
    private String remark;

    /***
     * 子菜单列表
     */
    private List<SysPrivilege> children;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.Id
     *
     * @return the value of sys_privilege.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.Id
     *
     * @param id the value for sys_privilege.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.privilegecode
     *
     * @return the value of sys_privilege.privilegecode
     *
     * @mbggenerated
     */
    public String getPrivilegecode() {
        return privilegecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.privilegecode
     *
     * @param privilegecode the value for sys_privilege.privilegecode
     *
     * @mbggenerated
     */
    public void setPrivilegecode(String privilegecode) {
        this.privilegecode = privilegecode == null ? null : privilegecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.privilegename
     *
     * @return the value of sys_privilege.privilegename
     *
     * @mbggenerated
     */
    public String getPrivilegename() {
        return privilegename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.privilegename
     *
     * @param privilegename the value for sys_privilege.privilegename
     *
     * @mbggenerated
     */
    public void setPrivilegename(String privilegename) {
        this.privilegename = privilegename == null ? null : privilegename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.parentcode
     *
     * @return the value of sys_privilege.parentcode
     *
     * @mbggenerated
     */
    public String getParentcode() {
        return parentcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.parentcode
     *
     * @param parentcode the value for sys_privilege.parentcode
     *
     * @mbggenerated
     */
    public void setParentcode(String parentcode) {
        this.parentcode = parentcode == null ? null : parentcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.uri
     *
     * @return the value of sys_privilege.uri
     *
     * @mbggenerated
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.uri
     *
     * @param uri the value for sys_privilege.uri
     *
     * @mbggenerated
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.isshow
     *
     * @return the value of sys_privilege.isshow
     *
     * @mbggenerated
     */
    public String getIsshow() {
        return isshow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.isshow
     *
     * @param isshow the value for sys_privilege.isshow
     *
     * @mbggenerated
     */
    public void setIsshow(String isshow) {
        this.isshow = isshow == null ? null : isshow.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.sequence
     *
     * @return the value of sys_privilege.sequence
     *
     * @mbggenerated
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.sequence
     *
     * @param sequence the value for sys_privilege.sequence
     *
     * @mbggenerated
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.remark
     *
     * @return the value of sys_privilege.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.remark
     *
     * @param remark the value for sys_privilege.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }    
	

	public List<SysPrivilege> getChildren() {
		return children;
	}

	public void setChildren(List<SysPrivilege> children) {
		this.children = children;
	}
    
}