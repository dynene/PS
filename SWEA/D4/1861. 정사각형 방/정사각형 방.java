import java.io.*;
import java.util.*;

public class Solution {
	static int t;
	static int n;
	static int[][] rooms;
	static int result, maxmove;
	static int[] dx = {0, 0, -1, 1}; //상,하,좌,우
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder(); // 출력할 정답을 저장할 stringbuilder sb
		
		t = Integer.parseInt(in.readLine()); // 첫 줄 : 테케 개수 t
		for(int z=1;z<=t;++z) { // 테케 개수 만큼 반복
			n = Integer.parseInt(in.readLine()); // 1<= n <= 10^3
			rooms = new int[n][n]; // rooms n*n으로 초기화
			
			for(int i=0;i<n;++i) { // rooms 입력받기
				st = new StringTokenizer(in.readLine()); // i번째 줄
				for(int j=0;j<n;++j) { // j번째 칸
					rooms[i][j] = Integer.parseInt(st.nextToken()); // rooms[i][j] 채우기
				}
			}
			
			result = 0; // 최대 이동 가능했던 칸의 값 result에 저장
			maxmove = 0; // 지금까지 탐색한 칸들 중 최대 이동 횟수 maxmove
			
			//각 칸에 대해
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					// 여기서 몇 칸 이동 가능한지 세기
					int cnt=1; // 이동 가능한 칸 수
					int x=i;
					int y=j;
					int cur;
					// 상하좌우 보면서, 배열 범위 안&&나보다 1 크면 그 칸으로 이동하고 break
					while(true) {
						boolean canMove=false;
						for(int a=0;a<4;++a) { //dx, dy로 이동
							int newx = x+dx[a];
							int newy = y+dy[a];
							if(newx>=0 && newx<n && newy>=0 && newy<n) { //해당 칸이 배열 내부이면
								if(rooms[newx][newy] == rooms[x][y]+1) { //이동 가능한 방이면
									x = newx;
									y = newy;
									cnt++;
									canMove=true;
									break;
								}
							}
						}
						if(!canMove) break;
					}
					if(cnt<maxmove) {
						continue;
					}
					if(cnt==maxmove) {
						if(rooms[i][j] > result) {
							continue;
						}
					}
					maxmove=cnt;
					result=rooms[i][j];
				}
			}
			// 결과 저장
			sb.append("#").append(z).append(" ").append(result).append(" ").append(maxmove).append("\n");
		}
		System.out.println(sb); // 결과 출력
	}
}
