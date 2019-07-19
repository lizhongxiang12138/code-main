package com.lzx.code.codegeneration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 启动类模型
 *
 * @Auther: lzx
 * @Date: 2019/7/19 10:40
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationJavaInfo {

    private String packageName;
    private String className;

}
