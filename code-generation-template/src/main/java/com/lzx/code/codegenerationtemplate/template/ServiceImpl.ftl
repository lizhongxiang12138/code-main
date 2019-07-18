/**
 * 描述:${classDescribe}业务接口实现
 *
 * @Auther: lzx
 * @Date: 2019/7/11 17:09
 */
@Service
@Slf4j
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {

    private ${className}Dao ${springBeanName}Dao;

    public ${className}ServiceImpl(${className}Dao ${springBeanName}Dao) {
        /*
        !!!!!!!!!!!!!!!!!!!!重点：
        这个时必须的   ·······~~~~~~~~注意哦
        */
        this.dao = ${springBeanName}Dao;
        this.${springBeanName}Dao = ${springBeanName}Dao;
    }

    /**
    * 扩展基类中没有的查询条件自己的查询条件
    * @return
    */
    @Override
    public List<Predicate> createPredicateList() {
        return super.createPredicateList();
    }
}