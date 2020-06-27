package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据一颗二叉树的前序和中序遍历列表构建出一颗二叉树
 *
 * @author chenchunyu
 * @date 2020/6/27
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class BuildTree {

    private static Map<Integer, Integer> inorderIndex;

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);

        //后面可以对这个树做前序和中序遍历得到的结果与入参对比，来检测结果是否正确
        List<Integer> integers = BinaryTree.inorderTraversal_1(treeNode);
        System.out.println(JSON.toJSONString(integers));
    }


    /**
     * 解题思路
     * 1、由二叉树的前序遍历得到的数组和中序遍历得到的数组可以得到如下的规律：
     * 前序遍历：{根，左子树的前序遍历结果，右子树的前序遍历结果}
     * 中序遍历：{左子树的中序遍历结果，根，右子树的中序遍历结果}
     * 而且在每一个子树的前、中序遍历结果中也遵循上述的规律。
     * <p>
     * 由此我们找到了重复性，我们可以使用迭代的方式，从根开始找到它的左右子树
     * 本迭代的关键在于如何设计入参：
     * 为了减少空间的浪费，每一层的左子树和右子树我们都从原数组中读取，那么我们势必需要左右子树在前序和中序中的数组下标边界
     * <p>
     * 那怎么计算这个边界呢，关键就在于根在中序遍历中的位置
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        int pre_left = 0;
        int pre_right = preorder.length - 1;
        int in_left = 0;
        int in_right = inorder.length - 1;

        return buildMyTree(preorder, inorder, pre_left, pre_right, in_left, in_right);
    }

    private static TreeNode buildMyTree(int[] preorder, int[] inorder, int pre_left, int pre_right, int in_left, int in_right) {

        //termilater
        if (pre_left > pre_right) {
            return null;
        }

        //根结点在前序遍历数组中的位置
        int pre_root = pre_left;
        //根结点在中序遍历数组中的位置
        int in_root = inorderIndex.get(preorder[pre_root]);
        //左子树的长度
        int left_child_length = in_root-in_left;

        TreeNode root = new TreeNode(preorder[pre_root]);

        //左子树在前序遍历数组中的右边界位置
        int pre_left_child_right = pre_root+left_child_length;
        root.setLeft(buildMyTree(preorder,inorder,pre_root+1,pre_left_child_right,in_left,in_root-1));

        //右子树在前序遍历数组中的左边界位置
        int pre_right_child_left = pre_left_child_right+1;
        root.setRight(buildMyTree(preorder,inorder,pre_right_child_left,pre_right,in_root+1,in_right));

        return root;
    }


}
