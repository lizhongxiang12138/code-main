package com.lzx.code.codegeneration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 类字段模型
 *
 * @Auther: lzx
 * @Date: 2019/7/18 11:59
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassBeanField {

    /**
     * 字段数据类型
     */
    private String type;
    /**
     * 字段名称
     */
    private String name;

    /**
     * 数据表的字段名称
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String describe;

}
