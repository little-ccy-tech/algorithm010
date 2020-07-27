package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 二叉树
 * 实现二叉树的前序、中序和后序遍历
 * 树的遍历：访问树中所有节点且各节点仅访问一次的方式。
 * "中序遍历"是指按照 左子树-父节点-右子树
 * "前序遍历"是指按照 父节点-左子树-右子树
 * "后序遍历"是指按照 左子树-右子树-父节点
 * "层次遍历"从根结点开始，一次往深层次访问，同一个层次中按照 firstChild->nextSibing,给我的感觉是需要存储结构支持
 * 同一个层次的节点需要有引用关系
 *
 * @author chenchunyu
 * @TreeNode
 * @date 2020/6/22
 */
public class BinaryTree {

    /**
     * 根结点
     */
    private static TreeNode root;


    /**
     * 构建一颗二叉树
     * 把一个数组转为二叉树结构
     * 思路：数组的0号元素作为二叉树的根，依次把数组中的元素放入二叉树的左和右子树节点中。
     * 通过观察可以发现如下的规律：
     * 假设index表示元素在数组的下标，那么树中对应的元素
     * 它的左子树的数组下标是 2*index+1;
     * 它的右子树的数组下标是 2*index+2;
     * 它的父节点的数组下标是 (index-1)/2
     * <p>
     * 这样得到的二叉树是一颗完全二叉树
     *
     * @param vals
     */
    public BinaryTree(Integer[] vals) {
        List<TreeNode> nodeList = new ArrayList<>();
        //把值转换为TreeNode,并且保证元素在列表和数组的下标一致
        for (int index = 0; index < vals.length; index++) {
            Integer val = vals[index];
            nodeList.add(null == val? null:new TreeNode(val));
        }

        //根据下标来设置左、右子树节点;防止数组越界，2*j+2 <vals.length-> j<vals.length/2-1
        for (int j = 0; j < vals.length / 2 - 1; j++) {
            //左子树
            nodeList.get(j).setLeft(nodeList.get(2 * j + 1));

            //右子树
            nodeList.get(j).setRight(nodeList.get(2 * j + 2));
        }

        //还有最后一个父节点
        int lastParentIndex = vals.length / 2 - 1;
        int lastLeft = lastParentIndex * 2 + 1; // 注意 当vals.length奇偶不同时，会得到不同的值，因此不能换算成：lastLeft = vals.length-1
        nodeList.get(lastParentIndex).setLeft(nodeList.get(lastLeft));

        //仅当数组的长度是奇数时，才有右子树
        if (vals.length % 2 == 1) {
            int lastRight = lastParentIndex * 2 + 2;// 注意不能直接换算成：lastRight = vals.length
            nodeList.get(lastParentIndex).setRight(nodeList.get(lastRight));
        }
        root = nodeList.get(0);
    }




    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> integers = inorderTraversal_1(binaryTree.getRoot());
        List<Integer> integers1 = inorderTraversal_2(binaryTree.getRoot());
        List<List<Integer>> lists = levelOrderTraversal(binaryTree.getRoot());
        List<List<Integer>> lists1 = levelOrderTraversal_queue(binaryTree.getRoot());
        System.out.println(JSON.toJSONString(integers));
        System.out.println(JSON.toJSONString(integers1));
        System.out.println(JSON.toJSONString(lists));
        System.out.println(JSON.toJSONString(lists1));


    }



    /**
     * 二叉树的层序遍历
     * 解题思路：
     * 1、构建一个队列或者列表，把每一层的节点全部放入队列中，然后逐个打印节点，并获取节点的子节点放入一个新的集合中
     * 2、清空队列
     * 3、把上一次循环得到的子节点集合放入队列中，循环第一步
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderTraversal(TreeNode root){
        //总的层序遍历结果
        List<List<Integer>> result = new ArrayList<>();

        //构建队列
        List<TreeNode> currDealNodes = new ArrayList<>();

        //利用队列代码更简洁Queue<TreeNode>

        currDealNodes.add(root);

        while (!currDealNodes.isEmpty()){

            //当前层所能得到的全部子节点，从左到右排列
            List<TreeNode> currLevelChildNodes = new ArrayList<>();

            //当前层能打印的节点值
            List<Integer> currLevelRes = new ArrayList<>();

            //当前层的节点从左到右遍历
            for (TreeNode treeNode:currDealNodes){
                currLevelRes.add(treeNode.getVal());

                //放入左节点
                if (null != treeNode.getLeft()){
                    currLevelChildNodes.add(treeNode.getLeft());

                }

                //放入右节点
                if (null != treeNode.getRight()){
                    currLevelChildNodes.add(treeNode.getRight());
                }
            }

            currDealNodes.clear();
            currDealNodes.addAll(currLevelChildNodes);
            result.add(currLevelRes);
        }

        return result;

    }

    /**
     * 使用队列来做层序遍历的数据结构
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderTraversal_queue(TreeNode root){

        Queue<TreeNode> nodes = new LinkedList<>();

        nodes.add(root);

        List<List<Integer>> res = new ArrayList<>();

        while (!nodes.isEmpty()){
            int currLevelSize = nodes.size();
            List<Integer> currRes = new ArrayList<>();
            for (int i=0;i<currLevelSize;i++){
                TreeNode poll = nodes.poll();
                currRes.add(poll.getVal());

                if (poll.getLeft() != null ){
                    nodes.add(poll.getLeft());
                }

                if (poll.getRight() != null){
                    nodes.add(poll.getRight());
                }
            }

            res.add(currRes);
        }
        return res;
    }


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
