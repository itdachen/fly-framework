package com.github.itdachen.framework.webmvc.entity;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.snowflake.IdUtils;
import com.github.itdachen.framework.webmvc.utils.ClientUtil;
import com.github.itdachen.framework.webmvc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Description: 实体类操作工具
 * Created by 王大宸 on 2022-06-29 10:05
 * Created with IntelliJ IDEA.
 */
public class EntityUtils {

    /**
     * 雪花算法 id 生成
     */
    public static String getId() {
        return IdUtils.getId();
    }

    /**
     * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     * @author Ths Sun
     */
    public static <T> void setCreatAndUpdateInfo(T entity) {
        setCreateInfo(entity);
        setUpdatedInfo(entity);
    }

    /**
     * 快速将bean的crtUser、crtHost、crtTime附上相关值
     *
     * @param entity 实体bean
     * @author Ths Sun
     */
    public static <T> void setCreateInfo(T entity) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String hostIp = StringUtils.defaultIfBlank(request.getHeader("userHost"), ClientUtil.getClientIp(request));

        String[] fields = {"id", "tenantId", "createUser", "createUserId", "createTime", "createHost"};
        Field createTimeField = ReflectionUtils.getAccessibleField(entity, "createTime");
        // 默认值
        Object[] value = null;
        String id = getId();
        if (createTimeField != null && createTimeField.getType().equals(LocalDateTime.class)) {
            value = new Object[]{
                    id,
                    BizContextHandler.getTenantId(),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(),
                    LocalDateTime.now(),
                    hostIp
            };
        }
        if (createTimeField != null && createTimeField.getType().equals(String.class)) {
            value = new Object[]{
                    id,
                    BizContextHandler.getTenantId(),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    hostIp
            };
        }
        if (createTimeField != null && createTimeField.getType().equals(Date.class)) {
            value = new Object[]{
                    id,
                    BizContextHandler.getTenantId(),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(), new Date(),
                    hostIp
            };
        }

        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     * @author Ths Sun
     */
    public static <T> void setUpdatedInfo(T entity) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String hostIp = StringUtils.defaultIfBlank(request.getHeader("userHost"), ClientUtil.getClientIp(request));

        // 默认属性
        String[] fields = {"updateTime", "updateUser", "updateUserId", "updateHost"};
        Field updateTime = ReflectionUtils.getAccessibleField(entity, "updateTime");
        Object[] value = null;
        if (updateTime != null && updateTime.getType().equals(LocalDateTime.class)) {
            value = new Object[]{
                    LocalDateTime.now(),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(),
                    hostIp
            };
        }
        if (updateTime != null && updateTime.getType().equals(String.class)) {
            value = new Object[]{
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(),
                    hostIp
            };
        }
        if (updateTime != null && updateTime.getType().equals(Date.class)) {
            value = new Object[]{
                    new Date(),
                    BizContextHandler.getNickName(),
                    BizContextHandler.getUserId(),
                    hostIp
            };
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value  值数组
     * @author Ths Sun
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (ReflectionUtils.hasField(entity, field)) {
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    /**
     * 根据主键属性，判断主键是否值为空
     *
     * @param entity
     * @param field
     * @return 主键为空，则返回false；主键有值，返回true
     * @author Ths Sun
     * @date 2016年4月28日
     */
    public static <T> boolean isPKNotNull(T entity, String field) {
        if (!ReflectionUtils.hasField(entity, field)) {
            return false;
        }
        Object value = ReflectionUtils.getFieldValue(entity, field);
        return value != null && !"".equals(value);
    }

}
