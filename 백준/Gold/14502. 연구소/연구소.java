
import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int map[][];
	static int map2[][];
	
	static int result;
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		map2 = new int[n][m];
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<m;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 벽 세울 세 곳을 어떻게 정하지? 이것도 완탐 아녀?
		dfs(0);
		
		System.out.println(result);
	}
	
	// 벽 세울 수 있는 모든 경우의 수 구해서 안전 영역 크기 구하기
	static void dfs(int cnt) {
		if(cnt==3) {
			for(int i=0;i<n;++i) {
				map2[i] = map[i].clone();
			}
			bfs();
			return;
		}
		for(int i=0;i<n;++i) {
			for(int j=0;j<m;++j) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void bfs() {
		// 현재 위치 세 개에 벽 세웠을 때 안전영역 크기 구하자
		Queue<Point> queue = new ArrayDeque<>();
		for(int i=0;i<n;++i) {
			for(int j=0;j<m;++j) {
				if(map2[i][j]==2) {
					queue.add(new Point(i, j));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int i=0;i<4;++i) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				if(nx>=0 && nx<n && ny>=0 && ny<m && map2[nx][ny]==0) {
					map2[nx][ny]=2;
					queue.offer(new Point(nx, ny));
				}

			}
		}
		
		int tmp=0;
		
		for(int i=0;i<n;++i) {
			for(int j=0;j<m;++j) {
				if(map2[i][j]==0) {
					tmp++;
				}
			}
		}

		if(tmp>result) result=tmp;
		
	}

}
