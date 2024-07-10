import java.util.*;
import java.io.*;

/*
240710
BJ20055 컨베이어 벨트 위의 로봇 - 골5
 */

public class Main {

    /*
    1(올) ... n(내)
    2n ...... n+1 형태인 벨트
    벨트 위에 로봇 올리기

    로봇 항상 (올리는 위치)에 올림, (내리는 위치)도달 시 즉시 내림
    로봇 올릴 때 / 로봇 이동할 때 해당 "칸"의 내구도 1씩 즉시 감소

    로봇 건너편으로 옮기기

    1. 벨트가 각 칸 위 로봇과 함께 한 칸 회전
    2. 가장 먼저 올라간 로봇부터, 회전 방향으로 한 칸 이동 가능하다면 이동. 불가능할 시 이동 X.
        - 이동 조건: 1) 이동할 칸에 로봇이 없고 2) 칸의 내구도가 1 이상이어야 함
    3. (올리는 위치) 칸의 내구도가 1 이상이면, 로봇을 (올리는 위치)에 올린다.
    4. 내구도 0인 칸이 k개 이상이면 종료 / 그렇지 않을 시 1번으로 돌아가서 반복.

    종료되었을 때, 몇 번째 단계가 진행 중이었는지 구하기.
    1단계부터 시작.
     */
    static int n, k;
    static int[][] state; // 각 칸별 내구도
//    static boolean[] robots;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 각 칸 내구도 입력 받기...
        state = new int[2 * n + 1][2];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= 2 * n; ++i) {
            state[i][0] = Integer.parseInt(st.nextToken());
        }
        int curIn = 1;
        int curOut = n;
//        robots = new boolean[n + 1];

        int stage = 1;
        int numOf0 = 0;
        while (true) {
//            System.out.println("stage " + stage + " : curIn " + curIn + " curOut " + curOut);

            // 1. 벨트 회전
            if (curIn == 1) curIn = 2 * n;
            else curIn--;

            if (curOut == 1) curOut = 2 * n;
            else curOut--;

            // 회전하면서 (내리는 위치)로 이동했는지 확인
            if (state[curOut][1] == 1) {
                state[curOut][1] = 0;
            }

            // 2. 로봇 이동시키기
            /*
            모든 로봇 각각에 대해...
            1) 현재 위치 확인하기
            2) 현재 위치+1 칸에 로봇 있는지 확인하기
            3) 현재 위치+1 칸의 내구도 1 이상인지 확인하기
             */
            int cur = curOut;
            for (int i = 1; i <= n; ++i) { // curOut-1 ~ curIn+1 탐색하기
                if (state[cur][1] == 0) {}
                else {
                    //로봇있는칸인경우
                    int nextLoc = 0;
                    if (cur == 2 * n) nextLoc = 1;
                    else nextLoc = cur + 1;

                    if (state[nextLoc][1] == 1 || state[nextLoc][0] < 1) {
                        // do nothing
                    } else { // 이동시키고 내구도 깎기
                        state[cur][1] = 0;
                        state[nextLoc][1] = 1;
                        state[nextLoc][0]--;

                        if (state[nextLoc][0] == 0) numOf0++;

                        if (nextLoc == curOut) state[nextLoc][1] = 0;
                    }
                }

                if (cur == 1) cur = 2 * n;
                else cur--;
            }

            // 3. 로봇 올리기.
            // 올리는 칸에 로봇이 있을 순 없으므로 확인할 필요 X
            if (state[curIn][0] > 0) {
                // 로봇 올리고 내구도 깎기
//                robots.add(new Robot(curOrd++, curIn));
                state[curIn][1] = 1;
                state[curIn][0]--;

                if (state[curIn][0] <= 0) numOf0++;
            }

            // 4. 종료 조건 확인
//            System.out.println("numOf0 " + numOf0);
            if (numOf0 >= k) {
                break;
            } else {
                stage++;
            }
        }

        System.out.print(stage);
    }
}
