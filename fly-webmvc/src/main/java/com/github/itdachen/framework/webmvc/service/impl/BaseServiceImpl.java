package com.github.itdachen.framework.webmvc.service.impl;

import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.IBaseService;
import com.github.itdachen.framework.webmvc.utils.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***
 * 通用 Service 实现
 *
 * @author 剑鸣秋朔
 * @date 2023/11/15 10:15
 */
public class BaseServiceImpl<IBizMapper extends Mapper<T>, T, PK> implements IBaseService<T, PK> {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected IBizMapper bizMapper;

    /***
     * 分页查询
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:18
     * @param query params
     * @return com.github.itdachen.framework.core.response.TableData<T>
     */
    @Override
    public TableData<T> page(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            Iterator<Map.Entry<String, Object>> iterator = query.entrySet().iterator();
            String key;
            while (iterator.hasNext()) {
                key = String.valueOf(iterator.next());
                criteria.andLike(key, "%" + query.get(key) + "%");
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = bizMapper.selectByExample(example);
        return new TableData<T>(result.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:19
     * @param t t
     * @return T
     */
    @Override
    public T saveInfo(T t) throws Exception {
        EntityUtils.setCreatAndUpdateInfo(t);
        bizMapper.insertSelective(t);
        return t;
    }

    /***
     * 根据主键查询
     *
     * @author 剑鸣秋朔
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
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:20
     * @param t t
     * @return T
     */
    @Override
    public T updateInfo(T t) throws Exception {
        EntityUtils.setUpdatedInfo(t);
        bizMapper.updateByPrimaryKeySelective(t);
        return t;
    }

    /***
     * 根据主键删除
     *
     * @author 剑鸣秋朔
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
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:34
     * @param t t
     * @return T
     */
    @Override
    public T selectOne(T t) throws Exception {
        return bizMapper.selectOne(t);
    }

    @Override
    public void expInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) throws Exception {
        logger.warn("请重写导出方法");
        throw new BizException("请重写导出方法");

//        WorkBookUtils.export()
//                .request(null)
//                .response(null)
//                .title("")
//                .fields(new ArrayList<>())
//                .list(new ArrayList<>())
//                .params(null)
//                .execute();

    }

    @Override
    public void impInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
        logger.warn("请重写导入方法");
        throw new BizException("请重写导入方法");
    }


}
