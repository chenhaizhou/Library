package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.mapper.UserMapper;
import com.thoughtworks.dolphin.service.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;


	public int insertUser(User user) {
        return userDAO.insertUser(user);
//        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
//
//        try
//        {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            userMapper.InsertUser(user);
//            sqlSession.commit();
//        }finally {
//            sqlSession.close();
//        }
//        return 0;
    }

}
