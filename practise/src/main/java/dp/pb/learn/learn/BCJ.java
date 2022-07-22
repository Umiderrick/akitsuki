package dp.pb.learn.learn;


import java.util.Random;

/**
 * @author pengbo
 * @date 2021/5/13 19:26
 * @name:
 */
public class BCJ {

    int Find(int[] root, int index) {
        if (root[index] != index) {
            root[index] = Find(root, root[index]);
        }
        return root[index];
    }

    // 将一个根节点指向另一个根节点
    void Union(int[] root, int index1, int index2) {

        root[Find(root, index1)] = Find(root, index2);
    }


    int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        int[] root=new int[size];

        // 初始指向自己
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
        //合并集合
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (isConnected[i][j] == 1) {
                    Union(root, i, j);
                }
            }
        }
        //计算树的个数
        int circles = 0;
        for (int i = 0; i < size; i++) {
            if (root[i] == i) {
                circles++;
            }
        }
        return circles;
    }




    public static void main(String[] args) {
        int[][] a =new int[2][3];
        Random r =new Random();
        for (int i=0;i<2;i++){
            for (int j=0;j<3;j++){
                a[i][j] = r.nextInt();
            }
        }
    }
}
