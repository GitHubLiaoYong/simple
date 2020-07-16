package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest{
    @Test
    public void testL1Cache(){
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1 = userMapper.selectById(1L);
            //对当前后取得对象重新赋值
            user1.setUserName("New Name");
            //再次查询获取id相同的用户
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertEquals(user1,user2);
            Assert.assertEquals("New Name",user2.getUserName());
        }finally {
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession");
        sqlSession = getSqlSession();
        try{
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = userMapper.selectById(1L);
            //第二个session获取的用户名仍然是admin
            Assert.assertNotEquals("New Name",user2.getUserName());
            //这里的user2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(user1,user2);
            //执行删除操作
            userMapper.deleteById(2L);
            //获取user3
            SysUser user3 = userMapper.selectById(1L);
            //这里user2和user3是两个不同的实例
            Assert.assertNotEquals(user2,user3);
        }finally {
            sqlSession.close();
        }
    }
    //185页，测试二级缓存的效果
    @Test
    public void testL2Cache(){
        SqlSession sqlSession = getSqlSession();
        SysRole role1 = null;
        try{
            //
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role1 = roleMapper.selectById(1L);
            //对当前获取的对象重新赋值
            role1.setRoleName("New Name");
            //再次查询相同id的用户
            SysRole role2 = roleMapper.selectById(1L);
            //瑞然没有更新数据库，但是这个用户名和role1重新赋值的名字相同
            Assert.assertEquals("New Name",role2.getRoleName());
            //无论如何，role2和role1完全就是同一个实例
            Assert.assertEquals(role1,role2);
        }finally {
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession");
        sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role2 = roleMapper.selectById(1L);
            //第二个session获取的用户名时New Name
            Assert.assertEquals("New Name",role2.getRoleName());
            //这里role2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(role1,role2);
            //获取Role3
            SysRole role3 = roleMapper.selectById(1L);
            //这里role2和role3是两个不同的实例
            Assert.assertNotEquals(role2,role3);
        }finally {
            sqlSession.close();
        }
    }
}
