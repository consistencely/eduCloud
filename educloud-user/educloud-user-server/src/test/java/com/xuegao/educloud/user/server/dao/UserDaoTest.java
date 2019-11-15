package com.xuegao.educloud.user.server.dao;

import com.xuegao.educloud.user.server.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 15:45
 * @Description:
 */
public class UserDaoTest extends BaseTest{

    @Autowired
    private UserDao userDao;

    @Test
    public void saveUserRole() throws Exception {
        Integer[] roles = new Integer[]{1,2,3};
        long userId = 1001L;
        userDao.saveUserRole(userId,roles);

    }

    @Test
    public void saveUserGrade() throws Exception {
        Integer[] grades = new Integer[]{4,5};
        long userId = 1001L;
        userDao.saveUserGrade(userId,grades);
    }

}