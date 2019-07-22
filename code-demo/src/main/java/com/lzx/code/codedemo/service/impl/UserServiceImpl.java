package com.lzx.code.codedemo.service.impl;

import com.lzx.code.codedemo.dao.UserDao;
import com.lzx.code.codedemo.entity.User;
import com.lzx.code.codedemo.service.UserService;
import com.lzx.code.japcommon.base.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * 描述:用户业务接口实现
 *
 * @Auther: lzx
 * @Date: 2019/7/11 17:09
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        /*
        !!!!!!!!!!!!!!!!!!!!重点：
        这个时必须的   ·······~~~~~~~~注意哦
        */
        this.dao = userDao;
        this.userDao = userDao;
    }

    /**
     * 扩展基类中没有的查询条件自己的查询条件
     * @return
     */
    @Override
    public List<Predicate> createPredicateList() {
        return super.createPredicateList();
    }
}
