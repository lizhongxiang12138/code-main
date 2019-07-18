package com.lzx.code.codegeneration.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 数据类型 视图模型
 *
 * @Auther: lzx
 * @Date: 2019/7/18 18:22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataTypeVO {

    private String name;
    private String code;

    @AllArgsConstructor
    @Getter
    public enum Type{
        STRING("String","String"),
        BIG_DECIMAL("BigDecimal","BigDecimal"),
        LONG("Long","Long");
        private String name;
        private String dec;
    }

}
