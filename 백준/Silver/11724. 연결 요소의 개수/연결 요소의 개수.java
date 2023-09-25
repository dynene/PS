/*
 * 230925
 * boj 11724 연결 요소의 개수
 * 실버 2
 * 
 * 메모리 KB
 * 시간 ms
 */

import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * 연결 요소
	 * : 하나의 그래프 안에서 간선으로 연결되지 않아 나누어진 각각의 그래프
	 * 정점 최대 1000개
	 * 무방향 그래프
	 */
	
	static int n, m;
	
	static List<List<Integer>> adjList;
	
	static boolean visited[];
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<List<Integer>>();
		for(int i=0;i<n;++i) {
			adjList.add(new ArrayList<Integer>());
		}
		
		visited = new boolean[n];
		
		for(int i=0;i<m;++i) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1; // 정점 번호 0~n-1이니까 1씩 빼줌
			int to = Integer.parseInt(st.nextToken()) - 1;
			// 양방향 간선 기록하기
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		//0~n-1까지 돌면서
		//visited[i]가 false이면
		//result++하고
		//해당 정점과 연결된 정점을 갈 수 있는 데까지 탐색하면서 방문 체크(dfs)
		for(int i=0;i<n;++i) {
			if(visited[i]==false) {
				result++;
				dfs(i);
			}
		}
		
		System.out.println(result);
	}
	
	static void dfs(int cur) {
		if(visited[cur]==true) {
			return;
		}
		
		visited[cur] = true;
		
		for(int next : adjList.get(cur)) {
			dfs(next);
		}
	}
}
