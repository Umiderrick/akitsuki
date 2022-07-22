package dp.pb.learn.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author pengbo
 * @date 2022/2/28 15:46
 * @name:
 */
public class XXX {
        static class Edge {
            int from;
            int to;
            int cost;

            Edge(int from, int to, int cost) {
                this.from = from;
                this.to = to;
                this.cost = cost;
            }

            void revert() {
                cost = -cost;
                from ^= to;
                to ^= from;
                from ^= to;
            }
        }

        public static int maximumRequests(int n, int[][] requests) {
            int ans = requests.length;
            int[] counts = new int[n];
            int k = 0;
            ArrayList<Edge> edgesList = new ArrayList<>();
            ArrayList<Integer>[] vListArrs = new ArrayList[n + 2];
            int s = n;
            int t = n + 1;
            for (int i = 0; i < vListArrs.length; i++) {
                vListArrs[i] = new ArrayList<>();
            }
            for (int[] request : requests) {
                counts[request[0]]++;
                counts[request[1]]--;
                Edge edge = new Edge(request[0], request[1], 1);
                edgesList.add(edge);
                vListArrs[request[0]].add(edgesList.size() - 1);
                vListArrs[request[1]].add(edgesList.size() - 1);
            }

            for (int i = 0; i < counts.length; i++) {
                while (counts[i] > 0) {
                    k++;
                    Edge edge = new Edge(s, i, 0);
                    edgesList.add(edge);
                    vListArrs[s].add(edgesList.size() - 1);
                    vListArrs[i].add(edgesList.size() - 1);
                    counts[i]--;
                }
                while (counts[i] < 0) {
                    Edge edge = new Edge(i, t, 0);
                    edgesList.add(edge);
                    vListArrs[i].add(edgesList.size() - 1);
                    vListArrs[t].add(edgesList.size() - 1);
                    counts[i]++;
                }
            }

            //0-1 BFS
            int[] h = new int[n + 2];
            int[] dist = new int[n + 2];
            boolean[] vis = new boolean[n + 2];
            int[] pre = new int[n + 2];
            while (k-- > 0) {
                Arrays.fill(dist, n + 2);
                Arrays.fill(vis, false);
                Arrays.fill(pre, -1);
                LinkedList<Integer> store = new LinkedList<>();

                dist[s] = 0;
                store.add(s);

                while (!store.isEmpty()) {
                    int cur = store.removeFirst();
                    if (vis[cur]) {
                        continue;
                    }
                    vis[cur] = true;
                    for (int edgeIndex : vListArrs[cur]) {
                        Edge edge = edgesList.get(edgeIndex);
                        if (edge.from != cur || vis[edge.to]) {
                            continue;
                        }

                        int w = edge.cost + h[edge.from] - h[edge.to];
                        if (dist[edge.to] > dist[edge.from] + w) {
                            dist[edge.to] = dist[edge.from] + w;
                            pre[edge.to] = edgeIndex;
                        }
                        if (w == 0) {
                            store.addFirst(edge.to);
                        } else {
                            store.addLast(edge.to);
                        }
                    }
                }

                for (int i = 0; i < h.length; i++) {
                    h[i] += dist[i];
                }

                ans -= h[t];

                int v = t;
                while (v != s) {
                    Edge edge = edgesList.get(pre[v]);
                    v = edge.from;
                    edge.revert();
                }
            }

            return ans;
        }
    //[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]
    public static void main(String[] args) {
        int[][] requests ={{1,0},{0,1},{1,2},{3,4},{0,1},{2,0}};
        int i = maximumRequests(5, requests);
        System.err.println(i);
    }
}
