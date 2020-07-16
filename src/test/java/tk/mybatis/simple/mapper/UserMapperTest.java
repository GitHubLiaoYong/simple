package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        //获取session
        SqlSession sqlSession = getSqlSession();
        try
        {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询id == 1的用户
            SysUser user = userMapper.selectById(1L);
            //user不为空
            Assert.assertNotNull(user);
            //userName = admin
            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.close();

        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectAll方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            //结果不为空
            Assert.assertNotNull(userList);
            //用户数量大于0个
            Assert.assertTrue(userList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectRoleByUserId(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            //查询到的结果不为空
            Assert.assertNotNull(roleList);
            //角色数大于0
            Assert.assertTrue(roleList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片到byte数组中
            user.setHeadImg(new byte[]{1,2,2});
            user.setCreateTime(new Date());
            //讲新建的对象插入数据库中，特别注意这里的返回值result是执行sql影响的行数
            int result = userMapper.insert(user);
            //直插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配置回写的id的值
            Assert.assertNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片到byte数组中
            user.setHeadImg(new byte[]{1,2,2});
            user.setCreateTime(new Date());
            //讲新建的对象插入数据库中，特别注意这里的返回值result是执行sql影响的行数
            int result = userMapper.insert2(user);
            //直插入一条数据
            Assert.assertEquals(1,result);
            //因为id回写，所以id不为null
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片到byte数组中
            user.setHeadImg(new byte[]{1,2,2});
            user.setCreateTime(new Date());
            //讲新建的对象插入数据库中，特别注意这里的返回值result是执行sql影响的行数
            int result = userMapper.insert2(user);
            //直插入一条数据
            Assert.assertEquals(1,result);
            //因为id回写，所以id不为null
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询1个user对象
            SysUser user = userMapper.selectById(1L);
            //当前userName为admin
            Assert.assertEquals("admin",user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            user.setUserEmail("test@mybatis.tk");
            //更新数据
            int result = userMapper.updateById(user);
            Assert.assertEquals(1,result);
            //根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字是
            Assert.assertEquals("admin_test",user.getUserName());
        }finally {
            //为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询一个user对象,根据id=1查询
            SysUser user1 = userMapper.selectById(1L);

            //现在还能查询出user对象
            Assert.assertNotNull(user1);
            //调用删除方法
            Assert.assertEquals(1,userMapper.deleteById(1L));
            //再次查询，这是应该没有值,null
            Assert.assertNull(userMapper.selectById(1L));

            //使用SysUser参数在进行一次测试，根据id = 1001查询
            SysUser user2 = userMapper.selectById(1001L);
            //现在还能查询出user对象
            Assert.assertNotNull(user2);
            //调用方法删除,注意这里使用参数为user2
            Assert.assertEquals(1,userMapper.deleteById(user2));
            //再次查询，这是应该没有值，为null
            Assert.assertNull(userMapper.selectById(1001L));

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //地哦哦用selectRolesByUserIdAndRoleEnabled方法查询用户角色
            List<SysRole> userList =
                    userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);
            Assert.assertNotNull(userList);
            //角色数量大于0个
            Assert.assertTrue(userList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //地哦哦用selectRolesByUserIdAndRoleEnabled方法查询用户角色
            SysUser user = userMapper.selectById(1L);
            SysRole role = new SysRole();
            role.setEnabled(1L);
            List<SysRole> userList =
                    userMapper.selectRolesByUserAndRole(user,role);
            Assert.assertNotNull(userList);
            //角色数量大于0个
            Assert.assertTrue(userList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    //动态SQL select 使用 if
    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            //当同事查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            //由于没有同时符合这两个条件的用户，因此查询结果为0
            Assert.assertTrue(userList.size() == 0);

        }finally {
            sqlSession.close();
        }
    }
    //update 使用if
    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个新user对象
            SysUser user = new SysUser();
            //更新id=1的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            //更新邮箱。注意，这里的返回值result执行的是SQL影响的行数
            int result = userMapper.updateByIdSelective(user);
            //只更新1条数据
            Assert.assertEquals(1,result);
            //根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字保持不变，但是邮箱变成了新的
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        }finally {
            //为了不影响其它测试，这里选择回滚
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //在insert中使用 if动态
    @Test
    public void testInsert4Selective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            //插入数据库
            userMapper.insert4(user);
            //获取插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            //当没有id时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            //当id和name都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2,userList.size());
        }finally {
            sqlSession.close();
        }
    }

    //

    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            List<SysUser> userList = new ArrayList<SysUser>();
            for(int i = 0; i < 2; i++){
                SysUser user = new SysUser();
                user.setUserName("test"+i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            //将新建的对象批量插入数据库中
            //特别注意，这里的返回值result是执行SQL影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2,result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //foreach 实现动态插入数据
    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<String,Object>();
            //查询了条件，同样也是更新字段，必须保证该值存在
            map.put("id",1L);
            //要更新其它字段
            map.put("user_email","test@mybatis.tk");
            map.put("user_password","12345678");
            //更新数据
            userMapper.updateByMap(map);
            //根据当前id查询修改后的数据
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectUserAndRoleById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(sysUser);
            Assert.assertNotNull(sysUser.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数"+userList.size());
            for(SysUser user : userList){
                System.out.println("用户名："+user.getUserName());
                for(SysRole role : user.getRoleList()){
                    System.out.println("角色名："+role.getRoleName());
                    for(SysPrivilege privilege : role.getPrivilegeList()){
                        System.out.println("权限名："+privilege.getPrivilegeName());
                    }
                }

            }
        }finally {
            sqlSession.close();
        }
    }
    //154页
    @Test
    public void testSelectAllUserAndRolesSelect(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("用户名："+user.getUserName());
            for(SysRole role : user.getRoleList()){
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege : role.getPrivilegeList()){
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.close();
        }
    }
    //164页
    @Test
    public void testSelectUserById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("用户名："+user.getUserName());

        }finally{
            sqlSession.close();
        }
    }

    //165页
    @Test
    public void testSelectUserPage(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("userName","ad");
            params.put("offset",0);
            params.put("limit",10);
            //params.put("total",0);
            List<SysUser> userList = userMapper.selectUserPage(params);
            Long total = (Long)params.get("total");
            System.out.println("总数："+total);
            for(SysUser user : userList){
                System.out.println("用户名："+user.getUserName());
            }
        }finally {
            sqlSession.close();
        }
    }
    //167页
    @Test
    public void testInsertAndDelete(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            //插入用户信心和角色相关信息
            userMapper.insertUserAndRoles(user,"1,2");
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getCreateTime());
            //可以执行下面的commit后在查看数据库中的数据
            //sqlSession.commit();
            //测试阐述刚刚插入的数据
            userMapper.deleteUserById(user.getId());

        }finally {
            sqlSession.close();
        }
    }

}
