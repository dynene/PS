import java.io.*;
import java.util.*;

// SWEA 1226 - D4

public class Solution {
	/*
	 * 테케 10개에 20초 -> 시간 되게 넉넉하다..
	 */

	static int[][] map;
	static int sx, sy; // 시작점
	static int fx, fy; // 끝점
	
	static int dx[] = {-1, 1, 0, 0}; // 상 하 좌 우
	static int dy[] = {0, 0, -1, 1};
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int z=1;z<=10;++z) {
			in.readLine(); // 테케 번호 입력 날리기
			
			map = new int[16][16];
			
			for(int i=0;i<16;++i) {
				String str = in.readLine().trim();
				for(int j=0;j<16;++j) {
					map[i][j] = (char) (str.charAt(j)-'0');
					if(map[i][j]==2) {
						sx=i;
						sy=j;
					} else if(map[i][j]==3) {
						fx=i;
						fy=j;
					}
				}
			}
			
			int result=0; // 경로 찾으면 1로 바뀌도록 0을 선언
			boolean notFound=true;
			
			Queue<Point> queue = new ArrayDeque<Point>();
			queue.offer(new Point(sx, sy));
			map[sx][sy] = 1; // 0인 곳만 방문해 나가기 =>1이면 방문한 곳
			
			while(!queue.isEmpty() && notFound) {
				Point cur = queue.poll();
				
				for(int i=0;i<4;++i) {
					int nx=cur.x+dx[i];
					int ny=cur.y+dy[i];
					if(nx>=0 && nx<16 && ny>=0 && ny<16) { // 범위 안이면
						if(map[nx][ny]==0) {
							map[nx][ny]=1;
							queue.offer(new Point(nx, ny));
						}
						else if(map[nx][ny]==3) {
							result=1;
							notFound=false;
							break;
						}
					}
				}
			}
		
			
			// 결과 저장
			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
