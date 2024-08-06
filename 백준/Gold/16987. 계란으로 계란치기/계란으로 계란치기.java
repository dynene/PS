import java.io.*;
import java.util.*;

/*
240806
BJ16987 계란으로 계란치기 - 골5
 */

public class Main {

    /*
    계란 A로 계란 B 치기 > 상대 계란 A 무게만큼 계란 B의 내구도가 깎임
    내구도 0 이하 되는 순간 계란 깨짐

    1. 가장 왼쪽 계란을 들어서
    2. 깨지지 않은 계란 중 하나를 친다. 든 계란이 깨졌거나/안 깨진 계란 없으면 패스.
    3. 들었던 계란의 한 칸 오른쪽 계란을 들고, 2번부터 반복.

    이 방법으로 최대로 깰 수 있는 계란 개수 구하기
     */

    static int n; // 1~8
    static int[][] eggs;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());

        eggs = new int[n][2];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        dfs(0, 0);

        System.out.println(result);
    }

    static void dfs(int order, int cnt) {

        if (order == n) {
            result = Math.max(result, cnt);
            return;
        }

        // order번째 계란 깨졌는지 확인하기
        if (eggs[order][0] <= 0) {
            // 깨졌다면 바로 다음 order로 넘어가기
            dfs(order + 1, cnt);
        } else {
            // 안 깨졌다면 어떤 계란 칠지 찾기
            boolean check = false;
            for (int i = 0; i < n; ++i) {
                if (i == order) continue; // order번째 계란으로 칠 것... 패스

                if (eggs[i][0] > 0) { // 안 깨진 계란이라면
                    check = true;

                    // i번째 계란 치기
                    eggs[i][0] -= eggs[order][1];
                    eggs[order][0] -= eggs[i][1];

                    // 다음 차례 탐색하기
                    int cnt2 = cnt;
                    if (eggs[i][0] <= 0) cnt2++;
                    if (eggs[order][0] <= 0) cnt2++;
                    dfs(order + 1, cnt2);

                    // 복구하기
                    eggs[i][0] += eggs[order][1];
                    eggs[order][0] += eggs[i][1];
                }
            }

            if (!check) dfs(order + 1, cnt);
        }

    }
}
