

/*
 * 231006
 * BOj 11725 트리의 부모 찾기
 * 실버 2
 * 
 * 메모리
 * 시간
 */

import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * 루트 1인 트리에 대해 각 노드의 부모 구하기
	 * 
	 * 노드 10만개
	 * n-1개 간선
	 */
	
	static int n;
	static List<List<Integer>> adjList;
	static int[] parent;
	static boolean[] visited;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(in.readLine());
		
		parent = new int[n];
		visited = new boolean[n];
		
		adjList = new ArrayList<>();
		for(int i=0;i<n;++i) {
			adjList.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<n-1;++i) {
			st = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			adjList.get(a).add(b);
			adjList.get(b).add(a); 
		}
		
		//1부터 bfs 돌려서...각 노드가 다음 노드로 찾아내는 노드를 부모 노드로 저장하기?
		visited[0]=true;
		dfs(0);
		
		for(int i=1;i<n;++i) {
			sb.append(parent[i]+1).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int cur) {
		
		for(int next : adjList.get(cur)) {
			if(!visited[next]) {
				visited[next]=true;
				parent[next] = cur;
				dfs(next);
			}

		}
	}
}
