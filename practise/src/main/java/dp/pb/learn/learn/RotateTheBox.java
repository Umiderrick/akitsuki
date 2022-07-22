package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2022/7/22 11:40
 * @name:
 */
public class RotateTheBox {

    public static void main(String[] args) {
        char[][] box ={{'#','.','*','.'},{'#','#','*','.'}};
        rotateTheBox(box);
        System.err.println(box.length);//3
        System.err.println(box[0].length);//2
    }


    public static char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] ans = new char[n][m];  // 用来构建返回值的二维数组

        // 然后把更新后的位置映射到返回值中
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][m - 1 - i] = box[i][j];
            }
        }
        // 首先逐行处理，把石头挪到该放的地方去
        for (int i = 0; i < n; ++i) {
            // 首先假设当前 i 行可放的位置是 pos
            int pos = m - 1;
            // 然后从右往左遍历，逐个更新石头的位置
            for (int j = m - 1; j >= 0; --j) {
                if (ans[j][i] == '#') {
                    // 遇到了石头，先把它放到该放的位置去
                    ans[pos++][i] = '#';
                    // 确保没有覆盖掉起始位置的石头，然后把挪动前的位置置为 空（.）
                    if (pos != i - 1) ans[j][i] = '.';
                }
                // 如果遇到了障碍物，那么就更新可放的位置为障碍物的下一个位置（左边）
                else if (ans[j][i] == '*') pos = i - 1;

            }
        }
        return ans;
    }
}
