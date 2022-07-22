package dp.pb.learn.learn;

import dp.pb.test.Node;

import java.util.*;


public class Solution {
	
	public ArrayList<Integer> PrintFromTopToBottom(Node root) {
		ArrayList<Integer> layerList = new ArrayList<Integer>();
		if (root == null)
			return layerList;
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			layerList.add(node.val);
			if (node.left != null)
				queue.addLast(node.left);
			if (node.right != null)
				queue.addLast(node.right);
		}
		return layerList;
	}
	int src, dst;
	HashMap<Integer, List<int[]>> indegree;
	// 备忘录
	int[][] memo;

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		// 将中转站个数转化成边的条数
		K++;
		this.src = src;
		this.dst = dst;

		indegree = new HashMap<>();
		memo=new int[flights[0].length][flights.length];
		for (int[] f : flights) {
			int from = f[0];
			int to = f[1];
			int price = f[2];
			// 记录谁指向该节点，以及之间的权重
			indegree.putIfAbsent(to, new LinkedList<>());
			indegree.get(to).add(new int[] {from, price});
		}

		return dp(dst, K);
	}
	// 定义：从 src 出发，k 步之内到达 s 的最小成本
	int dp(int s, int k) {
		// base case
		if (s == src) {
			return 0;
		}
		if (k == 0) {
			return -1;
		}
		// 查备忘录，防止冗余计算
		if (memo[s][k] != 0) {
			return memo[s][k];
		}

		int res = Integer.MAX_VALUE;
		if (indegree.containsKey(s)) {
			// 当 s 有入度节点时，分解为子问题
			for (int[] v : indegree.get(s)) {
				int from = v[0];
				int price = v[1];
				// 从 src 到达相邻的入度节点所需的最短路径权重
				int subProblem = dp(from, k - 1);
				// 跳过无解的情况
				if (subProblem != -1) {
					res = Math.min(res, subProblem + price);
				}
			}
		}
		// 存入备忘录
		memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
		return memo[s][k];
	}

	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];
		output[0] = 1;
		for(int i = 1; i < nums.length; i++){
			// 首趟遍历，存下之前的数的乘积，即不选当前i的乘积
			// 注意，这里output[i - 1]表示不选i-1的数的乘积
			// 所以还需要乘i-1
			output[i] = output[i - 1] * nums[i - 1];
		}

		int total = 1;
		// 第二趟遍历,设一个变量保存包括当前数字及后续所有数字的乘积
		for(int i = nums.length - 1; i >= 0; i--){
			// i前面的数以及不包括当前数i的乘积 * i后面所有数的乘积
			output[i] *= total;
			// 更新当前数字及后续所有数字的乘积
			total *= nums[i];
		}
		return output;
	}


	public String reverseWords(String s) {
		StringBuffer ret = new StringBuffer();
		int length = s.length();
		int i = 0;
		while (i < length) {
			int start = i;
			while (i < length && s.charAt(i) != ' ') {
				i++;
			}
			for (int p = start; p < i; p++) {
				ret.append(s.charAt(start + i - 1 - p));
			}
			while (i < length && s.charAt(i) == ' ') {
				i++;
				ret.append(' ');
			}
		}
		return ret.toString();
	}


	public static void main(String[] args) {
		Solution s =new Solution();
		int[][] ll ={{0,1,100},{1,2,100},{0,2,500}};
		s.findCheapestPrice(3, ll,0,2,1);

		int xxx[] ={5,8,2,3};
		int[] ints = s.productExceptSelf(xxx);

		String s1 = s.reverseWords("Let's rock & roll");

		int l = s.longestValidParentheses(")((())()");
		System.err.println(l);
	}

	public int longestConsecutive(int[] nums) {
		Set<Integer> num_set = new TreeSet<>();
		for (int num : nums) {
			num_set.add(num);
		}

		int longestStreak = 0;
		Iterator<Integer> itr =num_set.iterator();
		int curr =0;
		while(itr.hasNext()){
			int num =itr.next();
			if(curr>=num){
				continue;
			}
			curr = num;
			int currentStreak = 1;
			while (num_set.contains(curr + 1)) {
				curr ++;
				currentStreak ++;
			}

			longestStreak = Math.max(longestStreak, currentStreak);
		}

		return longestStreak;
	}

	public int longestValidParentheses(String s) {
		Deque<Integer> stack = new ArrayDeque<>();
		int ans = 0;
		for (int i = 0, j = -1; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.addLast(i);
			} else {
				if (!stack.isEmpty()) {
					stack.pollLast();
					int top = j;
					if (!stack.isEmpty()) top = stack.peekLast();
					ans = Math.max(ans, i - top);
				} else {
					j = i;
				}
			}
		}
		return ans;
	}

}
