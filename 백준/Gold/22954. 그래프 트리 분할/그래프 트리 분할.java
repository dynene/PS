import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (N <= 2 || M <= N - 3) {
            System.out.println(-1);
            return;
        }

        List<int[]> edgeList = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edgeList.add(new int[]{i, a, b});
        }

        Map<Integer, List<int[]>> edgeDict = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            edgeDict.put(i, new ArrayList<>());
        }

        boolean[] visited = new boolean[N + 1];
        for (int[] edge : edgeList) {
            int i = edge[0], a = edge[1], b = edge[2];
            edgeDict.get(a).add(new int[]{i, b});
            edgeDict.get(b).add(new int[]{i, a});
        }

        List<List<Integer>> allNodes = new ArrayList<>();
        List<List<Integer>> allEdges = new ArrayList<>();
        List<Integer> lastNodes = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                List<Integer> nodeList = new ArrayList<>();
                List<Integer> edgeListForNode = new ArrayList<>();
                dfs(i, nodeList, edgeListForNode, visited, edgeDict);
                allNodes.add(nodeList);
                allEdges.add(edgeListForNode);
                lastNodes.add(nodeList.get(nodeList.size() - 1));
            }
        }

        if (allNodes.size() > 2) {
            System.out.println(-1);
            return;
        }

        if (allNodes.size() == 2) {
            if (allNodes.get(0).size() == allNodes.get(1).size()) {
                System.out.println(-1);
                return;
            }
            System.out.println(allNodes.get(0).size() + " " + allNodes.get(1).size());
            for (int i = 0; i < 2; i++) {
                printList(allNodes.get(i));
                printList(allEdges.get(i));
            }
            return;
        }

        List<Integer> nodes = allNodes.get(0);
        List<Integer> edges = allEdges.get(0);
        int lastNode = lastNodes.get(0);

        List<Integer> curEdges = new ArrayList<>();
        for (int idx : edges) {
            int[] edge = edgeList.get(idx - 1);
            int a = edge[1], b = edge[2];
            if (a == lastNode || b == lastNode) continue;
            curEdges.add(idx);
        }

        System.out.println(nodes.size() - 1 + " 1");
        for (int node : nodes) {
            if (node != lastNode) {
                System.out.print(node + " ");
            }
        }
        System.out.println();
        printList(curEdges);
        System.out.println(lastNode);
    }

    private static void dfs(int node, List<Integer> nodeList, List<Integer> edgeList, boolean[] visited, Map<Integer, List<int[]>> edgeDict) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        nodeList.add(node);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int[] edge : edgeDict.get(curr)) {
                int idx = edge[0], next = edge[1];
                if (!visited[next]) {
                    visited[next] = true;
                    edgeList.add(idx);
                    nodeList.add(next);
                    stack.push(next);
                }
            }
        }
    }

    private static void printList(List<Integer> list) {
        Collections.sort(list);
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
