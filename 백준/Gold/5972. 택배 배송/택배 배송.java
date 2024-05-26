import java.util.*;
import java.io.*;

/*
240524
BJ5972 택배 배송 - 골5
RE
 */

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight); // 이래도 되낭? ? ?
        }
    }

    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<List<Node>> adjList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            adjList.get(a).add(new Node(b, c));
            adjList.get(b).add(new Node(a, c));
        }

        int[] dist = new int[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0));
        dist[0] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node next : adjList.get(cur.to)) {
                if (dist[next.to] > dist[cur.to] + next.weight) { //
                    dist[next.to] = dist[cur.to] + next.weight;
                    queue.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        System.out.print(dist[n - 1]);
    }
}
