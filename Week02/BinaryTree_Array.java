package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用数组实现的二叉树
 *
 * @author chenchunyu
 * @date 2020/6/23
 */
public class BinaryTree_Array {

    private int[] nodes;

    public BinaryTree_Array(int[] nodes) {
        this.nodes = nodes;
    }


    public static void main(String[] args) {
        BinaryTree_Array binaryTree_array = new BinaryTree_Array(new int[]{1, 2, 3, 4, 5, 6,7});
        List<Integer> integers = binaryTree_array.inorderTraversal();
        List<Integer> integers1 = binaryTree_array.preorderTraversal();
        List<Integer> integers2 = binaryTree_array.postorderTraversal();
        System.out.println("中序"+JSON.toJSONString(integers));
        System.out.println("前序"+JSON.toJSONString(integers1));
        System.out.println("后序"+JSON.toJSONString(integers2));

    }


    /**
     * 中序遍历
     *
     * @return
     */
    public List<Integer> inorderTraversal() {
        List<Integer> res = new ArrayList<>();
        int startIndex = 0;
        inorderHelper(startIndex, res);
        return res;
    }

    /**
     * 前序遍历
     * @return
     */
    public List<Integer> preorderTraversal(){
        List<Integer> res = new ArrayList<>();
        int startIndex = 0;
        preorderHelper(startIndex, res);
        return res;
    }

    /**
     * 后序遍历
     * @return
     */
    public List<Integer> postorderTraversal(){
        List<Integer> res = new ArrayList<>();
        int startIndex = 0;
        postorderHelper(startIndex, res);
        return res;
    }

    private void inorderHelper(int startIndex, List<Integer> res) {
        if (startIndex >= 0 && startIndex < nodes.length) {
            res.add(nodes[startIndex]);
        }
        //left-child-node
        int leftIndex = 2 * startIndex + 1;
        if (leftIndex >= 0 && leftIndex < nodes.length) {
            inorderHelper(leftIndex, res);
        }


        //right-child-node
        int rightIndex = 2 * startIndex + 2;
        if (rightIndex >= 0 && rightIndex < nodes.length) {
            inorderHelper(rightIndex, res);
        }
    }

    private void preorderHelper(int startIndex,List<Integer> res){
        //left-child-node
        int leftIndex = 2 * startIndex + 1;
        if (leftIndex >= 0 && leftIndex < nodes.length) {
            inorderHelper(leftIndex, res);
        }

        if (startIndex >= 0 && startIndex < nodes.length) {
            res.add(nodes[startIndex]);
        }

        //right-child-node
        int rightIndex = 2 * startIndex + 2;
        if (rightIndex >= 0 && rightIndex < nodes.length) {
            inorderHelper(rightIndex, res);
        }
    }

    private void postorderHelper(int startIndex,List<Integer> res){
        //left-child-node
        int leftIndex = 2 * startIndex + 1;
        if (leftIndex >= 0 && leftIndex < nodes.length) {
            inorderHelper(leftIndex, res);
        }



        //right-child-node
        int rightIndex = 2 * startIndex + 2;
        if (rightIndex >= 0 && rightIndex < nodes.length) {
            inorderHelper(rightIndex, res);
        }

        if (startIndex >= 0 && startIndex < nodes.length) {
            res.add(nodes[startIndex]);
        }
    }




    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }
}
