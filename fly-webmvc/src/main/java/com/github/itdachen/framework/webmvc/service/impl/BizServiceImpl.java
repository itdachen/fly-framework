package com.github.itdachen.framework.webmvc.service.impl;

import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.framework.core.biz.BizQuery;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.IBizService;
import com.github.itdachen.framework.webmvc.utils.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 通用服务接口实现
 * T (Entity): 数据对象，主要用于数据库层传递。
 * D (Dto): 数据传输对象，主要用于外部接口参数传递封装，接口与接口进行传递使用。
 * V (Vo): 视图对象，主要用于给前端返回页面参数使用
 * Q (Query): 查询对象, 禁止使用 Map 传输查询参数
 * DTO 转 T：接口接收参数将参数转化为数据库实体操作数据库使用。
 * T 转 Vo：将数据库层实体转化为Vo返回给客户端。
 * 注意: ***********************************
 * * 1、entity 里的每一个字段，与数据库相对应;
 * * 2、vo 里的每一个字段，是和你前台 html 页面相对应;
 * * 3、dto 这是用来转换从 entity 到 vo，或者从 vo 到 entity 的中间的东西。（DTO中拥有的字段应该是entity中或者是vo中的一个子集）
 * Created by 王大宸 on 2022-06-30 9:47
 * Created with IntelliJ IDEA.
 */
public class BizServiceImpl<IBizMapper extends Mapper<T>, IBizConvert extends IBizConvertMapper<T, D, V>, T, D, V, Q extends BizQuery, PK> implements IBizService<D, V, Q, PK> {
    private static final Logger logger = LoggerFactory.getLogger(BizServiceImpl.class);
    @Autowired
    protected IBizMapper bizMapper;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected IBizConvert bizConvert;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return com.github.itdachen.framework.core.response.TableData<V>
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
     * @param d 需要新增的数据
     * @return T
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public V save(D d) throws Exception {
        T t = bizConvert.toJavaObject(d);
        EntityUtils.setCreatAndUpdateInfo(t);
        bizMapper.insertSelective(t);
        return bizConvert.toJavaObjectVo(t);
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
        T t = bizMapper.selectByPrimaryKey(id);
        return bizConvert.toJavaObjectVo(t);
    }

    /**
     * 修改数据
     *
     * @param d 需要更新的数据
     * @return T
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public V update(D d) throws Exception {
        T t = bizConvert.toJavaObject(d);
        EntityUtils.setUpdatedInfo(t);
        bizMapper.updateByPrimaryKeySelective(t);
        return bizConvert.toJavaObjectVo(t);
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
