import java.util.*;
import java.io.*;

/*
240428
BJ1707 이분 그래프 - 골드 4
 */

public class Main {

    /*
    테케 2~5개
    정점 최대 2만개
    간선 최대 20만개

    이분 그래프 = 그래프 정점 집합을 둘로 나눠서, 각 집합의 정점끼리는 서로 인접하지 않도록 분할할 수 있는 그래프
     */

    static int k;
    static int v, e;
    static List<List<Integer>> adjList;
    static int[] visited;
    static boolean result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        k = Integer.parseInt(in.readLine());

        for (int z = 1; z <= k; ++z) {

            result = true;

            st = new StringTokenizer(in.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            adjList = new ArrayList<>();
            for (int i = 0; i < v; ++i) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < e; ++i) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            // 일단 그래서 이분 그래프가 뭔데? ;;

            // dfs ? bfs?
            //
            visited = new int[v];
            // 연결 그래프라는 보장이 없으므로 모든 정점에 대해 확인해줘야 함 !! ㅠ_ㅠ
            for (int i = 0; i < v; ++i) {
                if (visited[i] == 0) {
                    dfs(i, 2);
                }
            }


            if (result) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }

    static void dfs(int cur, int prevColor) {
        int curColor = (prevColor == 1 ? 2 : 1);

        visited[cur] = curColor; // 현재 정점은 visited가 false라서 들어온 거임. prev와 반대 색으로 칠하기

        for (int i = 0; i < adjList.get(cur).size(); ++i) { // cur과 연결된 모든 정점에 대해
            if (visited[adjList.get(cur).get(i)] == 0) { // visited 0이면
                // dfs 보내기
                dfs(adjList.get(cur).get(i), curColor);
            } else if (visited[adjList.get(cur).get(i)] == prevColor) { // 0이 아니면.. prev와 반대 색으로 칠해져 있는지 확인하기
                // ok
            } else { // 이분 그래프 규칙 위배
                result = false;
                return;
            }
        }
    }
}
