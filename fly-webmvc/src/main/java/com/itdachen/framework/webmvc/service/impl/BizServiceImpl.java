package com.itdachen.framework.webmvc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itdachen.framework.core.biz.BizQuery;
import com.itdachen.framework.core.exception.BizException;
import com.itdachen.framework.core.response.TableData;
import com.itdachen.framework.core.utils.StringUtils;
import com.itdachen.framework.webmvc.entity.EntityUtils;
import com.itdachen.framework.webmvc.mapper.IBizMapper;
import com.itdachen.framework.webmvc.service.IBizService;
import com.itdachen.framework.webmvc.utils.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Description: 通用服务接口实现
 * Created by 王大宸 on 2022-06-30 9:47
 * Created with IntelliJ IDEA.
 */
public class BizServiceImpl<BizMapper extends IBizMapper<T, V, Q, PK>, T, V, Q extends BizQuery, PK> implements IBizService<T, V, Q, PK> {
    private static final Logger logger = LoggerFactory.getLogger(BizServiceImpl.class);
    @Autowired
    protected BizMapper bizMapper;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return com.itdachen.framework.core.response.TableData<V>
     * @throws BizException
     */
    @Override
    public TableData<V> page(Q params) throws Exception {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(clazz);
        String s = String.valueOf(params);
        Map<String, String> stringStringMap = StringUtils.strObjToHashMap(s);
        if (stringStringMap.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
                criteria.orLike(entry.getKey(), entry.getValue() == null ? "" : "%" + entry.getValue() + "%")
                        .orEqualTo(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
            }
        }
        example.setOrderByClause("id DESC");
        Page<V> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<V> list = (List<V>) bizMapper.selectByExample(example);
        return new TableData<V>(page.getTotal(), list);
    }

    /**
     * 新增
     *
     * @param entity 需要新增的数据
     * @return T
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public T save(T entity) throws Exception {
        EntityUtils.setCreatAndUpdateInfo(entity);
        bizMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 根据id查询
     *
     * @param id 根据id查询数据
     * @return V
     * @throws BizException
     */
    @Override
    public V getById(PK id) throws Exception {
        return bizMapper.selectInfoVo(id);
    }

    /**
     * 修改数据
     *
     * @param entity 需要更新的数据
     * @return T
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public T update(T entity) throws Exception {
        EntityUtils.setUpdatedInfo(entity);
        bizMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }

    /**
     * 根据id删除数据
     *
     * @param id 需要删除数据的id
     * @return int
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(PK id) throws Exception {
        return bizMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量添加(不建议使用)
     *
     * @param list  数据集合
     * @param clazz 类类型
     * @return int
     * @throws BizException
     */
    @Transactional(rollbackFor = Exception.class)
    protected <E> int batchSave(List<E> list, Class<E> clazz) throws Exception {
        try {
            if (CollectionUtils.isEmpty(list)) {
                return 0;
            }
            String sql = JdbcUtils.batchSave(list, clazz);
            logger.info("批量添加sql: " + sql);
            jdbcTemplate.execute(sql);
            return list.size();
        } catch (Exception e) {
            logger.error("批量添加操作失败", e);
            throw new BizException("批量操作失败");
        }
    }

    /**
     * 批量更新(不建议使用)
     *
     * @param list  数据集合
     * @param clazz 数据类类型
     * @return int
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    protected <E> int batchUpdate(List<E> list, Class<E> clazz) throws Exception {
        try {
            if (CollectionUtils.isEmpty(list)) {
                return 0;
            }
            String sql = JdbcUtils.batchUpdateSql(list, clazz);
            logger.info("批量更新sql: " + sql);
            jdbcTemplate.execute(sql);
            return list.size();
        } catch (Exception e) {
            logger.error("批量更新操作失败", e);
            throw new BizException("批量操作失败");
        }
    }


}
