import java.util.*;
import java.io.*;

/* BOJ 1012 유기농 배추
 * 실버 2
 * 512MB / 1초
 */

public class Main {

	/*
	 * 상하좌우로 인접한 1으로 이루어진 구역 개수 구하기...
	 */

	static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };

	static int[][] map;

	static int t;
	static int m, n, k;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(in.readLine());

		for (int z = 1; z <= t; ++z) {
			st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken()); // 밭 가로
			n = Integer.parseInt(st.nextToken()); // 밭 세로
			k = Integer.parseInt(st.nextToken());

			map = new int[m][n]; // n행 m열...

			int x, y;
			for (int i = 0; i < k; ++i) {
				st = new StringTokenizer(in.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				// (y.x) = 각 지렁이 위치
				map[x][y] = 1;
			}

			int result = 0;

			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (map[i][j] == 1) { // 새로운 구역 발견
						dfs(i, j);
						result++;
					}
				}
			}

			sb.append(result).append("\n");

		}

		System.out.println(sb.toString());
	}

	static void dfs(int x, int y) {
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
				if (map[nx][ny] == 1) {
					map[nx][ny] = 0;
					dfs(nx, ny);
				}
			}
		}
	}

}
