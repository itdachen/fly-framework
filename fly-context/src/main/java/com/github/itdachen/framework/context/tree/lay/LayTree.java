package com.github.itdachen.framework.context.tree.lay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * LayTree
 *
 * @author 王大宸
 * @date 2024-12-24 10:27
 */
public class LayTree implements Serializable {
    private static final long serialVersionUID = 415337031177257051L;

    /**
     * 选中的ID集合
     */
    private List<String> checked = new ArrayList<>();

    /**
     * 树结构数据
     */
    private List<TreeNode> data = new ArrayList<>();

    public LayTree(List<String> checked, List<TreeNode> data) {
        this.checked = checked;
        this.data = data;
    }

    public LayTree checked(List<String> checked) {
        this.checked = checked;
        return this;
    }

    public LayTree data(List<TreeNode> data) {
        this.data = data;
        return this;
    }


    public List<String> getChecked() {
        return checked;
    }

    public void setChecked(List<String> checked) {
        this.checked = checked;
    }

    public List<TreeNode> getData() {
        return data;
    }

    public void setData(List<TreeNode> data) {
        this.data = data;
    }

}
