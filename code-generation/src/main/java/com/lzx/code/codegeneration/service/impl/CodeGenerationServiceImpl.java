package com.lzx.code.codegeneration.service.impl;

import com.lzx.code.codegeneration.entity.ClassBean;
import com.lzx.code.codegeneration.service.CodeGenerationService;
import com.lzx.code.codegenerationtemplate.template.TemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 描述: 代码服务生成业务实现
 *
 * @Auther: lzx
 * @Date: 2019/7/18 13:10
 */
@Service
@Slf4j
public class CodeGenerationServiceImpl implements CodeGenerationService {

    private Configuration cfg;

    public CodeGenerationServiceImpl() throws IOException {
        cfg = TemplateUtils.getConfiguration();
    }

    /**
     * 生成实体类
     * @param classBean
     * @return
     */
    @Override
    public String generationEntiry(ClassBean classBean) throws IOException, TemplateException {
        Template template = cfg.getTemplate("EntityTemple.ftl", "UTF-8");

        //输出
        StringWriter out = new StringWriter();
        template.process(classBean,out);
        return out.toString();
    }

    @Override
    public String generationDao(ClassBean classBean) throws IOException, TemplateException {
        Template template = cfg.getTemplate("Dao.ftl", "UTF-8");

        //输出
        StringWriter out = new StringWriter();
        template.process(classBean,out);
        return out.toString();
    }

    /**
     * 生成业务接口代码
     * @param classBean
     * @return
     */
    @Override
    public String generationService(ClassBean classBean) throws IOException, TemplateException {
        Template template = cfg.getTemplate("Service.ftl", "UTF-8");

        //输出
        StringWriter out = new StringWriter();
        template.process(classBean,out);
        return out.toString();
    }

    /**
     * 生成业务的实现
     * @param classBean
     * @return
     */
    @Override
    public String generationServiceImpl(ClassBean classBean) throws IOException, TemplateException {
        Template template = cfg.getTemplate("ServiceImpl.ftl", "UTF-8");

        //输出
        StringWriter out = new StringWriter();
        template.process(classBean,out);
        return out.toString();
    }

    @Override
    public String generationController(ClassBean classBean) throws IOException, TemplateException {
        Template template = cfg.getTemplate("Controller.ftl", "UTF-8");

        //输出
        StringWriter out = new StringWriter();
        template.process(classBean,out);
        return out.toString();
    }
}
