import java.util.*;
import java.io.*;

/*
RE
240727
BJ2138 전구와 스위치 - 골4
 */

public class Main {

    /*
    전구 스위치 누르면 왼/현위치/오 3개의 상태가 토글됨
    처음 상태 > 목표 상태로 만들기 위해 최소 몇 번의 스위치 눌러야 하는지 / 불가능 -1
     */

    static int n;
    static boolean[] start;
    static boolean[] start2;
    static boolean[] goal;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        String str = in.readLine();
        String str2 = in.readLine();
        start = new boolean[n];
        start2 = new boolean[n];
        goal = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (str.charAt(i) - '0' == 0) {
                start[i] = start2[i] = false; // 꺼짐
            } else {
                start[i] = start2[i] = true; // 켜짐
            }

            if (str2.charAt(i) - '0' == 0) {
                goal[i] = false;
            } else {
                goal[i] = true;
            }
        }

        // 전구 i의 최종 상태 = i+1 스위치에 의해 결정됨
        // i번째 스위치 누를지 여부 결정 > i-1번 전구의 상태가 최종 상태와 다르다면 무조건 누른다.
        // 이때, 0번 스위치 누를지 말지 결정할 0-1번 전구가 없기 때문에, 0번 스위치를 누른 상태 / 안 누른 상태 두 가지 경우를 모두 진행.

        // 1. 0번 스위치 누른 경우
        
        // 스위치 누르기..
        start[0] = !start[0];
        start[1] = !start[1];

        int cnt1 = 1; // 0번 누른 거 반영
        for (int i = 1; i < n; ++i) {
            if (start[i - 1] == goal[i - 1]) {
                // 안 누름
            } else {
                // 스위치 누르기
                start[i - 1] = !start[i - 1];
                start[i] = !start[i];
                if (i != n - 1) start[i + 1] = !start[i + 1];

                cnt1++;
            }
        }

        // 1번 경우 결과 판단..
        boolean isSame = true;
        for (int i = 0; i < n; ++i) {
            if (start[i] != goal[i]) {
                isSame = false;
                break;
            }
        }
        if (!isSame) cnt1 = -1;

        // 2. 0번 스위치 안 누른 경우
        int cnt2 = 0;
        for (int i = 1; i < n; ++i) {
            if (start2[i - 1] == goal[i - 1]) {
                // 안 누름
            } else {
                // 누름
                start2[i - 1] = !start2[i - 1];
                start2[i] = !start2[i];
                if (i != n - 1) start2[i + 1] = !start2[i + 1];

                cnt2++;
            }
        }

        // 2번 경우 결과 판단
        isSame = true;
        for (int i = 0; i < n; ++i) {
            if (start2[i] != goal[i]) {
                isSame = false;
                break;
            }
        }
        if (!isSame) cnt2 = -1;

        
        // 종합 
        if (cnt1 == -1) {
            if (cnt2 == -1) {
                System.out.println(-1);
            } else {
                System.out.println(cnt2);
            }
        } else {
            if (cnt2 == -1) {
                System.out.println(cnt1);
            } else {
                System.out.println(Math.min(cnt1, cnt2));
            }
        }
    }
}
