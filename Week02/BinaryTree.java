package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    public BinaryTree(int[] vals) {
        List<TreeNode> nodeList = new ArrayList<>();
        //把值转换为TreeNode,并且保证元素在列表和数组的下标一致
        for (int index = 0; index < vals.length; index++) {
            nodeList.add(new TreeNode(vals[index]));
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
        int lastLeft = lastParentIndex * 2 + 1; // lastLeft = vals.length-1
        nodeList.get(lastParentIndex).setLeft(nodeList.get(lastLeft));

        //仅当数组的长度是奇数时，才有右子树
        if (vals.length % 2 == 1) {
            int lastRight = lastParentIndex * 2 + 2;// lastRight = vals.length
            //todo 这个地方没有理解到，下标vals.length 已经超出了范围
            nodeList.get(lastParentIndex).setRight(nodeList.get(lastRight));
        }
        root = nodeList.get(0);
    }

    /**
     * 插入一个数据
     *
     * @param val
     */
    public void addElemt(int val) {

    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7,8});
        List<Integer> integers = inorderTraversal_1(binaryTree.getRoot());
        List<Integer> integers1 = inorderTraversal_2(binaryTree.getRoot());
        System.out.println(JSON.toJSONString(integers));
        System.out.println(JSON.toJSONString(integers1));


    }

    /**
     * 中序遍历：左-根-右
     * 迭代的方式
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null) {
            inorderHelper(root.getLeft(), res);
        }
        res.add(root.getVal());
        if (root.getRight() != null) {
            inorderHelper(root.getRight(), res);
        }
    }


    /**
     * 中序遍历：左-根-右
     * leetcode 上的解题思路：对节点进行颜色标记，结合栈的结构
     * 访问过的节点标记为灰色，新的节点标记为白色
     * 如果出栈的节点是灰色，则把节点的值放在返回列表中
     * 如果出栈的节点是白色，则把节点自身（标记灰色）、节点左子树（标记白色）、节点右子树（标记灰色）
     * 按照一定的顺序压入栈中。因为栈是先入后出，因此入栈的顺序刚好与遍历的顺序相反
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        //创建一个栈
        Stack<ColorNode> stack = new Stack<>();

        //首先把根节点压入栈中
        stack.push(new ColorNode(ColorNode.WHITE, root));

        //对栈进行遍历
        while (!stack.isEmpty()) {
            ColorNode cur = stack.pop();
            if (ColorNode.WHITE == cur.color) {
                if (null != cur.node.getRight()) {
                    stack.push(new ColorNode(ColorNode.WHITE, cur.node.getRight()));
                }
                stack.push(new ColorNode(ColorNode.GAY, cur.node));
                if (null != cur.node.getLeft()) {
                    stack.push(new ColorNode(ColorNode.WHITE, cur.node.getLeft()));
                }
            } else {
                res.add(cur.getNode().getVal());
            }
        }
        return res;
    }


    /**
     * 节点颜色
     */
    static class ColorNode {
        private int color;
        private TreeNode node;

        public static int WHITE = 1;
        public static int GAY = 0;

        public ColorNode(int color, TreeNode node) {
            this.color = color;
            this.node = node;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }
    }


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
