/*
240915
18:50
BJ2234 성곽 - 골3
 */

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int m, n;
    static int[][] arr;
    static int[][] room;
//    static boolean[][] visited;

    static int[] dx = {0, -1, 0, 1}; // 좌 상 우 하
    static int[] dy = {-1, 0, 1, 0};

    static int roomCnt;
    static int maxArea;
    static int maxArea2;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m][n];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> areaList = new ArrayList<>();
        room = new int[m][n];
//        visited = new boolean[m][n];
        int cnt = 1;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (room[i][j] == 0) {
                    // i, j부터 시작해서 bfs 탐색
                    int area = 0;

                    Queue<Node> queue = new ArrayDeque<>();
                    queue.add(new Node(i, j));
                    room[i][j] = cnt;
                    area++;

                    while (!queue.isEmpty()) {
                        Node cur = queue.poll();

                        for (int a = 0; a < 4; ++a) {
                            int nx = cur.x + dx[a];
                            int ny = cur.y + dy[a];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if ((arr[cur.x][cur.y] & (1 << a)) == 0) { // 비트마스킹 어케하냐...
                                    if (room[nx][ny] == 0) {
                                        room[nx][ny] = cnt;
                                        area++;
                                        queue.add(new Node(nx, ny));
                                    } else {
                                    }
                                } else {
                                }
                            } else {
                            }
                        }
                    }

                    areaList.add(area); // 방 번호의 a의 넓이 = area.get(a-1);

                    if (maxArea < area) {
                        maxArea = area;
                    }

                    cnt++;
                }
            }
        }


        roomCnt = cnt - 1;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int curNum = room[i][j];
                int curArea = areaList.get(curNum - 1);

                for (int a = 0; a < 4; ++a) {
                    int nx = i + dx[a];
                    int ny = j + dy[a];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (curNum != room[nx][ny]) {
                            int sumArea = curArea + areaList.get(room[nx][ny] - 1);
                            if (maxArea2 < sumArea) {
                                maxArea2 = sumArea;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(roomCnt);
        System.out.println(maxArea);
        System.out.println(maxArea2);

    }

}
