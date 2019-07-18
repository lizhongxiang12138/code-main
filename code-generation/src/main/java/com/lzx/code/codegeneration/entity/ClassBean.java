package com.lzx.code.codegeneration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 描述: 实体类模型定义
 *
 * @Auther: lzx
 * @Date: 2019/7/18 11:53
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassBean {

    /**
     * bean名称（就是把首字母变成小写）
     */
    private String springBeanName;

    /**
     * 类名称
     */
    private String className;

    /**
     * 对应的表名
     */
    private String tableName;

    /**
     * 类注释
     */
    private String classDescribe;

    /**
     * 主键类型
     */
    private String idType;

    /**
     * 主键在表中的主键名称
     */
    private String idColumnName;

    /**
     * 主键字段名称
     */
    private String idName;

    /**
     * 主键注释
     */
    private String idDescribe;

    private List<ClassBeanField> fields;

}
