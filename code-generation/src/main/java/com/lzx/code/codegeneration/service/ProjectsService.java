package com.lzx.code.codegeneration.service;

import com.lzx.code.codegeneration.entity.ProjectFile;

import java.util.List;

/**
 * 描述: 项目业务类
 *
 * @Auther: lzx
 * @Date: 2019/7/22 15:14
 */
public interface ProjectsService {

    /**
     * 获取所有项目
     * @return
     */
    List<ProjectFile> getAllProjectsFiles();


    /**
     * 删除项目
     * @param projectName
     * @return
     */
    ProjectFile deleteProjectFile(String projectName);

}
