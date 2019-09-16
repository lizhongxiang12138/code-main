package com.lzx.code.codedemo;

import com.lzx.code.codedemo.dao.RolesDao;
import com.lzx.code.codedemo.dao.UserDao;
import com.lzx.code.codedemo.entity.Roles;
import com.lzx.code.codedemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeDemoApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RolesDao rolesDao;

    @Test
    public void contextLoads() {

        User user = null;
        Roles roles = null;

        for(int i=0;i<1000;i++){
            user = new User(
                    null,
                    "lzx"+i,
                    "123456"
            );
            roles = new Roles(
                    null,
                    "角色"+i
            );
            rolesDao.save(roles);
            userDao.save(user);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
