package com.github.itdachen.framework.code.utils;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Description: 测试
 * Created by 王大宸 on 2023-06-27 16:20
 * Created with IntelliJ IDEA.
 */
public class DataSourceDocumentUtils {
    /* 文档生产类型 */
    private static final EngineFileType FILE_OUTPUT_TYPE = EngineFileType.HTML;


    public static void main(String[] args) {
        //获取数据源  创建HikariConfig配置类
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/fly-next?useUnicode=true&characterEncoding=UTF8&createDatabaseIfNotExist=true&useUnicode=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&allowMultiQueries=true");
//        hikariConfig.setUsername("root");
//        hikariConfig.setPassword("root");
//
//        //#设置useInformationSchema 可以获取tables表注释信息 即解决数据库表和列字段有说明、生成文档没有说明
//        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
//        hikariConfig.setMinimumIdle(2);
//        hikariConfig.setMaximumPoolSize(5);
//        DataSource dataSource = new HikariDataSource(hikariConfig);

        DataSource dataSource = null;

                //创建screw的引擎配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("d:\\Downloads")
                //打开目录
                .openOutputDir(true)
                //文件类型  HTML->HTML文件  WORD->WORD文件  MD->Markdown文件
                .fileType(FILE_OUTPUT_TYPE)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称，即数据库文档名称
                .fileName("数据库文档名称").build();

        //创建screw的配置:生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                //版本
                .version("V1.0")
                //描述
                .description("生成文档信息描述")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(getProcessConfig())
                .build();
        //执行screw，生成数据库文档
        new DocumentationExecute(config).execute();
    }

    /***
     * 配置想要生成的表、想要忽略的表
     *
     * @author 王大宸
     * @date 2023/6/27 16:21
     * @return cn.smallbun.screw.core.process.ProcessConfig
     */
    private static ProcessConfig getProcessConfig() {
        //创建screw的处理配置，可忽略
        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("files");
        ignoreTableName.add("week_view");

        //忽略表前缀，如忽略test_开头的数据库表
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");

        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");

        // 需要生成数据库文档的表  如果designatedTablePrefix设置值不为空，则 designatedTableName 中的表名要去掉前缀，不然会重复生成，并且表名和字段注释有误
        ArrayList<String> designatedTableName = new ArrayList<>();
//        designatedTableName.add("user");
//        designatedTableName.add("product");
        // 需要生成数据库文档的表前缀
//        ArrayList<String> designatedTablePrefix = new ArrayList<>();
//        designatedTablePrefix.add("t_");

        //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        return ProcessConfig.builder()
                //根据名称指定表生成
//                .designatedTableName(new ArrayList<>())
                .designatedTableName(designatedTableName)
                //根据表前缀生成
//                .designatedTablePrefix(new ArrayList<>())
//                .designatedTablePrefix(designatedTablePrefix)
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }


}
