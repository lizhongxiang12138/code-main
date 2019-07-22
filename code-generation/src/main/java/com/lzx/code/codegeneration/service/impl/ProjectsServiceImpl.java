package com.lzx.code.codegeneration.service.impl;

import com.lzx.code.codegeneration.entity.ProjectFile;
import com.lzx.code.codegeneration.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述: 项目业务类实现
 *
 * @Auther: lzx
 * @Date: 2019/7/22 15:19
 */
@Service
@Slf4j
public class ProjectsServiceImpl implements ProjectsService {


    @Override
    public List<ProjectFile> getAllProjectsFiles() {
        URL resource = this.getClass().getResource("/");
        String projectsRoot = resource.getFile()+"/projects/";
        log.info(projectsRoot);

        List<ProjectFile> projectFiles = new ArrayList<>();
        File projectsRootFile = new File(projectsRoot);
        if(projectsRootFile.exists()){
            Arrays.asList(projectsRootFile.listFiles()).forEach(s ->{
                projectFiles.add(new ProjectFile(s.getName(),s.getPath()));
            });
        }

        return projectFiles;
    }

    @Override
    public ProjectFile deleteProjectFile(String projectName) {
        URL resource = this.getClass().getResource("/");
        String projectsNamePath = resource.getFile()+"/projects/"+projectName;
        log.info(projectsNamePath);

        this.delFolder(projectsNamePath);

        return new ProjectFile(projectName,null);
    }

    private void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下所有文件
    private boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
}
