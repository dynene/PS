import java.io.*;
import java.util.*;

public class Solution {

	static int n;
	static int start;
	static int[][] adjMatrix;
	static boolean[] visited;

	static class Vertex {
		int num;
		int depth;

		Vertex(int num, int depth) {
			this.num = num;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int z = 1; z <= 10; ++z) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			visited = new boolean[101];
			adjMatrix = new int[101][101];

			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n / 2; ++i) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = 1;
			}

			int result = 0;

			Queue<Vertex> queue = new ArrayDeque<Vertex>();
			queue.offer(new Vertex(start, 0));
			visited[start] = true;

			List<List<Integer>> maxlist = new ArrayList<>();

			while (!queue.isEmpty()) {
				Vertex cur = queue.poll();
				
				if(maxlist.size() <= cur.depth) {
					maxlist.add(new ArrayList<Integer>());
				}
				maxlist.get(cur.depth).add(cur.num);

				for (int i = 1; i <= 100; ++i) {
					if (adjMatrix[cur.num][i] == 0)
						continue;

					if (visited[i] == false) {
						queue.offer(new Vertex(i, cur.depth + 1));
						visited[i] = true;
					}
				}
			}
			
			int maxdepth = maxlist.size()-1;
			Collections.sort(maxlist.get(maxdepth));
			int resultidx = maxlist.get(maxdepth).size()-1;
			result = maxlist.get(maxdepth).get(resultidx);

			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
