package com.github.itdachen.framework.context.node;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 树结构数据类型
 * T: 数据类型
 * E: 选中的数据类型, 例如: Integer, String
 * Created by 王大宸 on 2023/05/15 21:48
 * Created with IntelliJ IDEA.
 */
public class TreeNode<T, E> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 树结构数据
     */
    private List<T> data;

    /**
     * 选中的数据
     */
    private List<E> checked;

    public TreeNode(List<T> data, List<E> checked) {
        this.data = data;
        this.checked = checked;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<E> getChecked() {
        return checked;
    }

    public void setChecked(List<E> checked) {
        this.checked = checked;
    }

}
