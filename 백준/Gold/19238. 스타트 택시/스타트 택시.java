/*
241006
17:24
BJ19238 스타트 택시 - 골2
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    m명 승객
    n*n 영역

    1. 현재 위치에서 최단거리가 가장 짧은 승객
    2. ... 중 행 번호가 가장 작은 승객
    3. ... 중 열 번호가 가장 작은 승객
    기준으로 다음 탑승객 선정.

    한 칸 이동=연료 1 소모
    승객 이동 완료=승객 이동에 소모한 연료*2 충전
    이동 중 연료 0 되면 이동 실패+업무 끝
     */

    static class Passenger implements Comparable<Passenger> {
        int sx, sy, ex, ey;
        boolean moved;

        Passenger(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.moved = false;
        }

        @Override
        public int compareTo(Passenger o) {
            // sx 작은 승객 -> sy 작은 승객 우선
            if (this.sx < o.sx) {
                return -1;
            } else if (this.sx == o.sx) {
                if (this.sy < o.sy) {
                    return -1;
                } else if (this.sy == o.sy) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    static class Node {
        int x, y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int n, m, fuel;
    static int[][] arr;
    static int taxiX, taxiY;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                arr[i][j] = (-1) * Integer.parseInt(st.nextToken()); // 벽 : -1
            }
        }

        // 택시 시작 위치
        st = new StringTokenizer(in.readLine());
        taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxiY = Integer.parseInt(st.nextToken()) - 1;
//        System.out.println("택시 시작 위치:" + taxiX + "." + taxiY);

        // 승객의 시작점/도착점 기록
        List<Passenger> passengers = new ArrayList<>();
        int psNum = 1; // 승객 번호 : 1~m
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            Passenger p = new Passenger(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            passengers.add(p);
            arr[p.sx][p.sy] = psNum++;
        }


        for (int a = 0; a < m; ++a) {
//            System.out.println("------ " + a + "번째 운행 시작 ------");
            // 1. taxiX, taxiY에서 시작해서 최단거리 승객 찾기...
            Passenger passenger = null; // 이번에 이동시킬 승객

            if (arr[taxiX][taxiY] > 0 && !passengers.get(arr[taxiX][taxiY] - 1).moved) { // 택시 위치에 승객 있는지 확인

//                System.out.println("택시 위치에 승객 있어서 태움");
                passenger = passengers.get(arr[taxiX][taxiY] - 1); // 거리 0 이동해서 태움

            } else { // 없으면 최단거리 찾으러 감
                Queue<Node> queue = new ArrayDeque<>();
                boolean[][] visited = new boolean[n][n];
                queue.add(new Node(taxiX, taxiY, 0));
                visited[taxiX][taxiY] = true;

                boolean found = false;
                List<Passenger> minDists = new ArrayList<>();
                int minDist = Integer.MAX_VALUE;

                while (!queue.isEmpty()) {
                    Node cur = queue.poll();

                    for (int i = 0; i < 4; ++i) {
                        int nx = cur.x + dx[i];
                        int ny = cur.y + dy[i];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && arr[nx][ny] != -1 && !visited[nx][ny] && fuel >= cur.dist + 1) { // 이동 가능한 칸
                            visited[nx][ny] = true;

                            // 이 칸에 승객 있는지 확인
                            if (arr[nx][ny] > 0 && !passengers.get(arr[nx][ny] - 1).moved) { // 있으면
//                                System.out.println(arr[nx][ny]+"번 손님 찾음. 거리:"+(cur.dist+1));

                                if (!found) { // 1-1) 첫 승객 찾으면, 거리/행/열 기록하고 거리 동일한 승객 더 있는지 찾기 + 여럿일 경우 기준에 따라 선정
                                    found = true;
                                    minDist = cur.dist + 1;
                                    minDists.add(passengers.get(arr[nx][ny] - 1));
                                } else { // 승객 찾았지만 첫 승객은 아닌 경우
                                    if (minDist == cur.dist + 1) {
                                        minDists.add(passengers.get(arr[nx][ny] - 1));
                                    }
                                }
                            }

                            if (minDist >= cur.dist + 1) {
                                queue.add(new Node(nx, ny, cur.dist + 1));
                            }
                        }
                    }
                    // 1-1) 첫 승객 찾으면, 거리/행/열 기록하고 거리 동일한 승객 더 있는지 찾기 + 여럿일 경우 기준에 따라 선정
                    // 1-2) 연료 떨어질 때까지 승객 못 찾으면 -1 출력하고 종료
                }

                if (minDists.isEmpty()) {
                    System.out.println(-1);
                    return;
                } else {
                    Collections.sort(minDists);
                    fuel -= minDist;
                    passenger = minDists.get(0);
                    taxiX = passenger.sx;
                    taxiY = passenger.sy;
                    minDists.get(0).moved = true;
                }
            }

//            System.out.println("태운 승객 번호:" + arr[passenger.sx][passenger.sy] + " 위치:" + passenger.sx + "." + passenger.sy);

            // 2. 태운 승객 sx, sy에서 ex, ey로 이동시키기...
            Queue<Node> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];
            queue.add(new Node(passenger.sx, passenger.sy, 0));
            visited[passenger.sx][passenger.sy] = true;

            boolean check = false;
            while (!queue.isEmpty() && !check) {
                Node cur = queue.poll();

                for (int i = 0; i < 4; ++i) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && arr[nx][ny] != -1 && !visited[nx][ny] && fuel >= cur.dist + 1) {
                        // 이동..
                        visited[nx][ny] = true;

                        if (passenger.ex == nx && passenger.ey == ny) { // 도착점인 경우
                            //택시 이동 + 충전
//                            System.out.println("이동에 걸린 거리:" + (cur.dist + 1));
                            taxiX = nx;
                            taxiY = ny;
//                            System.out.println("이동 후 택시위치:" + taxiX + "." + taxiY);
                            fuel += cur.dist + 1;
//                            System.out.println("충전 후 연료:" + fuel);
                            passenger.moved = true;
                            check = true;
                            break;
                        } else {
                            queue.add(new Node(nx, ny, cur.dist + 1));
                        }
                    }
                }
            }

            // 2-1) 연료 안에 이동 가능한 경우, 이동시키고 (이동 거리*2) 충전
            // 2-2) 연료 안에 이동 불가한 경우, -1 출력하고 종료
            if (!check) {
                System.out.println(-1);
                return;
            }
        }

        // 모든 승객 이동 마친 경우!
        System.out.println(fuel);
    }


}
