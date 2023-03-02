package com.itdachen.framework.webmvc.service;

import com.itdachen.framework.core.biz.BizQuery;
import com.itdachen.framework.core.exception.BizException;
import com.itdachen.framework.core.response.TableData;

import java.util.List;

/**
 * Description: 通用服务接口
 * T: 实体类对象
 * V: 查询数据返回对象
 * Q: 查询对象, 禁止使用 Map 传输查询参数
 * PK: 实体类对象主键类型
 * Created by 王大宸 on 2022-06-30 9:25
 * Created with IntelliJ IDEA.
 */
public interface IBizService<T, V, Q extends BizQuery, PK> {

    /**
     * 查询所有
     * @return java.util.List<V>
     * @throws Exception
     */
    List<V> list() throws Exception;

    /**
     * 分页查询
     * @param params 查询参数
     * @return com.itdachen.framework.core.response.TableData<V>
     * @throws BizException
     */
    TableData<V> page(Q params) throws Exception;

    /**
     * 新增
     *
     * @param entity 需要新增的数据
     * @return T
     */
    T save(T entity) throws Exception;

    /**
     * 根据id查询数据
     *
     * @param id 根据id查询数据
     * @return T
     */
    T getById(PK id) throws Exception;

    /**
     * 根据id查询数据
     *
     * @param id 根据id查询数据
     * @return V
     */
    V getVoById(PK id) throws Exception;

    /**
     * 更新数据
     *
     * @param entity 需要更新的数据
     * @return T
     */
    T update(T entity) throws Exception;

    /**
     * 删除
     *
     * @param id 需要删除数据的id
     * @return int
     */
    int remove(PK id) throws Exception;

}
