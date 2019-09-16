package com.lzx.code.codedemo.dao;

import com.lzx.code.codedemo.entity.Roles;
import com.lzx.code.codedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 描述: 用户dao接口
 *
 * @Auther: lzx
 * @Date: 2019/7/11 15:52
 */
@RepositoryRestResource(path = "roles")
public interface RolesDao extends JpaRepository<Roles,Long>,JpaSpecificationExecutor<Roles> {
}
