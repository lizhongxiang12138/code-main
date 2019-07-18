/**
 * 描述: ${classDescribe}dao接口
 *
 * @Auther: lzx
 * @Date: 2019/7/11 15:52
 */
@RepositoryRestResource(path = "${springBeanName}")
public interface ${className}Dao extends JpaRepository<${className},${idType}>,JpaSpecificationExecutor<${className}> {
}