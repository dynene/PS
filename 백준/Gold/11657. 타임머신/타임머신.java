/*
241103
10:07
BJ11657 타임머신 - 골4
+ 벨만포드 알고리즘 복습 ...ㅠㅠ
+ dist 자료형이 long이 되어야 함에 유의!!!
 */

import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); // 500개
        m = Integer.parseInt(st.nextToken()); // 6000개

        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()); // 음수가능, 0가능

            edgeList.add(new Edge(a, b, c));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Edge cur = edgeList.get(j);
                if (dist[cur.from] != Integer.MAX_VALUE) {
                    if (dist[cur.from] + cur.cost < dist[cur.to]) {
                        dist[cur.to] = dist[cur.from] + cur.cost;
                        if (i == n - 1) { // m번째 반복에서 갱신 = 음의 사이클
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; ++i) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);

    }
}
