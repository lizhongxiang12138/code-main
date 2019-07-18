/**
 * 描述: ${classDescribe}的api
 *
 * @Auther: lzx
 * @Date: 2019/7/11 17:14
 */
@RestController
@RequestMapping("/${springBeanName}")
public class ${className}Controller extends BaseController<${className}> {

    private ${className}Service ${springBeanName}Service;

    public ${className}Controller(${className}Service ${springBeanName}Service) {
        /*
        !!!!!!!!!!!!!!!!!!!!重点：
        这个时必须的   ·······~~~~~~~~注意哦
        */
        this.baseService = ${springBeanName}Service;
        this.${springBeanName}Service = ${springBeanName}Service;
    }
}