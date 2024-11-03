import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;

	static int n, k, l;

	static int dir;
	static int result;

	static int dx[] = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int dy[] = { 0, 1, 0, -1 };

	static class Move {
		int x;
		int y;
		char c;

		Move(int x, char c) { // 방향 체크
			this.x = x;
			this.c = c;
		}

		Move(int x, int y) { // 뱀
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(in.readLine());
		map = new int[n + 2][n + 2]; // 보드 초기화 (1~n, 1~n)

		for (int i = 0; i < n + 2; ++i) { // 테두리에 벽 두르기
			map[i][0] = -1;
			map[i][n + 1] = -1;
			map[0][i] = -1;
			map[n + 1][i] = -1;
		}

		k = Integer.parseInt(in.readLine());
		for (int i = 0; i < k; ++i) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 사과 1
		}

		l = Integer.parseInt(in.readLine());
		Queue<Move> queue = new ArrayDeque<>();
		for (int i = 0; i < l; ++i) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()); // 시작 시간으로부터 x초가 끝난 뒤 c 방향으로 회전
			char c = st.nextToken().charAt(0); // L: 왼쪽으로 방향 회전, D: 오른쪽으로 방향 회전
			queue.offer(new Move(x, c));
		}

		dir = 1; // 오른쪽 보고 시작 = 1 // 상 0 우 1 하 2 좌 3
		result = 0;
		Deque<Move> snake = new ArrayDeque<>();
		snake.addLast(new Move(1, 1));

		while (true) { // 게임 ㄱㄱ
			// 초 세기
			result++;

			// 뱀 머리 한 칸 늘리기
			Move head = snake.peekFirst();
			int nx = head.x + dx[dir];
			int ny = head.y + dy[dir];

			// 벽에 닿았거나 자기 몸에 닿았는지 확인하기
			if (map[nx][ny] == -1) { // 닿았으면 게임 끝
				break;
			} else if (map[nx][ny] == 1) { // 나아간 칸이 사과면...
				// 사과 없애고 뱀으로 채우기
				map[nx][ny] = -1;
				snake.addFirst(new Move(nx, ny)); // 머리 늘리기 - 앞에 넣어야 함
				// 꼬리 그대로
			} else if (map[nx][ny] == 0) { // 빈 칸이면...
				// 뱀 채우기
				map[nx][ny] = -1;
				snake.addFirst(new Move(nx, ny)); // 머리 늘리기 - 앞에 넣어야 함
				// 꼬리 줄이기
				Move tail = snake.pollLast(); // 뒤에서 빼야 함
				map[tail.x][tail.y] = 0; // 지나간 자리 0
			}

			// 이번 초가 끝났으니까 방향 바꿔야 하는지 확인하기
			Move nextmove = queue.peek();
			if(nextmove!=null) {
				if (result == nextmove.x) {
					queue.poll(); // 큐에서 뽑기
					if (nextmove.c == 'L') { // 왼쪽으로 회전
						if (dir == 0)
							dir = 3;
						else
							dir--;
					} else if (nextmove.c == 'D') { // 오른쪽으로 회전
						if (dir == 3)
							dir = 0;
						else
							dir++;
					}
				}
			}

		}

		System.out.println(result);
	}

}
