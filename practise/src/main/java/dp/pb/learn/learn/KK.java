package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2022/2/18 19:41
 * @name:
 */
public class KK {
    static int[] p;//用于给点进行编号
    public static int find(int x){//寻找祖先
        if(p[x]==x) return x;//如果该点的下标与该点的编号一致，就认为该点就是祖先
        return p[x]=find(p[x]);//将遇到的点的编号都设置为祖先的编号
    }
    public static int makeConnected(int n, int[][] connections) {
        p=new int[n];//定义数组
        int m=connections.length;
        if(n-1>m)
            return -1;
        for(int i=0;i<n;i++) p[i]=i;
        int ans=n-1;//节点总数
        for(int[] temp:connections){
            int x=find(temp[0]);//寻找该点的祖先
            int y=find(temp[1]);//寻找该点的祖先
            if(x!=y){//如果祖先不相同，就进行合并
                p[x]=y;//两个节点连接
                ans--;//就减去1个节点
            }
        }
        return ans;//剩下的单个节点数
    }

    public static void main(String[] args) {
        int[][] connections = {{0,1},{0,2},{1,2}};
        makeConnected(4, connections);
    }

}
