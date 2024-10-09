/*
241010
5:22
BJ10282 해킹 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    a가 b에 의존하면, b가 감염되면 s초 후 a도 감염됨
    b -s-> a
    b노드에서 a노드로 비용 s의 간선이 존재...
     */

    static class Node {
        int dist;
        int node;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int t;
    static int n, d, c;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(in.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int z = 0; z < t; ++z) {
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수, 1만
            d = Integer.parseInt(st.nextToken()); // 의존성 개수, 10만
            c = Integer.parseInt(st.nextToken()) - 1; // 해킹당한 컴퓨터 번호, 1~n

            int infectNum = 0;
            int infectTime = 0;

            List<List<Node>> adjList = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < d; ++i) {
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                // a가 b를 의존하며, b가 감염되면 s초 후 a도 감염됨
                adjList.get(b).add(new Node(a, s));
            }

            // c에서 시작해서 도달할 수 있는 가장 먼... 거리...
            int[] visited = new int[n];
            Arrays.fill(visited, Integer.MAX_VALUE);

            Queue<Node> queue = new ArrayDeque<>();

            queue.add(new Node(c, 0));
            visited[c] = 0;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (Node next : adjList.get(cur.node)) { // cur에서 도달할 수 있는 모든 노드에... 바이러스 보내기...
                    if (visited[next.node] > cur.dist + next.dist) { // 지금까지 도달 가능한 거리보다, 새로 계산한 거리가 더 짧음
                        visited[next.node] = cur.dist + next.dist;
                        queue.add(new Node(next.node, cur.dist + next.dist));
                    } else { // 지금까지 도달 가능한 거리가, 새로 계산한 거리보다 짧음
                        // 냅둬
                    }
                }
            }

            // 총 감염되는 컴퓨터 수
            // 마지막 컴퓨터가 감염되기까지의 시간
            for (int i = 0; i < n; ++i) {
                if (visited[i] != Integer.MAX_VALUE) {
                    infectNum++;
                    if (visited[i] > infectTime) {
                        infectTime = visited[i];
                    }
                }
            }

            sb.append(infectNum).append(" ").append(infectTime).append("\n");
        }

        System.out.print(sb);
    }
}
