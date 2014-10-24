package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.model.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;


public class UserDAOTest extends AbstractUnitTest {

    @Autowired
    private UserDAO userMapper;

    @Test
    public void shouldInsertUserByUsernameSuccess() throws Exception{

        UserEntity entity = new UserEntity();
        entity.setUsername("jack");
        entity.setName("jack");
        entity.setPassword("123");

        assertEquals(1, userMapper.insertUserByUsername(entity));

    }
}
