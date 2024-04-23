package com.github.itdachen.framework.webmvc.controller;

import com.github.itdachen.framework.context.annotation.Log;
import com.github.itdachen.framework.core.constants.LogType;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.IBaseService;
import com.github.itdachen.framework.webmvc.utils.StringEscapeEditor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/***
* 通用 Controller
*
* @author 王大宸
* @date 2023/11/15 10:16
*/
public class BaseController<BizService extends IBaseService<T, PK>, T, PK> {

    @Autowired
    protected BizService bizService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(String[].class, new StringEscapeEditor());
    }

    /***
     * 分页查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:26
     * @param params 分页查询参数
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.framework.core.response.TableData < V>>
     */
    @GetMapping(value = "/page")
    @ResponseBody
    @Log(title = "分页查询", type = LogType.GET_PAGE_DATA)
    public ServerResponse<TableData<T>> page(Map<String,Object> params) throws Exception {
        return ServerResponse.okData(bizService.page(params));
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2023/11/15 10:26
     * @param t 需要新增的数据
     * @return com.github.itdachen.framework.core.response.ServerResponse<T>
     */
    @PostMapping(value = "")
    @ResponseBody
    @Log(title = "新增", type = LogType.SAVE)
    public ServerResponse<T> saveInfo(@Valid @RequestBody T t) throws Exception {
        return ServerResponse.okData(bizService.saveInfo(t));
    }

    /***
     * 更新
     *
     * @author 王大宸
     * @date 2023/11/15 10:26
     * @param t 需要更新的数据
     * @return com.github.itdachen.framework.core.response.ServerResponse<T>
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    @Log(title = "修改/编辑", type = LogType.UPDATE)
    public ServerResponse<T> updateInfo(@Valid @RequestBody T t) throws Exception {
        return ServerResponse.okData(bizService.updateInfo(t));
    }

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2023/11/15 10:26
     * @param id 需要查询数据的id
     * @return com.github.itdachen.framework.core.response.ServerResponse<T>
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ServerResponse<T> selectByPrimaryKey(@PathVariable("id") PK id) throws Exception {
        return ServerResponse.okData(bizService.selectByPrimaryKey(id));
    }

    /***
     * 删除数据
     *
     * @author 王大宸
     * @date 2023/11/15 10:27
     * @param id 需要删除数据的id
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.Integer>
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @Log(title = "删除", type = LogType.REMOVE)
    public ServerResponse<Integer> deleteByPrimaryKey(@PathVariable("id") PK id) throws Exception {
        return ServerResponse.okData(bizService.deleteByPrimaryKey(id));
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024/4/16 21:52
     * @param request request
     * @param response response
     * @return void
     */
    @DeleteMapping(value = "/exp" )
    @ResponseBody
    @Log(title = "导出" , type = LogType.EXPORT)
    public void dataExpToExcel(HttpServletRequest request, HttpServletResponse response, Map<String,Object> params) throws Exception {
        bizService.dataExpToExcel(request, response, params);
    }


}
