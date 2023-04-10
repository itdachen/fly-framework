package com.github.itdachen.framework.code.utils;

import com.github.itdachen.framework.code.sdk.vo.TableColumnVo;
import com.github.itdachen.framework.code.sdk.vo.TableInfoVo;
import com.github.itdachen.framework.core.utils.StringUtils;

/**
 * Description:
 * Created by 王大宸 on 2023/04/10 21:57
 * Created with IntelliJ IDEA.
 */
public class PkColumnUtils {

    public static void setPkColumn(TableInfoVo table) {
        for (TableColumnVo column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }

    }


}
