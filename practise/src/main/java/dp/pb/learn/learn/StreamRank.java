package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2022/2/9 16:03
 * @name:
 */
class StreamRank {

    int[] tree = new int[50001];


    public StreamRank() {
    }

    public void track(int x) {
        for (int i = x+1; i <= 50001; i += (i & -i)) tree[i] += 1;
    }

    public int getRankOfNumber(int x) {
        int ans = 0;
        for (int i = x+1; i > 0; i -= (i & -i)) ans += tree[i];
        return ans;
    }

}

