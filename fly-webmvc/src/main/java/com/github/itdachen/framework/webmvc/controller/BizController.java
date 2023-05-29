package com.github.itdachen.framework.webmvc.controller;

import com.github.itdachen.framework.context.annotation.Log;
import com.github.itdachen.framework.core.biz.BizQuery;
import com.github.itdachen.framework.core.constants.LogType;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.IBizService;
import com.github.itdachen.framework.webmvc.utils.StringEscapeEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 通用控制器
 * D (Dto): 数据传输对象，主要用于外部接口参数传递封装，接口与接口进行传递使用。
 * V (Vo): 视图对象，主要用于给前端返回页面参数使用
 * Q (Query): 查询对象, 禁止使用 Map 传输查询参数
 * PK: 实体类对象主键类型
 * Created by 王大宸 on 2022-06-30 10:32
 * Created with IntelliJ IDEA.
 */
public class BizController<BizService extends IBizService<D, V, Q, PK>, D, V, Q extends BizQuery, PK> {

    @Autowired
    protected BizService bizService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(String[].class, new StringEscapeEditor());
    }

    /**
     * 分页查询
     *
     * @param params 分页查询参数
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.framework.core.response.TableData < V>>
     * @throws BizException
     */
    @GetMapping(value = "/page")
    @ResponseBody
    @Log(title = "分页查询", type = LogType.GET_PAGE_DATA)
    public ServerResponse<TableData<V>> page(Q params) throws Exception {
        return ServerResponse.okData(bizService.page(params));
    }

    /**
     * 新增
     *
     * @param d 需要新增的数据
     * @return com.github.itdachen.framework.core.response.ServerResponse<T>
     * @throws BizException
     */
    @PostMapping(value = "")
    @ResponseBody
    @Log(title = "新增", type = LogType.SAVE)
    public ServerResponse<V> save(@RequestBody D d) throws Exception {
        return ServerResponse.okData(bizService.save(d));
    }

    /**
     * 更新
     *
     * @param d 需要更新的数据
     * @return com.github.itdachen.framework.core.response.ServerResponse<T>
     * @throws BizException
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    @Log(title = "修改/编辑", type = LogType.UPDATE)
    public ServerResponse<V> update(@RequestBody D d) throws Exception {
        return ServerResponse.okData(bizService.update(d));
    }

    /**
     * 根据id查询
     *
     * @param id 需要查询数据的id
     * @return com.github.itdachen.framework.core.response.ServerResponse<V>
     * @throws BizException
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ServerResponse<V> getById(@PathVariable("id") PK id) throws Exception {
        return ServerResponse.okData(bizService.getById(id));
    }

    /**
     * 删除数据
     *
     * @param id 需要删除数据的id
     * @return com.github.itdachen.framework.core.response.ServerResponse<Integer>
     * @throws BizException
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @Log(title = "删除", type = LogType.REMOVE)
    public ServerResponse<Integer> remove(@PathVariable("id") PK id) throws Exception {
        return ServerResponse.okData(bizService.remove(id));
    }

}
