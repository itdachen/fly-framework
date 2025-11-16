package com.github.itdachen.framework.webmvc.poi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * WorkBookUtils
 *
 * @author 剑鸣秋朔
 * @date 2024-12-04 10:39
 */
public class WorkBookUtils {


    public static WorkBookExport export() {
        return new WorkBookExport();
    }

    public static WorkBookExport export(HttpServletRequest request, HttpServletResponse response) {
        return new WorkBookExport(request, response);
    }

    public static WorkBookExport export(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String title,
                                        List<String> fields,
                                        List<LinkedHashMap<String, String>> list,
                                        Object params) {
        return new WorkBookExport(request, response)
                .title(title)
                .fields(fields)
                .data(list)
                .params(params);
    }


}
