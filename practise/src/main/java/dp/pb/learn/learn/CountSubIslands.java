package dp.pb.learn.learn;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pengbo
 * @date 2022/2/17 15:12
 * @name:
 */
public class CountSubIslands {

    int[][] dir ={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][]  g1 ,g2;
    int m ,n;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        g1 =grid1;
        g2 =grid2;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    ans += bfs(i, j);
                }
            }
        }
        return ans;
    }

    private int bfs(int i ,int j){
        Queue<Pair<Integer,Integer>> q =new LinkedList<>();
        q.add(new Pair<>(i, j));
        g2[i][j] = 0;
        // 判断岛屿包含的每一个格子是否都在 grid1 中出现了
        int check = g1[i][j];
        while (!q.isEmpty()) {
            Pair<Integer,Integer> t = q.poll();
            for (int d = 0; d < 4; ++d) {
                int nx = t.getKey() + dir[d][0];
                int ny = t.getValue() + dir[d][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && g2[nx][ny] == 1) {
                    q.add(new Pair<>(nx, ny));
                    g2[nx][ny] = 0;
                    if (g1[nx][ny] != 1) {
                        check = 0;
                    }
                }
            }
        }
        return check;
    }
}
