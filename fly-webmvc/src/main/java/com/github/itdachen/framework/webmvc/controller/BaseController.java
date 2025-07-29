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
import org.springframework.web.multipart.MultipartFile;

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
    @Log(title = LogType.GET_PAGE_DATA_MSG, type = LogType.GET_PAGE_DATA)
    public ServerResponse<TableData<T>> page(Map<String, Object> params) throws Exception {
        return ServerResponse.ok(bizService.page(params));
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
    @Log(title = LogType.SAVE_MSG, type = LogType.SAVE)
    public ServerResponse<T> saveInfo(@Valid @RequestBody T t) throws Exception {
        return ServerResponse.ok(bizService.saveInfo(t));
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
    @Log(title = LogType.UPDATE_MSG, type = LogType.UPDATE)
    public ServerResponse<T> updateInfo(@Valid @RequestBody T t) throws Exception {
        return ServerResponse.ok(bizService.updateInfo(t));
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
        return ServerResponse.ok(bizService.selectByPrimaryKey(id));
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
    @Log(title = LogType.REMOVE_MSG, type = LogType.REMOVE)
    public ServerResponse<Integer> deleteByPrimaryKey(@PathVariable("id") PK id) throws Exception {
        return ServerResponse.ok(bizService.deleteByPrimaryKey(id));
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
    @GetMapping(value = "/exp")
    @ResponseBody
    @Log(title = LogType.EXPORT_MSG, type = LogType.EXPORT)
    public void dataExpToExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) throws Exception {
        bizService.expInfo(request, response, params);
    }

    /***
     * 导入
     *
     * @author 王大宸
     * @date 2025/7/29 18:51
     * @param request request
     * @param response response
     * @param file file
     * @return void
     */
    @PostMapping(value = "/imp")
    @ResponseBody
    @Log(title = LogType.IMPORT_MSG, type = LogType.IMPORT)
    public void impInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws Exception {
        bizService.impInfo(request, response, file);
    }


}
