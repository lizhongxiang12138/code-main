package com.lzx.code.codegeneration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.code.codegeneration.entity.ClassBean;
import com.lzx.code.codegeneration.entity.ClassBeanField;
import com.lzx.code.codegeneration.vo.DataTypeVO;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/18 13:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CodeGenerationServiceImplTest {

    @Autowired
    private CodeGenerationServiceImpl codeGenerationService;

    private ClassBean classBean;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void testBefore() throws JsonProcessingException {
        List<ClassBeanField> fields = new ArrayList<>();
        fields.add(new ClassBeanField(DataTypeVO.Type.STRING.getName(),"name","NAME","名称"));
        fields.add(new ClassBeanField(DataTypeVO.Type.STRING.getName(),"gender","GENDER","性别"));
        fields.add(new ClassBeanField(DataTypeVO.Type.STRING.BIG_DECIMAL.getName(),"age","AGE","分数"));
        classBean = new ClassBean("student","Student", "STUDENT", "学生表", "String", "ID", "id", "主键", fields);
        log.info("======实体的定义："+objectMapper.writeValueAsString(classBean));
    }

    @Test
    public void generationEntiry() throws IOException, TemplateException {
        String s = codeGenerationService.generationEntiry(classBean);
        log.info(s);
    }

    @Test
    public void generationService() throws IOException, TemplateException {
        String s = codeGenerationService.generationService(classBean);
        log.info(s);
    }

    @Test
    public void generationServiceImpl() throws IOException, TemplateException {
        String s = codeGenerationService.generationServiceImpl(classBean);
        log.info(s);
    }

    @Test
    public void generationController() throws IOException, TemplateException {
        String s = codeGenerationService.generationController(classBean);
        log.info(s);
    }

    @Test
    public void generationDao() throws IOException, TemplateException {
        String s = codeGenerationService.generationDao(classBean);
        log.info(s);
    }
}