package com.lzx.code.codegenerationtemplate.template;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 描述: 模板工具
 *
 * @Auther: lzx
 * @Date: 2019/7/18 11:24
 */
public class TemplateUtils {

    /**
     * freemarker 配置
     */
    private static Configuration cfg;

    /**
     * 获取模板跟地址
     * @return
     */
    public static URL getTemplatePath(){
        URL resource = TemplateUtils.class.getResource("");
        return resource;
    }

    /**
     * 获取 freemarker 配置
     * @return
     * @throws IOException
     */
    public static Configuration getConfiguration() throws IOException {
        if(null == cfg){
            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(getTemplatePath().getPath()));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        }
        return cfg;
    }
}
