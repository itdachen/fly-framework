package com.github.itdachen.framework.webmvc.service.impl;

import com.github.itdachen.framework.webmvc.utils.ConvertBeanUtils;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
public class BizServiceImpl<IBizMapper extends Mapper<T>, T, D, V, Q extends BizQuery, PK> implements IBizService<D, V, Q, PK> {
    private static final Logger logger = LoggerFactory.getLogger(BizServiceImpl.class);
    @Autowired
    protected IBizMapper bizMapper;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private final IBizConvertMapper<T, D, V> bizConvertMapper;

    public BizServiceImpl(IBizConvertMapper<T, D, V> bizConvertMapper) {
        this.bizConvertMapper = bizConvertMapper;
    }

    /***
     * 分页查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:28
     * @param params 查询参数
     * @return com.github.itdachen.framework.core.response.TableData<V>
     */
    @Override
    public TableData<V> page(Q params) throws Exception {
        // Class<Q> clazz = getQueryInstance();
        Class<T> tClazz = getEntityClazz();

        Example example = new Example(tClazz);
        String s = String.valueOf(params);
        Map<String, String> stringStringMap = StringUtils.strObjToHashMap(s);
        stringStringMap.remove("page");
        stringStringMap.remove("limit");
        if (stringStringMap.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }

        example.setOrderByClause("id DESC");
        Page<V> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<V> list = (List<V>) bizMapper.selectByExample(example);
        return new TableData<V>(page.getTotal(), list);
    }

    /***
     * 新增数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:28
     * @param d 需要新增的数据
     * @return V
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public V saveInfo(D d) throws Exception {
        T t = ConvertBeanUtils.convert(d, getEntityClazz());
        EntityUtils.setCreatAndUpdateInfo(t);
        bizMapper.insertSelective(t);
        return ConvertBeanUtils.convert(t, getVOClazz());
    }


    /***
     * 根据主键查询数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:28
     * @param id 需要查询的主键
     * @return V
     */
    @Override
    public V selectByPrimaryKey(PK id) throws Exception {
        T t = bizMapper.selectByPrimaryKey(id);
        return ConvertBeanUtils.convert(t, getVOClazz());
    }

    /***
     * 更新数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:27
     * @param d 需要更新的数据
     * @return V
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public V updateInfo(D d) throws Exception {
        T t = ConvertBeanUtils.convert(d, getEntityClazz());
        EntityUtils.setUpdatedInfo(t);
        bizMapper.updateByPrimaryKeySelective(t);
        return ConvertBeanUtils.convert(t, getVOClazz());
    }

    /***
     * 根据主键删除数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:27
     * @param id 需要删除数据的主键
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(PK id) throws Exception {
        return bizMapper.deleteByPrimaryKey(id);
    }

    /***
     * 数据导出
     *
     * @author 王大宸
     * @date 2025/10/17 0:47
     * @param request request
     * @param response response
     * @param params params
     * @return void
     */
    @Override
    public void expInfo(HttpServletRequest request,
                        HttpServletResponse response,
                        Q params) throws Exception {
        logger.warn("请重写导出方法");

//        WorkBookUtils.export(request, response)
//                .params(params)
//                .title("-")
//                .upload(true)
//                .rowNum(true)
//                .fields(new ArrayList<>())
//                .data(new ArrayList<>())
//                .execute();


        throw new BizException("请重写导出方法");
    }

    /***
     * 数据导入
     *
     * @author 王大宸
     * @date 2025/10/17 0:47
     * @param request request
     * @param response response
     * @param file file
     * @return void
     */
    @Override
    public void impInfo(HttpServletRequest request,
                        HttpServletResponse response,
                        MultipartFile file) throws Exception {
        logger.warn("请重写导入方法");
        throw new BizException("请重写导入方法");
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
    public <E> int batchSave(List<E> list, Class<E> clazz) throws Exception {
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
    public <E> int batchUpdate(List<E> list, Class<E> clazz) throws Exception {
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


    /***
     * 获取表泛型实体类
     *
     * @author 王大宸
     * @date 2025/10/16 22:46
     * @return T
     * @since 1.5
     */
    private T getEntityInstance() throws Exception {
        ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[1];
        return clazz.newInstance();

//        Type[] actualTypeArguments = ptype.getActualTypeArguments();
//        for (Type actualTypeArgument : actualTypeArguments) {
//            if (actualTypeArgument.getTypeName().contains("entity")) {
//                System.err.println(actualTypeArgument.getTypeName());
//                Class<T> clazz = (Class<T>) actualTypeArgument;
//                return clazz.newInstance();
//            }
//        }

    }

    private Class<T> getEntityClazz() throws Exception {
        ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) ptype.getActualTypeArguments()[1];
    }


    private Class<V> getVOClazz() throws Exception {
        ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ptype.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            if (actualTypeArgument.getTypeName().contains("vo")
                    && (actualTypeArgument.getTypeName().endsWith("VO")
                    || actualTypeArgument.getTypeName().endsWith("Vo"))) {
                return (Class<V>) actualTypeArgument;
            }
        }
        return null;
    }

}
