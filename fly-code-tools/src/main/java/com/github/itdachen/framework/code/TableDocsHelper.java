package com.github.itdachen.framework.code;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.github.itdachen.framework.code.entity.TableDocsInfo;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Description: 数据库表文档
 * Created by 王大宸 on 2023-06-27 16:33
 * Created with IntelliJ IDEA.
 */
public class TableDocsHelper {

    /***
     * 生产文档
     *
     * @author 王大宸
     * @date 2023/6/27 17:06
     * @param docsInfo docsInfo
     * @param dataSource dataSource
     * @return void
     */
    public static void document(TableDocsInfo docsInfo,
                                DataSource dataSource) {
        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir(docsInfo.getFileOutputDir())
                // 生成之后是否打开目录
                .openOutputDir(docsInfo.getOpenOutputDir())
                // 文件类型: WORD, HTML, MD
                .fileType(docsInfo.getFileType())
                //  .fileType(EngineFileType.HTML)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName(docsInfo.getFileName())
                .build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version(docsInfo.getVersion())
                .description(docsInfo.getDescription())
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig(docsInfo))
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /***
     * 配置想要生成的表、想要忽略的表
     *
     * @author 王大宸
     * @date 2023/6/27 16:21
     * @return cn.smallbun.screw.core.process.ProcessConfig
     */
    private static ProcessConfig getProcessConfig(TableDocsInfo docsInfo) {
        //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(docsInfo.getIgnoreTables())
                //忽略表前缀
                .ignoreTablePrefix(docsInfo.getIgnorePrefixTables())
                //忽略表后缀
                .ignoreTableSuffix(docsInfo.getIgnoreSuffixTables())
                .build();
    }


}
