package dp.pb.learn.learn;

import java.util.*;

class L228 {
    static class Edge{
        int from, to, cost;
        public Edge(int f, int t, int c) {
            from =f;
            to =t;
            cost =c;
        }
    }
    private static void swap(Edge e){
        int tmp =e.from;
        e.from =e.to;
        e.to =tmp;
    }
    public static int maximumRequests(int n, int[][] requests) {
        int s = n, t = n + 1, N = n + 2, K = 0;
        //calculate the diff array
        int[] diff =new int[n];
        for(int[] v : requests){
            diff[v[0]] += 1;
            diff[v[1]] -= 1;
        }
        //create the edges
        List<Edge> edges =new ArrayList<>();
        for(int i = 0; i < n; i += 1){
            if(diff[i] > 0) {
                for(int j = 0; j < diff[i]; j += 1) {
                    edges.add(new Edge(s, i, 0));
                }
            }
            if(diff[i] < 0){
                for(int j = 0; j < -diff[i]; j += 1)   {
                    edges.add(new Edge(i, t, 0));
                }
            }
            K += Math.max(diff[i], 0);
        }
        for(int[] v : requests) {
            edges.add(new Edge(v[0], v[1], 1));
        }
        //build the graph
        ArrayList<Integer>[] G= new ArrayList[n + 2];
        for(int i = 0; i < edges.size(); i += 1){
            List<Integer> fd = G[edges.get(i).from];
            fd.add(i);
            List<Integer> td = G[edges.get(i).to];
            td.add(i);
        }

        int ans = requests.length;
        //using ssp algorithm with 01BFS to find the min-cost max-flow
        int[] h =new int[N];
        for(int k = 0; k < K; k += 1) {
            int[] distance = new int[N];
            Arrays.fill(distance, N);
            int[] pre = new int[N];
            Arrays.fill(distance, -1);
            int[] done = new int[N];
            distance[s] = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.addLast(s);
            while (!q.isEmpty()) {
                int u = q.peek();
                q.poll();
                if (done[u] == 0) {
                    continue;
                }
                done[u] = 1;
                for (int i : G[u]) {
                    if (edges.get(i).from == u) {
                        int w = edges.get(i).cost + h[u] - h[edges.get(i).to];
                        if (distance[edges.get(i).to] > distance[u] + w) {
                            distance[edges.get(i).to] = distance[u] + w;
                            if (w == 0) {
                                q.addLast(edges.get(i).to);
                            } else {
                                q.addFirst(edges.get(i).to);
                            }
                            pre[edges.get(i).to] = i;
                        }
                    }
                }

            }
            for(int i = 0; i < N; i += 1) {
                h[i] += distance[i];
            }
            ans -= h[t];
            for(int u = t; u != s; u = edges.get(pre[u]).to){
                edges.get(pre[u]).cost = -edges.get(pre[u]).cost;
                swap(edges.get(pre[u]));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] requests ={{1,0},{0,1},{1,2},{3,4},{0,1},{2,0}};
        int i = maximumRequests(5, requests);
        System.err.println(i);
    }
}