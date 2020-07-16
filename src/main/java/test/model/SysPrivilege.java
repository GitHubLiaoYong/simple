package test.model;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_privilege
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class SysPrivilege {
    /**
     * Database Column Remarks:
     *   权限id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_privilege.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   权限名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_privilege.privilege_name
     *
     * @mbggenerated
     */
    private String privilegeName;

    /**
     * Database Column Remarks:
     *   权限URL
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_privilege.privilege_url
     *
     * @mbggenerated
     */
    private String privilegeUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.id
     *
     * @return the value of sys_privilege.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.id
     *
     * @param id the value for sys_privilege.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.privilege_name
     *
     * @return the value of sys_privilege.privilege_name
     *
     * @mbggenerated
     */
    public String getPrivilegeName() {
        return privilegeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.privilege_name
     *
     * @param privilegeName the value for sys_privilege.privilege_name
     *
     * @mbggenerated
     */
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName == null ? null : privilegeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_privilege.privilege_url
     *
     * @return the value of sys_privilege.privilege_url
     *
     * @mbggenerated
     */
    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_privilege.privilege_url
     *
     * @param privilegeUrl the value for sys_privilege.privilege_url
     *
     * @mbggenerated
     */
    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl == null ? null : privilegeUrl.trim();
    }
}