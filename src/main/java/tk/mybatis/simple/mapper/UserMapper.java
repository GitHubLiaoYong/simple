package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;
public interface UserMapper {
    /**
     * 通过id查询用户
     */
    SysUser selectById(Long id);
    List<SysUser> selectAll();

    /**
     * 根据用户id获取角色信息
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 新增用户
     *
     *
     */
    int insert(SysUser sysUser);

    /**
     * 使用jdbc主键自增获取插入后返回主键
     */
    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);

    int insert4(SysUser sysUser);

    /**
     * 根据主键更新
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    int deleteById(SysUser sysUser);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId,
                                                    @Param("enabled") Integer enabled);

    List<SysRole> selectRolesByUserAndRole(
            @Param("user")SysUser user,
            @Param("role")SysRole role
    );

    //动态sql
    List<SysUser> selectByUser(SysUser sysUser);

    //4.1.2 在UPDATE更新列中使用if  选择性更新的方法
    int updateByIdSelective(SysUser sysUser);

    SysUser selectByIdOrUserName(SysUser sysUser);

    //for each的用法
    List<SysUser> selectByIdList(List<Long> idList);

    //实现批量插入
    int insertList(List<SysUser> userList);

    int updateByMap(Map<String,Object> map);

    /**
     * 根据id获取用户信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

    SysUser selectUserAndRoleByIdSelect(Long id);

    List<SysUser> selectAllUserAndRoles();

    //154页
    SysUser selectAllUserAndRolesSelect(Long id);

    //163页
    void selectUserById(SysUser user);
    //164页
    List<SysUser> selectUserPage(Map<String,Object> params);
    //167ye
    int insertUserAndRoles(
            @Param("user")SysUser user,@Param("roleIds")String roleIds
    );

    int deleteUserById(Long id);


}
