package com.github.itdachen.framework.context.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * ZTreeBuild
 *
 * @author 王大宸
 * @date 2024/5/5 21:21
 */
public class ZTreeBuild {


    /**
     * 保存参与构建树形的所有数据（通常数据库查询结果）
     */
    private final List<ZTreeNode> nodeList;

    /**
     * 根节点ID
     */
    private final String parentId;

    /***
     * 构造方法
     *
     * @author 王大宸
     * @date 2024/5/5 21:33
     * @param nodeList 将数据集合赋值给nodeList，即所有数据作为所有节点。
     * @param parentId 根节点ID
     */
    public ZTreeBuild(List<ZTreeNode> nodeList, String parentId) {
        this.nodeList = nodeList;
        this.parentId = parentId;
    }

    /**
     * 获取需构建的所有根节点（顶级节点） "0"
     *
     * @return 所有根节点List集合
     */
    public List<ZTreeNode> getRootNode() {
        // 保存所有根节点（所有根节点的数据）
        List<ZTreeNode> rootNodeList = new ArrayList<>();
        // treeNode：查询出的每一条数据（节点）
        for (ZTreeNode treeNode : nodeList) {
            // 判断当前节点是否为根节点，此处注意：若parentId类型是String，则要采用equals()方法判断。
            if (parentId.equals(treeNode.getParentId())) {
                // 是，添加
                rootNodeList.add(treeNode);
            }
        }
        return rootNodeList;
    }

    /**
     * 根据每一个顶级节点（根节点）进行构建树形结构
     *
     * @return 构建整棵树
     */
    public List<ZTreeNode> buildTree() {
        // treeNodes：保存一个顶级节点所构建出来的完整树形
        List<ZTreeNode> treeNodes = new ArrayList<ZTreeNode>();
        // getRootNode()：获取所有的根节点
        for (ZTreeNode treeRootNode : getRootNode()) {
            // 将顶级节点进行构建子树
            treeRootNode = buildChildTree(treeRootNode);
            // 完成一个顶级节点所构建的树形，增加进来
            treeNodes.add(treeRootNode);
        }
        return treeNodes;
    }

    /**
     * 递归-----构建子树形结构
     *
     * @param pNode 根节点（顶级节点）
     * @return 整棵树
     */
    public ZTreeNode buildChildTree(ZTreeNode pNode) {
        List<ZTreeNode> childTree = new ArrayList<ZTreeNode>();
        // nodeList：所有节点集合（所有数据）
        for (ZTreeNode treeNode : nodeList) {
            // 判断当前节点的父节点ID是否等于根节点的ID，即当前节点为其下的子节点
            if (treeNode.getParentId().equals(pNode.getId())) {
                // 再递归进行判断当前节点的情况，调用自身方法
                childTree.add(buildChildTree(treeNode));
            }
        }
        // for循环结束，即节点下没有任何节点，树形构建结束，设置树结果
        pNode.setChildren(childTree);
        return pNode;
    }


    public static void main(String[] args) {
        // 模拟测试数据（通常为数据库的查询结果）
        List<ZTreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(new ZTreeNode("1", "顶级节点A", "0"));
        treeNodeList.add(new ZTreeNode("2", "顶级节点B", "0"));
        treeNodeList.add(new ZTreeNode("3", "父节点是A", "1"));
        treeNodeList.add(new ZTreeNode("4", "父节点是B", "2"));
        treeNodeList.add(new ZTreeNode("5", "父节点是B", "2"));
        treeNodeList.add(new ZTreeNode("6", "父节点的ID是3", "3"));

        // 创建树形结构（数据集合作为参数）
        ZTreeBuild treeBuild = new ZTreeBuild(treeNodeList, "0");
        // 原查询结果转换树形结构
        treeNodeList = treeBuild.buildTree();
        System.err.println(treeNodeList.size());
    }


}
