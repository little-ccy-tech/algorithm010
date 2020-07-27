package leetcode;

/**
 * 并查集合
 *
 * @author chenchunyu
 * @date 2020/7/27
 */
public class UnionFind {

    /**
     * 存放每个人的直接关联的一个朋友
     * 数组下标表示人的编号
     */
    private int[] parent;

    /**
     * 当前孤立的朋友圈有多少个
     */
    private int count;

    /**
     * 初始化一个并查集
     * @param n
     */
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        //让每个人只与自己关联，表示每个人都没有朋友
        for (int i=0;i<n;i++){
            parent[i] = i;
        }
    }

    /**
     * 查找p的朋友根
     * @param p
     * @return
     */
    public int findRoot(int p){
        while (parent[p] != p){
            parent[p] = parent[parent[p]]; //路径压缩
            p = parent[p]; //跳一格，检查查询次数
        }
        return p;
    }


    /**
     * 合并p和q所在的朋友圈
     * @param p
     * @param q
     */
    public void union(int p,int q){
        int rootp = findRoot(p);
        int rootq = findRoot(q);
        if (rootp == rootq) return;
        parent[rootp] = rootq;
        count--;//合并后朋友圈减少一个
    }

    public int[] getParent() {
        return parent;
    }

    public void setParent(int[] parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
