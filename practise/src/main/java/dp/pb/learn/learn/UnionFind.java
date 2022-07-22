package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2021/12/10 16:14
 * @name:
 */
public class UnionFind {
    int[] roots;
    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int find(int i) {
        if (roots[i] == i) {
            return i;
        }
        return roots[i] = find(roots[i]);
    }

    // 判断 p 和 q 是否在同一个集合中
    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    // 合并 p 和 q 到一个集合中
    public void union(int p, int q) {
        roots[find(p)] = find(q);
    }

    public static void main(String[] args) {
            // 初始化并查集
            int[][] graph ={{1,2,3},{0,2},{0,1,3},{0,2}};
            UnionFind uf = new UnionFind(graph.length);
            // 遍历每个顶点，将当前顶点的所有邻接点进行合并
            for (int i = 0; i < graph.length; i++) {
                int[] adjs = graph[i];
                for (int w: adjs) {
                    // 若某个邻接点与当前顶点已经在一个集合中了，说明不是二分图，返回 false。
                    if (uf.isConnected(i, w)) {
                        System.err.println(false);
                    }
                    uf.union(adjs[0], w);
                }
            }
        System.err.println(true);
    }
}
