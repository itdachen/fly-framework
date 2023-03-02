package com.itdachen.framework.webmvc.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description: 通过反射, 生成批量添加和批量更新的 sql 语句 (不建议使用)
 * Created by 王大宸 on 2022-03-14 16:41
 * Created with IntelliJ IDEA.
 */
public class JdbcUtils {
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    //实例化日历
    private static final Calendar calendar = Calendar.getInstance();
    private static final String MONTH_FORMAT = "yyyy-MM";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /***
     * 功能说明：批量添加
     *
     * @author 王大宸
     * @date 2019/3/17 1:19
     * @param list     数据集合
     * @param entity   类
     * @return java.lang.String
     */
    public static <T> String batchSave(List<T> list, Class<T> entity) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /** 拼接sql语句 */
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        /** 获取类名 */
        Class c = Class.forName(entity.getName());
        /** 获取Table注解属性 */
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" (id,");
        /** 获取方法 */
        Field[] fields = c.getDeclaredFields();
        List<String> methods = new ArrayList<>(fields.length);
        methods.add("getId");
        /** 拼接字段 */
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            /** 获取Column注解 */
            Column column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            sb.append(column.name()).append(i == fields.length - 1 ? ") VALUES " : ",");
            methods.add("get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1));
        }
        /** 拼接值 */
        T t;
        Method method;
        String type;
        Object object;
        for (int j = 0; j < list.size(); j++) {
            t = list.get(j);
            sb.append("(");
            for (int i = 0; i < methods.size(); i++) {
                /** 反射get方法 */
                method = t.getClass().getMethod(methods.get(i), null);
                type = method.getAnnotatedReturnType().getType().toString();

                // 如果type是类类型，则前面包含"class "，后面跟类名
                object = method.invoke(t, null);
                if (object == null) {
                    sb.append("null").append(i == methods.size() - 1 ? ")" : ",");
                } else if (type.endsWith("String")) {
                    String value = (String) object;
                    sb.append("'").append(value).append("'").append(i == methods.size() - 1 ? ")" : ",");
                } else if (type.endsWith("Integer")) {
                    Integer value = (Integer) object;
                    sb.append("'").append(value).append("'").append(i == methods.size() - 1 ? ")" : ",");
                } else if (type.endsWith("Boolean")) {
                    Boolean value = (Boolean) object;
                    sb.append(value).append(i == methods.size() - 1 ? ")" : ",");
                } else if (type.endsWith("Date")) {
                    Date value = (Date) object;
                    sb.append("'").append(getStrByDateTime(value)).append("'").append(i == methods.size() - 1 ? ")" : ",");
                } else if (type.endsWith("LocalDateTime")) {
                    LocalDateTime value = (LocalDateTime) object;
                    sb.append("'").append(getStrByDateTime(value)).append("'").append(i == methods.size() - 1 ? ")" : ",");
                } else {
                    sb.append("'").append(object.toString()).append("'").append(i == methods.size() - 1 ? ")" : ",");
                }

            }
            sb.append(j == list.size() - 1 ? "" : ",");
        }
        return sb.toString();
    }

    /***
     * 返回批量更新sql语句
     * 注意：T中必须有Table和Column注解，主键必须名id
     *
     * @author 王大宸
     * @date 2022/6/30 11:10
     * @param list list
     * @param t1 t1
     * @return java.lang.String
     */
    public static <T> String batchUpdateSql(List<T> list, Class<T> t1) throws Exception {
        /** sql语句 */
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        //反射类和注解 */
        Class c = Class.forName(t1.getName());
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" SET ");
        /** 字段 */
        Field[] fields = c.getDeclaredFields();

        Method method;
        String id;
        /** ids */
        List<String> ids = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            method = list.get(i).getClass().getMethod("getId", null);
            id = null == method.invoke(list.get(i), null) ? "" : method.invoke(list.get(i), null).toString();
            ids.add(id);
        }

        Column column;
        String methodName;
        String type;
        Object object;
        /** 拼接set语句 */
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            /** 获取Column注解 */
            column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            sb.append(column.name()).append(" = CASE id ");
            for (int j = 0; j < list.size(); j++) {
                T t = list.get(j);
                /** 反射get方法 */
                methodName = "get" + fields[i].getName().substring(0, 1).toUpperCase() +
                        fields[i].getName().substring(1);
                method = t.getClass().getMethod(methodName, null);
                sb.append(" WHEN '").append(ids.get(j)).append("' THEN ");
                type = method.getAnnotatedReturnType().getType().toString();
                // 如果type是类类型，则前面包含"class "，后面跟类名
                object = method.invoke(t, null);
                //如果值为空则更新为原来的值（不更新）
                if (object == null) {
                    sb.append(table.name()).append(".").append(column.name());
                } else if (type.endsWith("String")) {
                    String value = (String) object;
                    sb.append("'").append(value).append("'");
                } else if (type.endsWith("Integer")) {
                    Integer value = (Integer) object;
                    sb.append("'").append(value).append("'");
                } else if (type.endsWith("Boolean")) {
                    Boolean value = (Boolean) object;
                    sb.append(value);
                } else if (type.endsWith("Date")) {
                    Date value = (Date) object;
                    sb.append("'").append(getStrByDateTime(value)).append("'");
                } else {
                    sb.append("'").append(object.toString()).append("'");
                }
                sb.append(j == list.size() - 1 ? i == fields.length - 1 && j == list.size() - 1 ? " END " : " END, " : " ")
                        .append(i == fields.length - 1 && j == list.size() - 1 ? " WHERE id IN (" : "");
            }
        }
        //后置where语句
        for (int i = 0; i < ids.size(); i++) {
            sb.append("'").append(ids.get(i)).append("'")
                    .append(i == ids.size() - 1 ? ")" : ",");
        }
        return sb.toString();
    }


    /***
     * 功能说明：单个添加
     *
     * Class<T> entity
     *
     * @author 王大宸
     * @date 2019/7/23 18:18
     * @param t   T
     * @param
     * @return java.lang.String
     */
    public static <T> String save(T t, Class<T> entity) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        Class c = Class.forName(entity.getName());
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" (id,");

        Field[] fields = c.getDeclaredFields();
        List<String> methods = new ArrayList<>(fields.length);
        methods.add("getId");
        Column column = null;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            sb.append(column.name()).append(i == fields.length - 1 ? ") VALUES " : ",");
            methods.add("get" + fields[i].getName().substring(0, 1).toUpperCase() +
                    fields[i].getName().substring(1));
        }
        sb.append("(");
        Method method;
        String type = null;
        Object object = null;
        for (int i = 0; i < methods.size(); i++) {
            method = t.getClass().getMethod(methods.get(i), null);
            type = method.getAnnotatedReturnType().getType().toString();
            // 如果type是类类型，则前面包含"class "，后面跟类名
            object = method.invoke(t);
            if (object == null) {
                sb.append("null").append(i == methods.size() - 1 ? ")" : ",");
            } else if (type.endsWith("String")) {
                String value = (String) object;
                sb.append("'").append(value).append("'").append(i == methods.size() - 1 ? ")" : ",");
            } else if (type.endsWith("Integer")) {
                Integer value = (Integer) object;
                sb.append("'").append(value).append("'").append(i == methods.size() - 1 ? ")" : ",");
            } else if (type.endsWith("Boolean")) {
                Boolean value = (Boolean) object;
                sb.append(value).append(i == methods.size() - 1 ? ")" : ",");
            } else if (type.endsWith("Date")) {
                Date value = (Date) object;
                sb.append("'").append(getStrByDateTime(value)).append("'").append(i == methods.size() - 1 ? ")" : ",");
            } else if (type.endsWith("LocalDateTime")) {
                LocalDateTime value = (LocalDateTime) object;
                sb.append("'").append(getStrByDateTime(value)).append("'").append(i == methods.size() - 1 ? ")" : ",");
            } else {
                sb.append("'").append(object.toString()).append("'").append(i == methods.size() - 1 ? ")" : ",");
            }
        }
        return sb.toString();
    }

    /***
     * 功能说明：  jdbc 修改
     *
     *  字段为 null 的不更新
     *
     * @author 王大宸
     * @date 2019/7/23 15:50
     * @param t       T
     * @param entity  类对象
     * @return java.lang.String
     */
    public static <T> String update(T t, Class<T> entity) throws Exception {
        StringBuffer sb = new StringBuffer();
        Class c = Class.forName(entity.getName());
        sb.append("UPDATE ");
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" SET ");

        /* 获取方法 */
        Field[] fields = c.getDeclaredFields();
        List<String> methods = new ArrayList<>(fields.length);
        methods.add("getId");
        Method method;

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Column column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }

            methods.add("get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1));
            method = t.getClass().getMethod("get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1), null);
            String type = method.getAnnotatedReturnType().getType().toString();
            Object object = method.invoke(t, null);
            if (null == object) {
                //  sb.append("null").append(i == fields.length - 1 ? " " : ",");
                //  如果是 null ,不更新该字段
                if (i == fields.length - 1) {
                    sb.replace(sb.length() - 1, sb.length(), " ");
                }
                continue;
            }

            sb.append(column.name() + " = ");

            if (type.endsWith("String")) {
                String value = (String) object;
                sb.append("'").append(value).append("'").append(i == fields.length - 1 ? " " : ",");
                continue;
            }
            if (type.endsWith("Integer")) {
                Integer value = (Integer) object;
                sb.append("'").append(value).append("'").append(i == fields.length - 1 ? " " : ",");
                continue;
            }
            if (type.endsWith("Boolean")) {
                Boolean value = (Boolean) object;
                sb.append("'").append(value).append("'").append(i == fields.length - 1 ? " " : ",");
                continue;
            }
            if (type.endsWith("LocalDateTime")) {
                LocalDateTime value = (LocalDateTime) object;
                sb.append("'").append(getStrByDateTime(value)).append("'").append(i == fields.length - 1 ? " " : ",");
            }
            if (type.endsWith("Date")) {
                Date value = (Date) object;
                sb.append("'").append(getStrByDateTime(value)).append("'").append(i == fields.length - 1 ? " " : ",");
            } else {
                sb.append("'").append(object.toString()).append("'").append(i == fields.length - 1 ? " " : ",");
            }
        }

        // 获取 id 的值
        // Method m = t.getClass().getMethod("getId");
        // 转成自己想要的类型
        // Integer id = (Integer)m.invoke(t);

        sb.append("WHERE id = '" + t.getClass().getMethod("getId").invoke(t) + "'");
        return sb.toString();
    }

    /***
     * 功能说明：jdbc 根据id删除
     *
     * @author 王大宸
     * @date 2019/7/23 15:40
     * @param id      需要删除数据的id
     * @param entity  类对象
     * @return java.lang.String
     */
    public static <T> String remove(String id, Class<T> entity) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ");
        Class c = Class.forName(entity.getName());
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" WHERE id = '" + id + "'");
        return sb.toString();
    }

    /***
     * 功能说明：jdbc 根据id查询
     *
     * @author 王大宸
     * @date 2019/7/23 18:50
     * @param id       需要查询数据的 id
     * @param entity   类对象
     * @return java.lang.String
     */
    public static <T> String getById(String id, Class<T> entity) throws Exception {
        StringBuffer sb = new StringBuffer();
        Class c = Class.forName(entity.getName());
        sb.append("SELECT id,");
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Column column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            if (!column.name().contains("_")) {
                sb.append(column.name()).append(i == fields.length - 1 ? " " : ",");
            }
            if (column.name().contains("_")) {
                sb.append(column.name()).append(" AS ").append(camelMap(column.name())).append(i == fields.length - 1 ? " " : ",");
            }
        }
        sb.append(" FROM ");
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name()).append(" WHERE id = '" + id + "'");
        return sb.toString();
    }

    /***
     * 功能说明：根据 code 查询数据
     *
     * @author 王大宸
     * @date 2019-7-24 11:53
     * @param code     需要查询数据的 code
     * @param entity   类对象
     * @return java.lang.String
     */
    public static <T> String getEntityByCode(String code, Class<T> entity) throws Exception {
        return listAll(entity) + " WHERE code = '" + code + "'";
    }

    public static <T> String page(Class<T> entity) throws Exception {
        return listAll(entity) + " ORDER BY id  DESC";
    }

    /***
     * 功能说明：查询所有
     *
     * @author 王大宸
     * @date 2019-7-24 15:44
     * @param entity  类对象
     * @return java.lang.String
     */
    public static <T> String listAll(Class<T> entity) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id,");
        Class c = Class.forName(entity.getName());
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Column column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            if (!column.name().contains("_")) {
                sb.append(column.name()).append(i == fields.length - 1 ? " " : ",");
            }
            if (column.name().contains("_")) {
                sb.append(column.name()).append(" AS ").append(camelMap(column.name())).append(i == fields.length - 1 ? " " : ",");
            }
        }
        sb.append("FROM ");
        Table table = (Table) c.getAnnotation(Table.class);
        sb.append(table.name());
        return sb.toString();
    }

    /***
     * 功能说明： 分页
     *
     * @author 王大宸
     * @date 2019/7/23 22:40
     * @param entity       类对象
     * @param fieldName    字段名称
     * @param fieldValue   字段值
     * @param page         页码
     * @param pageSize     条数
     * @return java.lang.String
     */
    public static <T> String querySql(Class<T> entity, String fieldName, String fieldValue, Integer page, Integer pageSize) throws ClassNotFoundException {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id,");
        Class c = Class.forName(entity.getName());
        Field[] fields = c.getDeclaredFields();
        String value = null;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Column column = fields[i].getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            if (!column.name().contains("_")) {
                sb.append(column.name()).append(i == fields.length - 1 ? " " : ",");
            }
            if (column.name().contains("_")) {
                sb.append(column.name()).append(" AS ").append(camelMap(column.name())).append(i == fields.length - 1 ? " " : ",");
            }
            if (StringUtils.equals(fields[i].getName(), fieldName)) {
                value = column.name();
            }
        }
        if (null == value) {
            logger.error("生成分页sql语句失败：" + fieldName + " 属性未找到");
            return null;
        }
        Table table = entity.getAnnotation(Table.class);
        sb.append(" FROM ").append(table.name());
        if (StringUtils.isNotEmpty(fieldValue)) {
            sb.append(" WHERE ").append(value).append(" LIKE '%").append(fieldValue).append("%'");
        }
        /* 分页 */
        if (null != page && null != pageSize) {
            sb.append(" LIMIT ").append((page - 1) * pageSize).append(",").append(pageSize);
        }
        return sb.toString();
    }

    /***
     * 功能说明：查询分页总条数
     *
     * @author 王大宸
     * @date 2019-7-24 16:40
     * @param entity       类对象
     * @param fieldName    字段名称
     * @param fieldValue   字段值
     * @return java.lang.String
     */
    public static <T> String querySqlCount(Class<T> entity, String fieldName, String fieldValue) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT COUNT(id)");
        Table table = entity.getAnnotation(Table.class);
        sb.append(" FROM ").append(table.name());
        if (StringUtils.isNotEmpty(fieldValue)) {
            sb.append(" WHERE ").append(fieldName).append(" LIKE '%").append(fieldValue).append("%'");
        }
        return sb.toString();
    }

    /***
     * 功能说明： 下划线转驼峰
     *
     * @author 王大宸
     * @date 2019/7/23 19:31
     * @param inputString  带下划线的字段
     * @return java.lang.String
     */
    public static String camelMap(String inputString) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char charAt = inputString.charAt(i);
            if (charAt == '_') {
                if (stringBuilder.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    stringBuilder.append(Character.toUpperCase(charAt));
                    nextUpperCase = false;
                } else {
                    stringBuilder.append(Character.toLowerCase(charAt));
                }
            }
        }
        return stringBuilder.toString();
    }


    /***
     * 功能说明：时间转成 String 类型
     *
     * @author 王大宸
     * @date 2019/4/11 20:21
     * @param date 时间
     * @return java.lang.String
     */
    public static String getStrByDateTime(LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return df.format(date);
    }


    /***
     * 功能说明：时间转成 String 类型
     *
     * @author 王大宸
     * @date 2019/7/23 21:53
     * @param date
     * @return java.lang.String
     */
    public static String getStrByDateTime(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        return df.format(date);
    }

}
