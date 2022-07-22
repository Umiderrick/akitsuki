package dp.pb.learn.learn;

import java.util.ArrayList;

public class Solu {

	public int TreeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = TreeDepth(root.left);
		int right = TreeDepth(root.right);

		return left >= right ? left + 1 : right + 1;
	}

	public static  int Sum_Solution(int n) {
		if (n == 1||n == 0) {
			return n;
		}else{
			return n+Sum_Solution(n-1);
		}
	}

	public static String replaceSpace(StringBuffer str) {
		int q = str.length();
		int sp = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				sp++;
			}
		}
		int nq = 2 * sp + q;
		char[] cc = new char[nq];
		int skip = 0;
		for (int i = 0; i < q; i++) {
			if (str.charAt(i) == ' ') {
				cc[i + 2 * skip] = '%';
				cc[i + 2 * skip + 1] = '2';
				cc[i + 2 * skip + 2] = '0';
				skip++;
			} else {
				cc[i + 2 * skip] = str.charAt(i);
			}
		}
		return new String(cc);
	}

	static boolean  isSubsequence(String s, String t) {
		int m = s.length(), n = t.length();
		// 对 t 进行预处理
		ArrayList<Integer>[] index = new ArrayList[256];
		for (int i = 0; i < n; i++) {
			char c = t.charAt(i);
			if (index[c] == null)

				index[c] = new ArrayList<>();
			index[c].add(i);
		}

		// 串 t 上的指针
		int j = 0;
		// 借助 index 查找 s[i]
		for (int i = 0; i < m; i++) {
			char c = s.charAt(i);
			// 整个 t 压根儿没有字符 c
			if (index[c] == null) return false;
			int pos = left_bound(index[c], j);
			// 二分搜索区间中没有找到字符 c
			if (pos == index[c].size()) return false;
			// 向前移动指针 j
			j = index[c].get(pos) + 1;
		}
		return true;
	}
	// 查找左侧边界的二分查找
	private static int left_bound(ArrayList<Integer> arr, int tar) {
		int lo = 0, hi = arr.size();
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (tar > arr.get(mid)) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}

	public static void main(String[] args) {
		boolean subsequence = isSubsequence("abcd","aaaaabooocpppd");
		System.err.println(subsequence);
	}

}
