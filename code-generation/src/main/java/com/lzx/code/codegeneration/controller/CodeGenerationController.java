package com.lzx.code.codegeneration.controller;

import com.lzx.code.codegeneration.entity.ClassBean;
import com.lzx.code.codegeneration.service.CodeGenerationService;
import com.lzx.code.codegeneration.vo.DataTypeVO;
import com.lzx.common.dto.ReturnData;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述: 代码生成controller
 *
 * @Auther: lzx
 * @Date: 2019/7/18 14:59
 */
@RestController
@RequestMapping("/codeGeneration")
@Slf4j
public class CodeGenerationController {

    private CodeGenerationService codeGenerationService;

    public CodeGenerationController(CodeGenerationService codeGenerationService) {
        this.codeGenerationService = codeGenerationService;
    }

    private enum CodeName{
        ENTITY,
        DAO,
        SERVICE,
        SERVICE_IMPL,
        CONTROLLER;
    }

    /**
     * 生成代码
     * @param classBean
     * @param codeName
     * @return
     */
    @PostMapping("/generationCode/{codeName}")
    public ReturnData<String> generationCode(@RequestBody ClassBean classBean, @PathVariable("codeName")String codeName) throws IOException, TemplateException {
        CodeName codeNameEnum = null;
        try{
            codeNameEnum = CodeName.valueOf(codeName);
        }catch (Exception e){
            throw  new RuntimeException("文件代码错误");
        }
        String s = null;
        switch (codeNameEnum){
            case ENTITY:{
                s = codeGenerationService.generationEntiry(classBean);
                break;
            }case DAO:{
                s = codeGenerationService.generationDao(classBean);
                break;
            }case SERVICE:{
                s = codeGenerationService.generationService(classBean);
                break;
            }case SERVICE_IMPL:{
                s = codeGenerationService.generationServiceImpl(classBean);
                break;
            }case CONTROLLER:{
                s = codeGenerationService.generationController(classBean);
            }default:{
                break;
            }
        }
        return new ReturnData<String>(HttpStatus.OK.value(),"success",s);
    }


    /**
     * 获取所有数据类型
     * @return
     */
    @GetMapping("/getAllDataType")
    public ReturnData<List<DataTypeVO>> getAllDataType(){
        DataTypeVO.Type[] values = DataTypeVO.Type.values();
        List<DataTypeVO> dataTypeVOS = new ArrayList<>();
        Arrays.asList(values).forEach(f ->{
            dataTypeVOS.add(new DataTypeVO(f.getName(),f.getName()));
        });
        return new ReturnData<List<DataTypeVO>>(HttpStatus.OK.value(),"success",dataTypeVOS);
    }
}
