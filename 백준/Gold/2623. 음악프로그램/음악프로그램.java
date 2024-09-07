import java.util.*;
import java.io.*;

/*
240907
20:44
BJ2623 음악프로그램 - 골3
 */

public class Main {

    /*
    위상 정렬... 복습의 시간
    1️⃣ 진입차수가 0인 노드를 큐에 담습니다.
    2️⃣ 큐가 비어있을 때까지 다음의 과정을 반복합니다.
        1) 큐에 담긴 노드를 꺼내어 해당 노드에서 출발하는 모든 간선을 그래프에서 제거합니다.
        2) 진입차수가 0인 노드를 큐에 담습니다.
     */

    static int n, m;
    static int[] indegree;
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            adjList.add(new ArrayList<>());
        }

        indegree = new int[n + 1];
        boolean[] check = new boolean[n + 1];

        /*
        1️⃣ 진입차수가 0인 노드를 큐에 담습니다.
        2️⃣ 큐가 비어있을 때까지 다음의 과정을 반복합니다.
            1) 큐에 담긴 노드를 꺼내어 해당 노드에서 출발하는 모든 간선을 그래프에서 제거합니다.
            2) 진입차수가 0인 노드를 큐에 담습니다.
         */

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());

            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t - 1; ++j) {
                int b = Integer.parseInt(st.nextToken());
                adjList.get(a).add(b);
                indegree[b]++;
                a = b;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
                check[i] = true;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);

            for (int to : adjList.get(cur)) {
                indegree[to]--;
            }
            adjList.get(cur).clear();

            for (int i = 1; i <= n; ++i) {
                if (!check[i] && indegree[i] == 0) {
                    queue.add(i);
                    check[i] = true;
                }
            }
        }

        if(result.size()==n) {
            for(int t:result) {
                System.out.print(t+" ");
            }
        }else {
            System.out.println(0);
        }
    }
}
