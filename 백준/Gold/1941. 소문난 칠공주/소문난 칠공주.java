/**
 * 230923
 * BOJ 1941 소문난 칠공주
 * 골드 3
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * '소문난 칠공주' ...
	 * 5*5 자리표, S/Y로 구성
	 * 그중 7명 그룹 정하는 경우의 수
	 * 7명의 자리는 서로 가로/세로로 인접
	 * S가 반드시 4개 이상
	 */
	
	static int[][] map;
	static boolean[][] include;

	static int result;
	
	static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[5][5]; // 자리 배치도 저장할 배열 map
		
		for(int i=0;i<5;++i) {
			String str = in.readLine();
			for(int j=0;j<5;++j) {
				char c = str.charAt(j);
				if(c=='S') {
					map[i][j] = 1; // 이다솜파 = S = 1
				} else {
					map[i][j] = 2; // 임도연파 = Y = 2 로 저장
				}
			}
		}

		/*
		 * > 조건에 어긋나지 않으면서 길이 7이 될 때까지, 4방향 탐색 이어가기
		 * 그리고 그 값을 set에 담기
		 * .. 시간초과 날듯
		 * 
		 * > 25개 중에 중복 없이 7개 고르기
		 * 근데 그게 이어져 있어야 함 => np로 조합 구하고 확인
		 * .. 시간초과 날듯
		 * 
		 * > 꼭 한 줄이어야 하는 것도 아니고 여러 갈래로 나갈 수도 잇음 ..
		 */
		
		// np 써서 해보기...
		int[] p = new int[25]; // 25개 중 7개 선택하기
		int cnt=0; 
		while(++cnt<=7) p[25-cnt]=1; // p 에 7개의 1 넣기
		
		include = new boolean[5][5];
		
		do {
			int sx=-1;
			int sy=-1; // 처음 등장한 true 좌표. 여기서부터 탐색 시작
			int cntS = 0;

			for(int i=0;i<5;++i) { // 선택된 7명 학생 include 배열에 체크하기
				for(int j=0;j<5;++j) {
					
					int idx = 5*i + j; // index 구하기 = i*5+j
					
					if(p[idx]==1) { // 현재 탐색에서 7공주에 포함된 학생이면
						include[i][j] = true;
						
						if(map[i][j]==1) { // 이다솜파 학생이면
							cntS++;
						}
						
						if(sx==-1) {
							sx=i;
							sy=j;
						}
					} else {
						include[i][j] = false;
					}
				}
			}
			
			// 고른 7명 중 이다솜파가 4명 이상인지 체크, 4명 미만인 경우 continue;
			if(cntS < 4) {
				continue;
			}
			
			// include 배열의 true인 칸 7개가 서로 이어져 있는지 체크
			// 시작 좌표: sx, sy
			// 이어져 있는지 확인하려면..어케함 ?ㅆ?
			dfs(sx, sy);
			
			boolean connected = true;
			
			for(int i=0;i<5;++i) {
				for(int j=0;j<5;++j) {
					if(include[i][j]) connected = false;
				}
			}
			
			if(connected) {
				result++;
			}
			
		} while(np(p));
		
		System.out.println(result);
	}

	static void dfs(int x, int y) {
		// sx, sy에서 시작해서
		// 해당 칸의 상하좌우 보면서, true인 경우 false로 만들고 다음 탐색 보내기
		// 끝을 만났거나 false인 경우 return
		// 이걸 끝냈는데 모든 칸이 false이면 이어져있는 것
		for(int i=0;i<4;++i) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0 && nx<5 && ny>=0 && ny<5) { // 배열 범위 내인 경우
				if(include[nx][ny]==true) {
					include[nx][ny] = false;
					dfs(nx, ny);
				}
			}
		}
	}
	
	private static boolean np(int[] p) { // p: 다음 순열을 원하는 기존 순열의 배열
		// 1. 맨 뒤쪽부터  탐색하며 꼭대기 찾기
		int N = p.length;
		int i = N-1;
		while(i>0 && (p[i-1] >= p[i])) --i;
		
		if(i==0) return false; // 다음 순열이 없음(지금이 가장 큰 순열)
		
		// 2. 꼭대기 직전(i-1)위치와 교환할 한 단계 큰 수 찾기
		int j = N-1;
		while(p[i-1] >= p[j]) --j;
		
		// 3. 꼭대기 직전(i-1)위치의 수와 찾아둔 한 단계 큰 수를 교환하기
		swap(p, i-1, j);
		
		// 4. 꼭대기 자리부터 맨뒤까지의 수를 오름차순 형태로 바꾸기
		int k = N-1;
		while(i<k) {
			swap(p, i++, k--);
		}
		
		return true; // 끝~
	}
	
	static void swap(int[] p, int a, int b) {
		int tmp=p[a];
		p[a]=p[b];
		p[b]=tmp;
	}

}
