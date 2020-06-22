package leetcode;

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
 * @TreeNode
 * @author chenchunyu
 * @date 2020/6/22
 */
public class BinaryTree {

    /**
     * 根结点
     */
    private TreeNode root;


    /**
     * 中序遍历：左-根-右
     * 迭代的方式
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root,res);
        return res;
    }

    private void inorderHelper(TreeNode root,List<Integer> res){
        if (root ==null){
            return;
        }
        if (root.getLeft() != null ){
            inorderHelper(root.getLeft(),res);
        }
        res.add(root.getVal());
        if (root.getRight() != null){
            inorderHelper(root.getRight(),res);
        }
    }


    /**
     * 中序遍历：左-根-右
     * leetcode 上的解题思路：对节点进行颜色标记，结合栈的结构
     * 访问过的节点标记为灰色，新的节点标记为白色
     * 如果出栈的节点是灰色，则把节点的指放在返回列表中
     * 如果出栈的节点是白色，则把节点自身（标记灰色）、节点左子树（标记白色）、节点右子树（标记灰色）
     * 按照一定的顺序压入栈中。因为栈是先入后出，因此入栈的顺序刚好与遍历的顺序相反
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        //创建一个栈
        Stack<ColorNode> stack = new Stack<>();

        //首先把根节点压入栈中
        stack.push(new ColorNode(ColorNode.WHITE,root));

        //对栈进行遍历
        while (!stack.isEmpty()){
            ColorNode cur = stack.pop();
            if (ColorNode.WHITE == cur.color){
                stack.push(new ColorNode(ColorNode.WHITE,cur.node.getRight()));
                stack.push(new ColorNode(ColorNode.GAY,cur.node));
                stack.push(new ColorNode(ColorNode.WHITE,cur.node.getLeft()));
            }else{
                res.add(cur.getNode().getVal());
            }
        }
        return res;
    }


    /**
     * 节点颜色
     */
    static class ColorNode{
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
