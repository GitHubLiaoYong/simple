package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysPrivilege;

public interface PrivilegeMapper {
    SysPrivilege selectPrivilegeByRoleId(Long id);
}
