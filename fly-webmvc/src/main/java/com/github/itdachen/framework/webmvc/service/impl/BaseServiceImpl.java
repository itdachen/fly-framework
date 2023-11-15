package com.github.itdachen.framework.webmvc.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.IBaseService;
import com.github.itdachen.framework.webmvc.utils.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/***
 * 通用 Service 实现
 *
 * @author 王大宸
 * @date 2023/11/15 10:15
 */
public class BaseServiceImpl<IBizMapper extends Mapper<T>, T, PK> implements IBaseService<T, PK> {

    @Autowired
    protected IBizMapper bizMapper;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /***
     * 分页查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:18
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<T>
     */
    @Override
    public TableData<T> page(Map<String, Object> params) {
        Query query = new Query(params);
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = bizMapper.selectByExample(example);
        return new TableData<T>(result.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2023/11/15 10:19
     * @param t t
     * @return T
     */
    @Override
    public T saveInfo(T t) throws Exception {
        bizMapper.insertSelective(t);
        return t;
    }

    /***
     * 根据主键查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:20
     * @param id id
     * @return T
     */
    @Override
    public T selectByPrimaryKey(PK id) throws Exception {
        return bizMapper.selectByPrimaryKey(id);
    }

    /***
     * 编辑
     *
     * @author 王大宸
     * @date 2023/11/15 10:20
     * @param t t
     * @return T
     */
    @Override
    public T updateInfo(T t) throws Exception {
        bizMapper.updateByPrimaryKeySelective(t);
        return t;
    }

    /***
     * 根据主键删除
     *
     * @author 王大宸
     * @date 2023/11/15 10:31
     * @param id id
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(PK id) throws Exception {
        return bizMapper.deleteByPrimaryKey(id);
    }

    /***
     * 根据对象, 查询一条数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:34
     * @param t t
     * @return T
     */
    @Override
    public T selectOne(T t) throws Exception {
        return bizMapper.selectOne(t);
    }
}
