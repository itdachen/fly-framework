package com.github.itdachen.framework.webmvc.service;


import com.github.itdachen.framework.core.response.TableData;

import java.util.Map;
import java.util.Objects;

/***
 * 通用 Service
 *
 * @author 王大宸
 * @date 2023/11/15 10:15
 */
public interface IBaseService<T, PK> {

    /***
     * 分页查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:18
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<T>
     */
    TableData<T> page(Map<String, Object> params);

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2023/11/15 10:19
     * @param t t
     * @return T
     */
    T saveInfo(T t) throws Exception;

    /***
     * 根据主键查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:20
     * @param id id
     * @return T
     */
    T selectByPrimaryKey(PK id) throws Exception;

    /***
     * 编辑
     *
     * @author 王大宸
     * @date 2023/11/15 10:20
     * @param t t
     * @return T
     */
    T updateInfo(T t) throws Exception;

    /***
     * 根据主键删除
     *
     * @author 王大宸
     * @date 2023/11/15 10:31
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(PK id) throws Exception;

    /***
     * 根据对象, 查询一条数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:34
     * @param t t
     * @return T
     */
    T selectOne(T t) throws Exception;


}
