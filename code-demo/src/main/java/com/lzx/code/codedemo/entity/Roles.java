package com.lzx.code.codedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/9/10 09:32
 */
@Entity(name = "ROLES")
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "idRolesConfig")
    @GenericGenerator(name ="idRolesConfig" ,strategy="org.kcsm.common.ids.SerialIdGeneratorSnowflakeId")
    @Column(name = "ID", unique = true,nullable=false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Column(name = "ROLE_NAME",length = 200)
    private String roleName;

}
