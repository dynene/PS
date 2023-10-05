import java.io.*;
import java.util.*;

public class Solution {
	
	static int n, m, adj[][];
	static int gtCnt = 0, ltCnt = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(in.readLine());
		for(int z=1;z<=tc;++z) {
			n = Integer.parseInt(in.readLine());
			m = Integer.parseInt(in.readLine());
			adj = new int[n+1][n+1];
			
			StringTokenizer st;
			int a, b;
			for(int i=0;i<m;++i) {
				st = new StringTokenizer(in.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}
			
			int answer = 0;
			
			for(int i=1;i<=n;++i) {
				gtCnt = ltCnt = 0;
				
				gtDFS(i, new boolean[n+1]);
				ltDFS(i, new boolean[n+1]);
				
				if(gtCnt+ltCnt == n-1) answer++;
			}
			
			System.out.println("#"+z+" "+answer);
		}
	}
	
	static void gtDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for(int i=1;i<=n;++i) {
			if(adj[cur][i]==1 && !visited[i]) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}
	}
	
	static void ltDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for(int i=1;i<=n;++i) {
			if(adj[i][cur]==1 && !visited[i]) {
				ltCnt++;
				ltDFS(i, visited);
			}
		}
	}
}
