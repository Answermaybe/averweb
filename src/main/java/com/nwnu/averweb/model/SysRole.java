package com.nwnu.averweb.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_role
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class SysRole {
    /**
     * 主键
     * Id
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编码
     * rolecode
     */
    private String rolecode;

    /**
     * 角色名称
     * rolename
     */
    private String rolename;

    /**
     * 显示顺序
     * sequence
     */
    private Integer sequence;

    /**
     * 状态
     * status
     */
    private String status;

    /**
     * 备注
     * remark
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.Id
     *
     * @return the value of sys_role.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.Id
     *
     * @param id the value for sys_role.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.rolecode
     *
     * @return the value of sys_role.rolecode
     *
     * @mbggenerated
     */
    public String getRolecode() {
        return rolecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.rolecode
     *
     * @param rolecode the value for sys_role.rolecode
     *
     * @mbggenerated
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode == null ? null : rolecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.rolename
     *
     * @return the value of sys_role.rolename
     *
     * @mbggenerated
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.rolename
     *
     * @param rolename the value for sys_role.rolename
     *
     * @mbggenerated
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.sequence
     *
     * @return the value of sys_role.sequence
     *
     * @mbggenerated
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.sequence
     *
     * @param sequence the value for sys_role.sequence
     *
     * @mbggenerated
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.status
     *
     * @return the value of sys_role.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.status
     *
     * @param status the value for sys_role.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.remark
     *
     * @return the value of sys_role.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.remark
     *
     * @param remark the value for sys_role.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}