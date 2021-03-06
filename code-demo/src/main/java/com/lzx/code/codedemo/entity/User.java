package com.lzx.code.codedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "idUserConfig")
    @GenericGenerator(name ="idUserConfig" ,strategy="org.kcsm.common.ids.SerialIdGeneratorSnowflakeId")
    @Column(name = "ID", unique = true,nullable=false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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

