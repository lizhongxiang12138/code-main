package com.lzx.code.codegeneration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 项目文件信息
 *
 * @Auther: lzx
 * @Date: 2019/7/22 15:16
 */
@Getter
@Setter
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFile {

    /**
     * 项目文件名
     */
    private String projectFileName;

    /**
     * 项目文件路径
     */
    private String projectFilePath;

}
