
import java.io.*;
import java.util.*;

public class Solution {

	static int n, m;
	static List<List<Integer>> adjList;
	static boolean[] visited;

	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine());
		for (int z = 1; z <= t; ++z) {
			result = 0;

			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			adjList = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				adjList.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < m; ++i) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				adjList.get(a).add(b);
				adjList.get(b).add(a);
			}

			visited = new boolean[n];
			for (int i = 0; i < n; ++i) {
				if(visited[i]==false) {
					result++;
					visited[i]=true;
					dfs(i);
				}
			}

			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	static void dfs(int cur) {
		
		for(int next : adjList.get(cur)) {
			if(!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
	}

}
