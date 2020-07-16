package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest extends BaseMapperTest{


//    @BeforeClass
//    public static void init(){
//        try{
//            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            reader.close();
//        }catch (IOException ignore){
//            ignore.printStackTrace();
//        }
//    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }finally {
            //
            sqlSession.close();
        }
    }
    @Test
    public void testExample(){
        //
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建example对象
            CountryExample example = new CountryExample();
            //设置排序规则
            example.setOrderByClause("id desc, countryname asc");
            //设置是否distinct去重
            example.setDistinct(true);
            //创建条件
            CountryExample.Criteria criteria= example.createCriteria();
            //id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            //id < 4
            criteria.andIdLessThan(4);
            //countrycode like "%U%"
            //最容易出错的地方，注意like必须自己写上通配符的位置
            criteria.andCountrycodeLike("%U%");
            //or的情况下
            CountryExample.Criteria or = example.or();
            //countryname=中国
            or.andCountrynameEqualTo("中国");
            //执行查询
            List<Country> countryList = countryMapper.selectByExample(example);
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByExampleSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();//创建条件，只能有一个
            //更新所有id 》 2的国家
            criteria.andIdGreaterThan(2);
            //创建一个要设置的对象
            Country country = new Country();
            //设置属性
            country.setCountryname("China");
            //执行查询
            countryMapper.updateByExampleSelective(country,example);
            printCountryList(countryMapper.selectByExample(example));
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for(Country country : countryList){
            System.out.printf("%-4d%4s%4s\n",
                    country.getId(),country.getCountryname(),country.getCountrycode());
        }

    }
}
