package com.lzx.code.codegeneration.service;

import com.lzx.code.codegeneration.entity.ProjectInfo;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 描述: 项目生成业务
 *
 * @Auther: lzx
 * @Date: 2019/7/19 10:11
 */
public interface ProjectGenerationService {

    /**
     * 生成项目
     * @param projectInfo
     */
    String generationProject(ProjectInfo projectInfo) throws IOException;


    /**
     * 下载项目
     * @param projectName
     * @return
     */
    void downloadProject(String projectName, ServletOutputStream os) throws Exception;

}
