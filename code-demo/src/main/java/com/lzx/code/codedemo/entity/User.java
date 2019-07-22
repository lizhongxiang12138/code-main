package com.lzx.code.codedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 描述: 用户
 *
 * @Auther: lzx
 * @Date: 2019/7/11 15:39
 */
@Entity(name = "USER")
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID",length = 32)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "USER_NAME",length = 100)
    private String userName;
    /**
     * 密码
     */
    @Column(name = "PASSWORD",length = 100)
    private String password;
}

