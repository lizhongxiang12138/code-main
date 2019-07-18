package com.lzx.code.codegenerationtemplate.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/18 11:30
 */
@Slf4j
public class TemplateUtilsTest {

    @Test
    public void getTemplatePath() {
        log.info(TemplateUtils.getTemplatePath().getPath());
    }
}