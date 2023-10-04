
import java.io.*;
import java.util.*;

/*
 * BOJ 3055 탈출
 * 골드 4
 * 
 * 메모리 KB
 * 시간 ms
 */

public class Main {
	
	/*
	 * r행 c열
	 * 고슴도치: 매번 상하좌우 중 하나로 이동 가능
	 * 물: 매번 상하좌우로 확장
	 * 돌: 무조건 통과 불가능
	 * 
	 */
	
	static int r, c;
	static char[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; // 상하좌우
	
	static class Point {
		int x,y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		
		Queue<Point> hedgeq = new ArrayDeque<>();
		Queue<Point> waterq = new ArrayDeque<>();
		
		for(int i=0;i<r;++i) {
			String str = in.readLine();
			for(int j=0;j<c;++j) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j]=='S') { // 고슴도치 위치 - 현위치
					hedgeq.add(new Point(i,j));
				} else if(map[i][j]=='*') { // 물 - 계속해서 확장
					waterq.add(new Point(i,j));
				}
			}
		}
		
		int move = 0;
		
		while(!hedgeq.isEmpty()) {
			
			move++;
			int size = waterq.size();
			for(int i=0;i<size;++i) {
				Point cur = waterq.poll();
				
				for(int j=0;j<4;++j) {
					int nx = cur.x+dx[j];
					int ny = cur.y+dy[j];
					
					if(nx>=0 && nx<r && ny>=0 && ny<c) {
						if(map[nx][ny]=='*' || map[nx][ny]=='X' || map[nx][ny]=='D') continue;
						
						map[nx][ny]='*';
						waterq.add(new Point(nx, ny));
					}
				}
			}
			size = hedgeq.size();
			for(int i=0;i<size;++i) {
				Point cur = hedgeq.poll();
				
				for(int j=0;j<4;++j) {
					int nx = cur.x+dx[j];
					int ny = cur.y+dy[j];
					
					if(nx>=0 && nx<r && ny>=0 && ny<c) {
						if(map[nx][ny]=='*' || map[nx][ny]=='X' || map[nx][ny]=='S') continue;
						
						if(map[nx][ny]=='D') { // 비버굴이면
							System.out.print(move);
							return;
						}
						
						map[nx][ny] = 'S';
						hedgeq.add(new Point(nx,ny));
					}
				}
			}
			
		}
		
		// 여기까지 오면 답 없는 것
		System.out.print("KAKTUS");

	}

}
