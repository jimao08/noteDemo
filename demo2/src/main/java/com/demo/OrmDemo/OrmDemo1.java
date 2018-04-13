package com.demo.OrmDemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class OrmDemo1 {


    public static void main(String[] args) throws Exception{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Object> list = sqlSession.selectList("testMapper.findAll");

//        List<Object> list2 = sqlSession.selectList("testMapper.findById",null);

        OrmUser query = new OrmUser();
        query.setUid(1);

        OrmUser ormUser = (OrmUser) sqlSession.selectOne("testMapper.findById", query);

        System.out.println(ormUser);



        sqlSession.close();
    }
}
