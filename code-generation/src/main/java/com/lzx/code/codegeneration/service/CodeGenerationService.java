package com.lzx.code.codegeneration.service;

import com.lzx.code.codegeneration.entity.ClassBean;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 描述: 代码生成业务
 *
 * @Auther: lzx
 * @Date: 2019/7/18 11:50
 */
public interface CodeGenerationService {

    /**
     * 生成entity
     * @return
     */
    String generationEntiry(ClassBean classBean) throws IOException, TemplateException;

    /**
     * 生成dao
     * @param classBean
     * @return
     */
    String generationDao(ClassBean classBean) throws IOException, TemplateException;

    /**
     * 生成service接口
     * @param classBean
     * @return
     */
    String generationService(ClassBean classBean) throws IOException, TemplateException;

    /**
     * 生成service 的实现
     * @param classBean
     * @return
     */
    String generationServiceImpl(ClassBean classBean) throws IOException, TemplateException;

    /**
     * 生成controller
     * @param classBean
     * @return
     */
    String generationController(ClassBean classBean) throws IOException, TemplateException;

}
