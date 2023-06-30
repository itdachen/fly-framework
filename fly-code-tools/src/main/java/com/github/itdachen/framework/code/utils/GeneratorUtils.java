package com.github.itdachen.framework.code.utils;

import com.github.itdachen.framework.code.constants.PackageNameConstant;
import com.github.itdachen.framework.code.sdk.vo.TableInfoVo;
import com.github.itdachen.framework.core.constants.Constants;
import com.github.itdachen.framework.core.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description:
 * Created by 王大宸 on 2022-06-20 16:57
 * Created with IntelliJ IDEA.
 */
public class GeneratorUtils {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);
    private static final Snowflake snowflake = new Snowflake(1, 1, 0L);
    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 基础包目录
     */
    public static final String zipName = PackageNameConstant.PACKAGE_NAME;

    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * 资源空间路径
     */
    private static final String RESOURCES = "main/resources";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = RESOURCES + "/mapper";

    public static String getId() {
        return String.valueOf(snowflake.nextId());
    }

//    public static void main(String[] args) {
//        String s = "sysUser";
//        System.err.println("tableToJava: " + toCamelCase("sys_user"));
//        System.err.println(getPermissionPrefix("base", s));
//    }


    /**
     * 列名转换成 Java 属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成 Java 类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /***
     * 获取权限前缀
     *
     * @author 王大宸
     * @date 2020/11/12 0:22
     * @param moduleName      模块名称
     * @param clazzName       java 类名
     * @return java.lang.String
     */
    public static String getPermissionPrefix(String moduleName, String clazzName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(moduleName)) {
            stringBuilder.append(moduleName).append(":");
        }
        for (int i = 0; i < clazzName.length(); i++) {
            char c = clazzName.charAt(i);
            if (Character.isUpperCase(c)) {
                stringBuilder.append(":").append(Character.toLowerCase(c));
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void generatorCode(TableInfoVo tableInfoVo, ZipOutputStream zip) {
        PkColumnUtils.setPkColumn(tableInfoVo);
        VelocityContext context = VelocityUtils.prepareContext(tableInfoVo);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(tableInfoVo.getTplCategory(), tableInfoVo.getUiStyle());
        StringWriter sw;
        Template tpl;
        for (String template : templates) {
            // 渲染模板
            sw = new StringWriter();
            tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableInfoVo)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                logger.error("渲染模板失败，表名：" + tableInfoVo.getTableName(), e);
            }
        }
    }

    /**
     * 获取文件名
     */
    private static String getFileName(String template, TableInfoVo genTable) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        String vueModuleName = genTable.getModuleName();
        if ("admin".equals(vueModuleName)) {
            vueModuleName = packageName.substring(packageName.lastIndexOf(".") + 1);
        }
        // 大写类名
        String className = genTable.getClassName();
        String menuUri = VelocityUtils.menuUri(genTable);

        String javaPath = zipName + "/" + PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = zipName + "/" + MYBATIS_PATH + "/" + packageName.substring(packageName.lastIndexOf(".") + 1);
        String vuePath = zipName + "/" + "vue";

        if (template.contains("entity.java.vm")) {
            fileName = StringUtils.format("{}/entity/{}.java", javaPath, className);
        } else if (template.contains("dto.java.vm")) {
            fileName = StringUtils.format("{}/sdk/dto/{}DTO.java", javaPath, className);
        } else if (template.contains("vo.java.vm")) {
            fileName = StringUtils.format("{}/sdk/vo/{}VO.java", javaPath, className);
        } else if (template.contains("query.java.vm")) {
            fileName = StringUtils.format("{}/sdk/query/{}Query.java", javaPath, className);
        } else if (template.contains("convert.java.vm")) {
            fileName = StringUtils.format("{}/convert/{}Convert.java", javaPath, className);
        } else if (template.contains("IConvert.java.vm")) {
            fileName = StringUtils.format("{}/convert/I{}Convert.java" , javaPath, className);
        }    else if (template.contains("convertImpl.java.vm")) {
            fileName = StringUtils.format("{}/convert/impl/{}ConvertImpl.java" , javaPath, className);
        } else if (template.contains("mapper.java.vm")) {
            fileName = StringUtils.format("{}/mapper/I{}Mapper.java", javaPath, className);
        } else if (template.contains("service.java.vm")) {
            fileName = StringUtils.format("{}/service/I{}Service.java", javaPath, className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        } else if (template.contains("controller.java.vm")) {
            fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
        } else if (template.contains("mapper.xml.vm")) {
            fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        } else if (template.contains("menu.sql.vm")) {
            fileName = StringUtils.format("{}/sql/{}Menu.sql", zipName + "/" + RESOURCES, className);
        } else if (template.contains("add.html.vm")) {
            fileName = StringUtils.format("{}/templates/{}/add.html", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("edit.html.vm")) {
            fileName = StringUtils.format("{}/templates/{}/edit.html", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("index.html.vm")) {
            fileName = StringUtils.format("{}/templates/{}/index.html", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("view.html.vm")) {
            fileName = StringUtils.format("{}/templates/{}/view.html", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("add.js.vm")) {
            fileName = StringUtils.format("{}/static/{}/add.js", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("edit.js.vm")) {
            fileName = StringUtils.format("{}/static/{}/edit.js", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("index.js.vm")) {
            fileName = StringUtils.format("{}/static/{}/index.js", zipName + "/" + RESOURCES, menuUri);
        } else if (template.contains("model.ts.vm")) {
            fileName = StringUtils.format("{}/api/{}/model/{}.ts", vuePath, vueModuleName, className + "Model");
        } else if (template.contains("api.ts.vm")) {
            fileName = StringUtils.format("{}/api/{}/{}.ts", vuePath, vueModuleName, className + "Api");
        } else if (template.contains("composables.ts.vm")) {
            fileName = StringUtils.format("{}/composables/{}/{}.ts", vuePath, vueModuleName, className + "Composable");
        } else if (template.contains("index.vue.vm")) {
            fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, vueModuleName, className.toLowerCase());
        } else if (template.contains("ref.vue.vm")) {
            fileName = StringUtils.format("{}/views/{}/{}/{}.vue", vuePath, vueModuleName, className, "Ref" + className);
        }
        return fileName;
    }

}
