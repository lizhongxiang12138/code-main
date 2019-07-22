package com.lzx.code.codegeneration.controller;

import com.lzx.code.codegeneration.entity.ProjectFile;
import com.lzx.code.codegeneration.service.ProjectsService;
import com.lzx.common.dto.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述: 项目接口
 *
 * @Auther: lzx
 * @Date: 2019/7/22 15:26
 */
@RestController
@Slf4j
@RequestMapping("/projects")
public class ProjectsController {

    private ProjectsService projectsService;

    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    /**
     * 获取所有的项目文件
     * @return
     */
    @GetMapping("/getAllProjectsFiles")
    ReturnData<List<ProjectFile>> getAllProjectsFiles(){
        return new ReturnData<List<ProjectFile>>(HttpStatus.OK.value(),"success",projectsService.getAllProjectsFiles());
    }

    /**
     * 删除文件
     * @return
     */
    @DeleteMapping("/deleteProjectFiles/{projectName}")
    ReturnData<ProjectFile> deleteProjectFiles(@PathVariable("projectName")String projectName){
        return new ReturnData<ProjectFile>(HttpStatus.OK.value(),"success",projectsService.deleteProjectFile(projectName));
    }

}
