package com.github.itdachen.framework.webmvc.service;


import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.utils.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

/***
 * 通用 Service
 *
 * @author 剑鸣秋朔
 * @date 2023/11/15 10:15
 */
public interface IBaseService<T, PK> {

    /***
     * 分页查询
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:18
     * @param query params
     * @return com.github.itdachen.framework.core.response.TableData<T>
     */
    TableData<T> page(Query query);

    /***
     * 新增
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:19
     * @param t t
     * @return T
     */
    T saveInfo(T t) throws Exception;

    /***
     * 根据主键查询
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:20
     * @param id id
     * @return T
     */
    T selectByPrimaryKey(PK id) throws Exception;

    /***
     * 编辑
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:20
     * @param t t
     * @return T
     */
    T updateInfo(T t) throws Exception;

    /***
     * 根据主键删除
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:31
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(PK id) throws Exception;

    /***
     * 根据对象, 查询一条数据
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:34
     * @param t t
     * @return T
     */
    T selectOne(T t) throws Exception;

    /***
     * 导出
     *
     * @author 剑鸣秋朔
     * @date 2024/4/16 21:54
     * @param request request
     * @param response response
     * @param params params
     * @return void
     */
    void expInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) throws Exception;

    /***
     * 导入
     *
     * @author 剑鸣秋朔
     * @date 2025/7/29 18:53
     * @param request request
     * @param response response
     * @param file file
     * @return void
     */
    void impInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception;


}
