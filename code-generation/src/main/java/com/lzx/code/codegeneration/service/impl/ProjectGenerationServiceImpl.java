package com.lzx.code.codegeneration.service.impl;

import com.lzx.code.codegeneration.entity.ApplicationJavaInfo;
import com.lzx.code.codegeneration.entity.ProjectInfo;
import com.lzx.code.codegeneration.service.ProjectGenerationService;
import com.lzx.code.codegenerationtemplate.template.TemplateUtils;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/19 10:22
 */
@Service
@Slf4j
public class ProjectGenerationServiceImpl implements ProjectGenerationService {


    /**
     * 生成项目
     * @param projectInfo
     */
    @Override
    public String generationProject(ProjectInfo projectInfo) throws IOException {

        URL resource = this.getClass().getResource("/");
        log.info(resource.getFile());

        String lastPackageName = projectInfo.getArtifactId().replaceAll("-","").toLowerCase();
        //启动类名称
        String[] split = projectInfo.getArtifactId().split("-");
        StringBuffer applicationJavaName = new StringBuffer();
        Arrays.asList(split).forEach(s -> {
            applicationJavaName.append(s.substring(0,1).toUpperCase()+s.substring(1));
        });
        applicationJavaName.append("Application");

        //1、创建  xxxApplication.java
        generationApplicationJava(projectInfo, resource, lastPackageName, applicationJavaName);


        //2、创建配置文件
        generationApplicationYml(projectInfo, resource);

        //3、创建主测试类
        generationApplicationJavaTests(projectInfo, resource, lastPackageName, applicationJavaName);

        //4、生成配置文件
        generationGitignore(projectInfo, resource);

        return projectInfo.getArtifactId();


    }

    /**
     * 下载项目
     * @param projectName
     * @return
     */
    @Override
    public void downloadProject(String projectName, ServletOutputStream os) throws Exception {

        //压缩成zip
        URL resource = this.getClass().getResource("/");
        log.info(resource.getFile());
        ZipOutputStream out = new ZipOutputStream(os);
        File f = new File(resource.getFile() + projectName);
        this.zip(out,f,"");

        //删除文件夹
        this.delFolder(resource.getFile() + projectName);
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

    /**
     * 压缩
     * @param out
     * @param f
     * @param base
     * @throws Exception
     */
    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            //System.out.println(base);
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    private void generationGitignore(ProjectInfo projectInfo, URL resource) throws IOException {
        generationPom(projectInfo, resource);

        //5、生成git提交时忽略的文件配置规则
        File file = new File(
                resource.getFile()
                        + projectInfo.getArtifactId() + "/",
                ".gitignore"
        );
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
        Template template = TemplateUtils.getConfiguration().getTemplate("gitignore.ftl");
        try{
            template.process(null,out);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 生成配置文件
     * @param projectInfo
     * @param resource
     * @throws IOException
     */
    private void generationPom(ProjectInfo projectInfo, URL resource) throws IOException {
        File pomFile = new File(
                resource.getFile()
                        + projectInfo.getArtifactId() + "/",
                "pom.xml"
        );
        if(!pomFile.exists()){
            if(!pomFile.getParentFile().exists()){
                pomFile.getParentFile().mkdirs();
            }
            pomFile.createNewFile();
        }
        OutputStreamWriter pomOut = new OutputStreamWriter(new FileOutputStream(pomFile));
        Template template = TemplateUtils.getConfiguration().getTemplate("pomXml.ftl");
        try{
            template.process(projectInfo,pomOut);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null != pomFile){
                pomOut.flush();
                pomOut.close();
            }
        }
    }

    /**
     * 主测试类生成
     * @param projectInfo
     * @param resource
     * @param lastPackageName
     * @param applicationJavaName
     * @throws IOException
     */
    private void generationApplicationJavaTests(ProjectInfo projectInfo, URL resource, String lastPackageName, StringBuffer applicationJavaName) throws IOException {
        ApplicationJavaInfo applicationJavaInfo = new ApplicationJavaInfo(
                projectInfo.getGroupId() + "." + lastPackageName,
                applicationJavaName.toString()
        );

        Template template = TemplateUtils.getConfiguration().getTemplate("ApplicationJavaTests.ftl", "UTF-8");
        String packagePath = applicationJavaInfo.getPackageName().replace(".", "/")+"/";
        File applicationJavaFile = new File(resource.getFile()
                +projectInfo.getArtifactId()+"/src/test/java/"
                +packagePath, applicationJavaInfo.getClassName() + "Tests.java");
        if(!applicationJavaFile.exists()){
            if(!applicationJavaFile.getParentFile().exists()){
                applicationJavaFile.getParentFile().mkdirs();
            }
            applicationJavaFile.createNewFile();
        }
        OutputStreamWriter applicationJavaTestOut = new OutputStreamWriter(new FileOutputStream(applicationJavaFile));
        try{
            template.process(applicationJavaInfo,applicationJavaTestOut);
            log.info("==生成了主入口类"+applicationJavaFile.getPath());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null!=applicationJavaTestOut){
                applicationJavaTestOut.flush();
                applicationJavaTestOut.close();
            }
        }
    }

    /**
     * 创建配置文件
     * @param projectInfo
     * @param resource
     * @throws IOException
     */
    private void generationApplicationYml(ProjectInfo projectInfo, URL resource) throws IOException {
        Template template = TemplateUtils.getConfiguration().getTemplate("applicationYml.ftl", "UTF-8");
        File applicationYmlFile = new File(
                resource.getFile()
                        + projectInfo.getArtifactId() + "/src/main/resources/",
                "application.yml"
        );
        if(!applicationYmlFile.exists()){
            if(!applicationYmlFile.getParentFile().exists()){
                applicationYmlFile.getParentFile().mkdirs();
            }
            applicationYmlFile.createNewFile();
        }
        OutputStreamWriter applicationYmlOut = new OutputStreamWriter(new FileOutputStream(applicationYmlFile));
        try{
            template.process(null,applicationYmlOut);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null != applicationYmlOut){
                applicationYmlOut.flush();
                applicationYmlOut.close();
            }
        }
    }

    /**
     * 创建  xxxApplication.java
     * @param projectInfo
     * @param resource
     * @param lastPackageName
     * @param applicationJavaName
     * @throws IOException
     */
    private void generationApplicationJava(ProjectInfo projectInfo, URL resource, String lastPackageName, StringBuffer applicationJavaName) throws IOException {
        ApplicationJavaInfo applicationJavaInfo = new ApplicationJavaInfo(
                projectInfo.getGroupId() + "." + lastPackageName,
                applicationJavaName.toString()
        );

        Template template = TemplateUtils.getConfiguration().getTemplate("ApplicationJava.ftl", "UTF-8");
        String packagePath = applicationJavaInfo.getPackageName().replace(".", "/")+"/";
        File applicationJavaFile = new File(resource.getFile()
                +projectInfo.getArtifactId()+"/src/main/java/"
                +packagePath, applicationJavaInfo.getClassName() + ".java");
        if(!applicationJavaFile.exists()){
            if(!applicationJavaFile.getParentFile().exists()){
                applicationJavaFile.getParentFile().mkdirs();
            }
            applicationJavaFile.createNewFile();
        }
        OutputStreamWriter applicationJavaOut = new OutputStreamWriter(new FileOutputStream(applicationJavaFile));
        try{
            template.process(applicationJavaInfo,applicationJavaOut);
            log.info("==生成了主入口类"+applicationJavaFile.getPath());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(null!=applicationJavaOut){
                applicationJavaOut.flush();
                applicationJavaOut.close();
            }
        }
    }
}
