package leetcode;

/**
 * 寻找二叉树中2个节点的最近公共祖先
 *
 * @author chenchunyu
 * @date 2020/6/27
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class FirstFather {

    private static TreeNode firstFather;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode treeNode = lowestCommonAncestor(tree.getRoot(), new TreeNode(5), new TreeNode(4));
        System.out.println(treeNode.getVal());
    }


    /**
     * 解题思路
     * 一个x节点如果是两个节点p，q的最近公共祖先，那么必须满足如下条件
     * 1、x节点的左子树和右子树都必须包含p，q中的一个；
     * 2、如果x恰好是p，q中的一个，那么x的左子树或右子树必须包含p，q中的一个
     * 也就是满足如下公式：flson:x的左子树是否包含p，q中的一个；frson：x的右子树是否包含p，q中一个
     * (flson && frson) || ((x==p || x==q) && (flson ||frson))
     *
     * 代码逻辑：
     * 从根结点开始使用后续遍历的方式，首先根据上述规则判断是否是公共祖先；
     * 如果不是则标记节点的状态： 要么自己是p或q，要么子树包含p或q，满足则为true否则false
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        dfs(root,p,q);

        return firstFather;
    }

    /**
     * 该方法做两件事情
     * 1、判断入参中root是否为最近公共祖先
     * 2、为root打标并返回标记：root的左右子树或自己是否包含p或q，包含则返回true否则返回false
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        //终止条件
        if (root == null){
            return false;
        }

        boolean left = dfs(root.getLeft(),p,q);
        boolean right = dfs(root.getRight(),p,q);

        if ((left && right) || ((root.getVal() == p.getVal() || root.getVal() == q.getVal()) && (left || right))){
            firstFather = root;
        }

        return left || right || root.getVal()==p.getVal() || root.getVal() == q.getVal();
    }
}
