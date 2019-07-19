package com.lzx.code.codegeneration.controller;

import com.lzx.code.codegeneration.entity.ProjectInfo;
import com.lzx.code.codegeneration.service.ProjectGenerationService;
import com.lzx.common.dto.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述: 项目生成Controller
 *
 * @Auther: lzx
 * @Date: 2019/7/19 14:28
 */
@RestController
@Slf4j
@RequestMapping("/projectGeneration")
public class ProjectGenerationController {

    private ProjectGenerationService projectGenerationService;

    public ProjectGenerationController(ProjectGenerationService projectGenerationService) {
        this.projectGenerationService = projectGenerationService;
    }

    /**
     * 创建项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/generationProject")
    public ReturnData<String> generationProject(@RequestBody ProjectInfo projectInfo) throws IOException {
        String s = projectGenerationService.generationProject(projectInfo);
        return new ReturnData<String>(HttpStatus.OK.value(),"success",s);
    }

    /**
     * 下载项目
     */
    @GetMapping("/downloadProject/{projectName}")
    public void downloadProject(@PathVariable("projectName")String projectName, HttpServletResponse response) throws IOException {
        ServletOutputStream os = null;
        try{
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.zip)
            String fileName = new String((projectName+".zip").getBytes("UTF-8"),
                    "iso8859-1");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + fileName);
            os = response.getOutputStream();
            projectGenerationService.downloadProject(projectName,os);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null != os){
                os.flush();
                os.close();
            }
        }
    }
}
