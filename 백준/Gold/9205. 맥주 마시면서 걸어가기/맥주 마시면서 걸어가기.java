
/*
 * 231025
 * BOJ 9205 맥주 마시면서 걸어가기
 * 골드 5
 * 
 * 메모리 kb
 * 시간 ms
 */

import java.util.*;
import java.io.*;

public class Main {
	
	/*
	 * 길이 1000 이하인 정점간에 양방향 간선 연결한 그래프 만들기
	 * bfs 돌리기
	 */
	
	static int n;
	static List<List<Integer>> adjList;
	
	static int[] x;
	static int[] y;
	
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine());
		
		for(int z=1;z<=t;++z) {
			boolean result = false;
			
			n = Integer.parseInt(in.readLine());
			
			adjList = new ArrayList<>();
			for(int i=0;i<n+2;++i) { // 시작점, 도착점 포함
				adjList.add(new ArrayList<Integer>());
			}
			
			visited = new boolean[n+2];
			x = new int[n+2];
			y = new int[n+2];

			for(int i=0;i<n+2;++i) {
				st = new StringTokenizer(in.readLine());
				x[i] = Integer.parseInt(st.nextToken());
				y[i] =  Integer.parseInt(st.nextToken());
			}
			
			for(int i=0;i<n+2;++i) {
				for(int j=i;j<n+2;++j) {
					int dist = Math.abs(x[i]-x[j]) + Math.abs(y[i]-y[j]);
					if(dist <= 1000) {
						// 양방향 간선 추가
						adjList.get(i).add(j);
						adjList.get(j).add(i);
					}
				}
			}
						
			Queue<Integer> queue = new ArrayDeque<>();
			queue.add(0);
			visited[0] = true;
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				if (cur==n+1) {
					result = true;
					break;
				}
				
				for(int i : adjList.get(cur)) {
					if(visited[i] == false) {
						visited[i] = true;
						queue.offer(i);
					}
				}
			}
			
			if(result==true) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
