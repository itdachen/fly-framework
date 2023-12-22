package com.github.itdachen.boot.security.details;

import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 刷新用户权限
 *
 * @author 王大宸
 * @date 2023-12-17 14:15
 */
public interface IRefreshUserDetails {

    /***
     * 刷新用户信息
     *
     * @author 王大宸
     * @date 2023/12/17 14:37
     * @param request request
     * @param response response
     * @param userDetails userDetails
     * @return void
     */
    void refreshUserDetails(HttpServletRequest request,
                            HttpServletResponse response,
                            CurrentUserDetails userDetails) throws Exception;

    /***
     * 刷新用户信息
     *
     * @author 王大宸
     * @date 2023/12/17 14:59
     * @param request request
     * @param response response
     * @param userDetails 用户信息
     * @param authorities 权限编码集合
     * @return void
     */
    void refreshUserDetails(HttpServletRequest request,
                            HttpServletResponse response,
                            CurrentUserDetails userDetails,
                            List<String> authorities) throws Exception;

    /***
     * 刷新权限信息
     *
     * @author 王大宸
     * @date 2023/12/17 14:37
     * @param request request
     * @param response response
     * @param list list
     * @return void
     */
    void refreshAuthorities(HttpServletRequest request,
                            HttpServletResponse response,
                            List<String> list) throws Exception;

}
