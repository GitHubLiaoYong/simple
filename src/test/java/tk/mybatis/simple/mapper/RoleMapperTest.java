package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public class RoleMapperTest extends BaseMapperTest{
    @Test
    public void testSelectById(){
        //获取Session
        SqlSession sqlSession = getSqlSession();
        try{
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectById方法，查询id=1的角色
            SysRole role = roleMapper.selectById(1l);
            //role不问空
            Assert.assertNotNull(role);
            //roleName=管理员
            Assert.assertEquals("New Name",role.getRoleName());
        }finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectById方法，查询id=1的角色
            SysRole role = roleMapper.selectById2(1l);
            //role不问空
            Assert.assertNotNull(role);
            //roleName=管理员
            Assert.assertEquals("管理员",role.getRoleName());
        }finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectAll方法嘻哈寻所有角色
            List<SysRole> roleList = roleMapper.selectAll();
            //结果不为空
            Assert.assertNotNull(roleList);
            //角色数量大于0
            Assert.assertTrue(roleList.size() > 0);
            //验证下划线字段是否映射成功
            Assert.assertNotNull(roleList.get(0).getRoleName());
        }finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            for(SysRole role : roleList){
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege : role.getPrivilegeList()){
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.close();
        }
    }

    //157页
    @Test
    public void testSelectRoleByUserIdChoose(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(0L);
            roleMapper.updateById(role);
            //获取用户1的角色
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for(SysRole r : roleList){
                System.out.println("角色名："+r.getRoleName());
                if(r.getId().equals(1L)){
                    //第一个角色存在权限信息
                    Assert.assertNotNull(r.getPrivilegeList());
                }else if(r.getId().equals(2L)){
                    //第二个角色权限为null
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }
                for(SysPrivilege privilege : r.getPrivilegeList()){
                    System.out.println("权限名： "+privilege.getPrivilegeName());
            }

            }

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
