package com.github.itdachen.framework.code.utils;

import com.github.itdachen.framework.code.constants.PackageNameConstant;
import com.github.itdachen.framework.code.constants.UiStyleConstant;
import com.github.itdachen.framework.code.sdk.vo.TableColumnVo;
import com.github.itdachen.framework.code.sdk.vo.TableInfoVo;
import com.github.itdachen.framework.core.utils.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Description: 模板工具类
 * Created by 王大宸 on 2022-07-27 23:45
 * Created with IntelliJ IDEA.
 */
public class VelocityUtils {
    private static final Snowflake snowflake = new Snowflake(1, 1, 0L);


    /**
     * 默认上级菜单，系统工具
     */
    private static final String DEFAULT_PARENT_MENU_ID = "1539506077102641152";

    public static String getId() {
        return String.valueOf(snowflake.nextId());
    }

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(TableInfoVo tableInfo) {
        String packageName = tableInfo.getPackageName();
        String functionName = tableInfo.getFunctionName();
        String menuUri = menuUri(tableInfo);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("clientId", StringUtils.isEmpty(tableInfo.getClientId()) ? "admin" : tableInfo.getClientId());
        velocityContext.put("iframe", StringUtils.isEmpty(tableInfo.getIframe()) ? "iframe" : tableInfo.getIframe());
        velocityContext.put("tplCategory", tableInfo.getTplCategory());
        velocityContext.put("tableName", tableInfo.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");

        velocityContext.put("moduleName", tableInfo.getModuleName());
        velocityContext.put("BusinessName", StringUtils.capitalize(tableInfo.getBusinessName()));
        velocityContext.put("businessName", tableInfo.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        velocityContext.put("pkColumn", tableInfo.getPkColumn());
        velocityContext.put("importList", getImportList(tableInfo));

        velocityContext.put("table", tableInfo);
        velocityContext.put("package", tableInfo.getPackageName());
        velocityContext.put("contextPath", contextPathHandler(tableInfo.getContextPath()));
        velocityContext.put("menuUrl", menuUri);
        velocityContext.put("pathPrefix", null == menuUri || "".equals(menuUri) ? "admin" : menuUri.substring(1));
        velocityContext.put("author", tableInfo.getFunctionAuthor() == null || "".equals(tableInfo.getFunctionAuthor()) ? "<请填写作者名称>" : tableInfo.getFunctionAuthor());
        velocityContext.put("comments", tableInfo.getTableComment());
        velocityContext.put("functionName", tableInfo.getFunctionName());
        velocityContext.put("ClassName", tableInfo.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(tableInfo.getClassName()));
        velocityContext.put("primaryKey", tableInfo.getPkColumn().getJavaType());
        velocityContext.put("permsPrefix", permsPrefix(tableInfo));

        /* 实体类字段 */
        velocityContext.put("columns", tableInfo.getColumns());
        velocityContext.put("queryColumns", queryColumnName(tableInfo.getColumns()));
        velocityContext.put("dtoVoColumns", dtoVoColumnName(tableInfo.getColumns()));

        /* 菜单 */
        velocityContext.put("pMenuId", tableInfo.getMenuId() == null || "".equals(tableInfo.getMenuId()) ? DEFAULT_PARENT_MENU_ID : tableInfo.getMenuId());
        velocityContext.put("menuId", getId());
        velocityContext.put("pageId", getId());
        velocityContext.put("addId", getId());
        velocityContext.put("editId", getId());
        velocityContext.put("seeId", getId());
        velocityContext.put("delId", getId());

        return velocityContext;
    }





    /***
     * 获取模板信息
     *
     * @author 王大宸
     * @date 2022/7/27 23:55
     * @param mvcType mvcType
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getTemplateList(String mvcType, String uiStyle) {
        List<String> templates = new ArrayList<String>();
        /* sdk */
        templates.add("templates/vmtemplates/java/sdk/dto/dto.java.vm");
        templates.add("templates/vmtemplates/java/sdk/vo/vo.java.vm");
        templates.add("templates/vmtemplates/java/sdk/query/query.java.vm");

        /* 后端 java */
        templates.add("templates/vmtemplates/java/" + mvcType + "/entity/entity.java.vm");
        templates.add("templates/vmtemplates/java/" + mvcType + "/controller/controller.java.vm");
        templates.add("templates/vmtemplates/java/" + mvcType + "/service/service.java.vm");
        templates.add("templates/vmtemplates/java/" + mvcType + "/service/impl/serviceImpl.java.vm");
        templates.add("templates/vmtemplates/java/" + mvcType + "/mapper/mapper.java.vm");
        /* mybatis xml */
        templates.add("templates/vmtemplates/java/" + mvcType + "/xml/mapper.xml.vm");

        /* 菜单 sql */
        templates.add("templates/vmtemplates/sql/menu.sql.vm");

        if (UiStyleConstant.NONE.equals(uiStyle) || "".equals(uiStyle)) {
            return templates;
        }

        if (UiStyleConstant.LAY_UI.equals(uiStyle)) {
            templates.add("templates/vmtemplates/html/add.html.vm");
            templates.add("templates/vmtemplates/html/edit.html.vm");
            templates.add("templates/vmtemplates/html/index.html.vm");
            templates.add("templates/vmtemplates/html/view.html.vm");
            templates.add("templates/vmtemplates/js/add.js.vm");
            templates.add("templates/vmtemplates/js/edit.js.vm");
            templates.add("templates/vmtemplates/js/index.js.vm");
        } else if (UiStyleConstant.VUE_ELEMENT.equals(uiStyle)) {
            /* 前后端分离 vue */
            templates.add("templates/vmtemplates/vue/api.ts.vm");
            templates.add("templates/vmtemplates/vue/model.ts.vm");
            templates.add("templates/vmtemplates/vue/composables.ts.vm");
            templates.add("templates/vmtemplates/vue/index.vue.vm");
            templates.add("templates/vmtemplates/vue/ref.vue.vm");
        } else if (UiStyleConstant.VUE_LAY_UI.equals(uiStyle)) {
            /* 前后端分离 vue */
            templates.add("templates/vmtemplates/vue/api.ts.vm");
            templates.add("templates/vmtemplates/vue/model.ts.vm");
            templates.add("templates/vmtemplates/vue/composables.ts.vm");
            templates.add("templates/vmtemplates/vue/index.vue.vm");
            templates.add("templates/vmtemplates/vue/ref.vue.vm");
        }

        return templates;
    }



    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    private static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        return StringUtils.substring(packageName, 0, lastIndex);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    private static HashSet<String> getImportList(TableInfoVo genTable) {
        List<TableColumnVo> columns = genTable.getColumns();
        HashSet<String> importList = new HashSet<String>();
        for (TableColumnVo column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * http 请求 url, 区别是否有上下文
     *
     * @param tableInfo
     * @return
     */
    private static String httpUri(TableInfoVo tableInfo) {
        String uri = "";
        if (StringUtils.isNotEmpty(tableInfo.getModuleName().replaceAll(" ", "").replaceAll("/", ""))) {
            uri += tableInfo.getModuleName();
        }
        if (StringUtils.isNotEmpty(tableInfo.getBusinessName())) {
            uri += tableInfo.getBusinessName();
        }
        return uri;
    }

    /***
     * Controller 请求路径处理
     *
     * @author 王大宸
     * @date 2022/8/5 16:02
     * @param tableInfo tableInfo
     * @return java.lang.String
     */
    public static String menuUri(TableInfoVo tableInfo) {
        String uri = "";
        if (StringUtils.isNotEmpty(tableInfo.getBusinessName())) {
            uri += tableInfo.getBusinessName();
        }
        return uri;
    }

    /***
     * 权限标识前缀
     *
     * @author 王大宸
     * @date 2022/8/1 16:38
     * @param tableInfo tableInfo
     * @return java.lang.String
     */
    private static String permsPrefix(TableInfoVo tableInfo) {
        String permsPrefix = "";
        if (tableInfo.getModuleName().equals(tableInfo.getBusinessName())) {
            tableInfo.setModuleName(null);
        }
        if (StringUtils.isNotEmpty(tableInfo.getModuleName()) && !"/".equals(tableInfo.getModuleName()) && !":".equals(tableInfo.getModuleName())) {
            permsPrefix += trimStr(tableInfo.getModuleName()) + ":";
        }
        if (StringUtils.isNotEmpty(tableInfo.getBusinessName()) && !"/".equals(tableInfo.getBusinessName()) && !":".equals(tableInfo.getBusinessName())) {
            permsPrefix += trimStr(tableInfo.getBusinessName()) + ":";
        }
        permsPrefix = permsPrefix.substring(0, permsPrefix.length() - 1).replaceAll("/", ":");
        return permsPrefix.replaceAll("::", ":").replaceAll(" ", "");
    }

    /***
     * 获取 dto/vo 属性的字段
     *
     * @author 王大宸
     * @date 2023/3/20 14:15
     * @param columns columns
     * @return java.util.List<cn.edu.hubu.tools.sdk.vo.TableColumnVo>
     */
    private static List<TableColumnVo> dtoVoColumnName(List<TableColumnVo> columns) {
        List<TableColumnVo> list = new ArrayList<>();
        for (TableColumnVo column : columns) {
            if (GenConstants.REQUIRE.equals(column.getIsDtoVo())) {
                list.add(column);
            }
        }
        return list;
    }

    /***
     * 获取 查询/query 属性的字段
     *
     * @author 王大宸
     * @date 2023/3/20 14:57
     * @param columns columns
     * @return java.util.List<cn.edu.hubu.tools.sdk.vo.TableColumnVo>
     */
    private static List<TableColumnVo> queryColumnName(List<TableColumnVo> columns) {
        List<TableColumnVo> list = new ArrayList<>();
        for (TableColumnVo column : columns) {
            if (GenConstants.REQUIRE.equals(column.getIsQuery())) {
                list.add(column);
            }
        }
        return list;
    }

    private static String trimStr(String str) {
        return str.replaceAll("/", ":").replaceAll("\"", "").replaceAll(" ", "").trim();
    }

    private static String contextPathHandler(String contextPath) {
        if (null == contextPath || "".equals(contextPath)) {
            return "";
        }
        if (contextPath.startsWith("/")) {
            return contextPath;
        }
        return "/" + contextPath;
    }
}
