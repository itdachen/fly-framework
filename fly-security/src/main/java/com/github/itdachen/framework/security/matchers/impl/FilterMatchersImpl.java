package com.github.itdachen.framework.security.matchers.impl;

import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.security.matchers.IFilterMatchers;
import com.github.itdachen.framework.security.matchers.pass.IPassMatchers;
import com.github.itdachen.framework.security.matchers.pass.ISecurityPassMatchers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-09-23 17:24
 * Created with IntelliJ IDEA.
 */
@Service
public class FilterMatchersImpl implements IFilterMatchers {

    /* 不需要认证的路径 */
    private final IPassMatchers passMatchers;
    private final ISecurityPassMatchers securityPassMatchers;

    public FilterMatchersImpl(IPassMatchers passMatchers,
                              ISecurityPassMatchers securityPassMatchers) {
        this.passMatchers = passMatchers;
        this.securityPassMatchers = securityPassMatchers;
    }

    @Override
    public String[] matchers() {
        String[] matchers = securityPassMatchers.matchers();
        List<String> arr = passMatchers.matchers();
        if (StringUtils.isEmpty(arr) || 0 == arr.size()) {
            return rejectSame(Arrays.stream(matchers).toList());
        }
        arr.addAll(Arrays.asList(matchers));
        return rejectSame(arr);
    }

    @Override
    public List<String> requestMatchers() {
        String[] matchers = securityPassMatchers.matchers();
        List<String> arr = passMatchers.matchers();
        if (StringUtils.isEmpty(arr) || 0 == arr.size()) {
            return Arrays.stream(matchers).toList();
        }

        List<String> list = new ArrayList<>(Arrays.stream(matchers).toList());
        list.addAll(arr);
        return list;
    }

    /***
     * 去除重复的
     *
     * @author 王大宸
     * @date 2022/9/25 12:04
     * @param list list
     * @return java.lang.String[]
     */
    private String[] rejectSame(List<String> list) {
        List<String> arr = new ArrayList<>();
        for (String s : list) {
            if (arr.contains(s)) {
                continue;
            }
            arr.add(s);
        }
        return arr.toArray(new String[0]);
    }


}
