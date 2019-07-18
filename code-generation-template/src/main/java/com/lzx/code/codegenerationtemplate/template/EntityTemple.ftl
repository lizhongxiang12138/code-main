/**
 * 描述: ${classDescribe}
 *
 * @Auther: lzx
 * @Date: 2019/7/11 15:39
 */
@Entity(name = "${tableName}")
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className} {

    /**
     * ${idDescribe}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "${idColumnName}",length = 32)
    private ${idType} ${idName};

    <#list fields as f>
    /**
     * ${f.describe}
     */
    @Column(name = "${f.columnName}",length = 100)
    private ${f.type} ${f.name};
    </#list>
}
