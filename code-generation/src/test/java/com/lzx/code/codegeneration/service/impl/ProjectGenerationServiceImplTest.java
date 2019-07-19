package com.lzx.code.codegeneration.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.code.codegeneration.entity.ProjectInfo;
import com.lzx.code.codegeneration.service.ProjectGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/19 10:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProjectGenerationServiceImplTest {

    @Autowired
    private ProjectGenerationServiceImpl projectGenerationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void generationProject() throws IOException {

        ProjectInfo projectInfo = new ProjectInfo(
                "com.lzx",
                "web-demo",
                "0.0.1-SNAPSHOT",
                "web-demo",
                "Demo project for Spring Boot"
        );
        log.info("====报文："+objectMapper.writeValueAsString(projectInfo));
        projectGenerationService.generationProject(projectInfo);

    }
}