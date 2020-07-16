package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

public class BaseMapperTest {//基础测试类
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void init(){
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);//通过资源创建工厂
            reader.close();
        }catch(IOException ignore)
        {
            ignore.printStackTrace();
        }
    }
    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();//返回一个会话
    }
}
