package com.thoughtworks.dolphin.util;

/**
 * Created by ybhan on 9/26/14.
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.Reader;

/**
 * Created by ybhan on 9/26/14.
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    private  MyBatisUtil(){}

    static {
        Reader reader= null;

        try
        {
            reader = Resources.getResourceAsReader("/mybatis-config.xml");
        }catch (Exception e){

        }

        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory()
    {
        return factory;
    }
}

