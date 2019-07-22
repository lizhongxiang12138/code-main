package com.lzx.code.codegeneration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.code.codegeneration.entity.ProjectFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/22 15:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProjectsServiceImplTest {

    @Autowired
    private ProjectsServiceImpl projectsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProjectsFiles() throws JsonProcessingException {
        List<ProjectFile> allProjectsFiles = projectsService.getAllProjectsFiles();
        log.info(objectMapper.writeValueAsString(allProjectsFiles));
    }
}