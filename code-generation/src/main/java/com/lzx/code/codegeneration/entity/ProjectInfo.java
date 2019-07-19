package com.lzx.code.codegeneration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 项目信息
 *
 * @Auther: lzx
 * @Date: 2019/7/19 10:18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInfo {
    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;
}
