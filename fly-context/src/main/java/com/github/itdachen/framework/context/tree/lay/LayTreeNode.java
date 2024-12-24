package com.github.itdachen.framework.context.tree.lay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lay-vue 树结构
 *
 * @author 王大宸
 * @date 2024-12-24 10:21
 */
public class LayTreeNode implements Serializable {
    private static final long serialVersionUID = 415337031177257051L;

    public static LayTree tree() {
        return new LayTree(new ArrayList<>(), new ArrayList<>());
    }

    public static LayTree tree(List<String> checked, List<TreeNode> data) {
        return new LayTree(checked, data);
    }

    public static TreeNode treeNode() {
        return new TreeNode();
    }


}
