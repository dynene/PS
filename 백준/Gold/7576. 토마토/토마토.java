
/*
 * BOJ 7576 - 토마토
 * 골드 5
 * 
 * 메모리
 * 시간
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int n ,m; // 가로 m 세로 n
	static int map[][];
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	static class Point {
		int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		Queue<Point> queue = new ArrayDeque<>();
		
		int empty = 0;
		
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<m;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queue.offer(new Point(i, j)); // 익은 토마토 위치 큐에 넣고 시작
				} else if(map[i][j]==0) {
					empty++;
				}
			}
		}
		
		int day = 0; // 결과 저장할 변수
		
		while(!queue.isEmpty()) {
			
			if(empty==0) {
				break;
			}
			
			day++;
			
			int size = queue.size();
			for(int i=0;i<size;++i) {
				Point cur = queue.poll();
				
				for(int j=0;j<4;++j) {
					int nx = cur.x+dx[j];
					int ny = cur.y+dy[j];
					
					if(nx>=0 && nx<n && ny>=0 && ny<m) {
						if(map[nx][ny]==-1 || map[nx][ny]==1) continue;
						
						map[nx][ny] = 1;
						empty--;
						queue.offer(new Point(nx, ny));
					}
				}
			}
		}
		
		if(empty > 0) System.out.println(-1);
		else System.out.println(day);
	}
}
