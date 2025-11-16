package com.github.itdachen.framework.webmvc.service;

import com.github.itdachen.framework.core.biz.BizQuery;
import com.github.itdachen.framework.core.response.TableData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 通用服务接口
 * T: 实体类对象
 * V: 查询数据返回对象
 * Q: 查询对象, 禁止使用 Map 传输查询参数
 * PK: 实体类对象主键类型
 * Created by 剑鸣秋朔 on 2022-06-30 9:25
 * Created with IntelliJ IDEA.
 */
public interface IBizService<D, V, Q extends BizQuery, PK> {

    /***
     * 分页查询
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:28
     * @param params 查询参数
     * @return com.github.itdachen.framework.core.response.TableData<V>
     */
    TableData<V> page(Q params) throws Exception;


    /***
     * 新增数据
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:28
     * @param d 需要新增的数据
     * @return V
     */
    V saveInfo(D d) throws Exception;

    /***
     * 根据主键查询数据
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:28
     * @param id 需要查询的主键
     * @return V
     */
    V selectByPrimaryKey(PK id) throws Exception;

    /***
     * 更新数据
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:27
     * @param d 需要更新的数据
     * @return V
     */
    V updateInfo(D d) throws Exception;

    /***
     * 根据主键删除数据
     *
     * @author 剑鸣秋朔
     * @date 2023/11/15 10:27
     * @param id 需要删除数据的主键
     * @return int
     */
    int deleteByPrimaryKey(PK id) throws Exception;

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
    void expInfo(HttpServletRequest request, HttpServletResponse response, Q params) throws Exception;

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
