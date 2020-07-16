package tk.mybatis.simple.model;

public class SysUserRole {
    //映射用户权限表

    //用户ID
    private Long userId;
    //角色id
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
