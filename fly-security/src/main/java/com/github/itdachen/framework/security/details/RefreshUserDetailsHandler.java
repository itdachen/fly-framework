package com.github.itdachen.framework.security.details;

import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.*;

/**
 * 刷新用户信息
 *
 * @author 王大宸
 * @date 2023-12-17 14:17
 */
public class RefreshUserDetailsHandler implements IRefreshUserDetails {

    private final SecurityContextRepository securityContextRepository;

    public RefreshUserDetailsHandler(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

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
    @Override
    public void refreshUserDetails(HttpServletRequest request,
                                   HttpServletResponse response,
                                   CurrentUserDetails userDetails) throws Exception {
        setUserDetails(request, response, userDetails, null);
    }

    /***
     * 刷新用户信息
     *
     * @author 王大宸
     * @date 2023/12/17 14:41
     * @param request request
     * @param response response
     * @param userDetails 用户信息
     * @param authorities 新权限
     * @return void
     */
    @Override
    public void refreshUserDetails(HttpServletRequest request,
                                   HttpServletResponse response,
                                   CurrentUserDetails userDetails,
                                   List<String> authorities) throws Exception {
        setUserDetails(request, response, userDetails, authorities);
    }

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
    @Override
    public void refreshAuthorities(HttpServletRequest request,
                                   HttpServletResponse response,
                                   List<String> list) throws Exception {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(list.size());
        for (String authority : list) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        /* 当前认证信息 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUserInfo userInfo = (CurrentUserInfo) authentication.getPrincipal();

        /* 添加权限信息 */
        userInfo.setAuthorities(grantedAuthorities);

        securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
    }


    /***
     * 更新用户信息
     *
     * @author 王大宸
     * @date 2023/12/17 15:01
     * @param request request
     * @param response response
     * @param userDetails userDetails
     * @param authorities authorities
     * @return void
     */
    private void setUserDetails(HttpServletRequest request,
                                HttpServletResponse response,
                                CurrentUserDetails userDetails,
                                List<String> authorities) {
        /* 当前认证信息 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUserInfo userInfo = (CurrentUserInfo) authentication.getPrincipal();

        userInfo.setNickName(userDetails.getNickName());
        userInfo.setAvatar(userDetails.getAvatar());
        userInfo.setTelephone(userDetails.getTelephone());
        userInfo.setEmail(userDetails.getEmail());
        userInfo.setStatus(userDetails.getStatus());
        userInfo.setAppId(userDetails.getAppId());
        userInfo.setOpenId(userDetails.getOpenId());
        userInfo.setUserType(userDetails.getUserType());
        userInfo.setSex(userDetails.getSex());
        userInfo.setDeptId(userDetails.getDeptId());
        userInfo.setDeptTitle(userDetails.getDeptTitle());
        userInfo.setParentDeptId(userDetails.getParentDeptId());
        userInfo.setDeptLevel(userDetails.getDeptLevel());
        userInfo.setAnId(userDetails.getAnId());
        userInfo.setAnTitle(userDetails.getAnTitle());
        userInfo.setIsSuperAdmin(userDetails.getIsSuperAdmin());
        userInfo.setExpTime(userDetails.getExpTime());
        userInfo.setOther(userDetails.getOther());
        userInfo.setPermissions(userDetails.getPermissions());

        /* 更新权限 */
        if (null != authorities && !authorities.isEmpty()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities.size());
            for (String authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
            userInfo.setAuthorities(grantedAuthorities);
        }

        securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
    }


}
