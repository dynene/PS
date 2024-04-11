
import java.io.*;
import java.util.*;

public class Solution {
	static int h, w;
	static int n;
	static char[][] map;
	
	static int dx[] = {-1, 1, 0, 0}; // 상 하 좌 우
	static int dy[] = {0, 0, -1, 1};
	static char dirchar[] = {'^', 'v', '<', '>'};
	static int dir;
	
	static int curx, cury;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받아들이기 위한 br
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; // 한 줄 입력후 토큰으로 쪼개 사용하기 위한 st
		StringBuilder sb = new StringBuilder(); // 결과 출력을 위한 sb
		
		int t = Integer.parseInt(in.readLine().trim());
		for(int z=1;z<=t;++z) {
			st = new StringTokenizer(in.readLine().trim());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char[h+2][w+2]; // 가장자리도 강철 벽으로 처리하기 위해 가로 세로 +2씩 더해서 만들어줌
			
			// map 테두리 강철벽(#)으로 두르기
			for(int i=0;i<h+2;++i) {
				map[i][0] = map[i][w+1] = '#';
			}
			for(int i=0;i<w+2;++i) {
				map[0][i] = map[h+1][i] = '#';
			}
			
			// map 입력받기
			for(int i=1;i<=h;++i) { // h행만큼
				String tmp = in.readLine().trim();
				for(int j=1;j<=w;++j) { // w열만큼
					// map 내용 넣기
					char cur = tmp.charAt(j-1);
					map[i][j] = cur;
					
					if(cur=='^'||cur=='v'||cur=='<'||cur=='>') {
						curx=i;
						cury=j;
						
						if(cur=='^') {
							dir=0;
						} else if(cur=='v') {
							dir=1;
						} else if(cur=='<') {
							dir=2;
						} else if(cur=='>') {
							dir=3;
						}
					}
				}
			}
			
			// 사용자 입력 개수 n개
			n = Integer.parseInt(in.readLine().trim());
			
			String tmp = in.readLine().trim();
			
			for(int i=0;i<n;++i) { // 사용자 입력 n개 차례대로 수행
				char instr = tmp.charAt(i);

				if(instr == 'S') { // 현재 바라보는 방향으로 포탄 발사(벽돌/강철 만날 때까지 진행, 벽돌이면 깨트리고 멈춤)
					// 포탄발사~~
					// 포탄은 물도 넘고.. 평지도 넘고.. #이나 * 만날 때까지 dir 방향으로 ++
					int nx=curx+dx[dir];
					int ny=cury+dy[dir];
					while(true) {
						if(map[nx][ny]=='#') {
							break;
						} else if(map[nx][ny] == '*') {
							map[nx][ny]='.';
							break;
						} else {
							// 다음 진행 ㄱㄱ
							nx+=dx[dir];
							ny+=dy[dir];
						}
					}
					
				} else { // 이동 명령인 경우
					
					// 각 방향에 맞게 dir 설정해주기
					if(instr=='U') {
						dir=0;
					} else if(instr=='D') {
						dir=1;
					} else if(instr=='L') {
						dir=2;
					} else if(instr=='R') {
						dir=3;
					}
					
					// 바라보는 방향의 다음 칸 평지인지 탐색
					int nx=curx+dx[dir];
					int ny=cury+dy[dir];
					
					if(map[nx][ny]=='.') { // 다음 칸 평지인 경우
						
						map[curx][cury]='.'; // 현재 칸 평지로 만들고
						curx=nx; // 다음 칸으로 이동시키기
						cury=ny;
						
					} else { // 평지 아닌 경우
						//do nothing
					}
					
					map[curx][cury] = dirchar[dir]; // 업데이트 된 nx, ny에 해당하는 칸에 현재 이동 방향에 맞는 전차 모양 넣어주기
 				}
			}
			
			
			// 결과 저장
			sb.append("#").append(z).append(" ");
			for(int i=1;i<=h;++i) {
				for(int j=1;j<=w;++j) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
		} // 테케 끝
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
}