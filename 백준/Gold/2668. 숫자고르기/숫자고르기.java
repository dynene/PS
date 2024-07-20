import java.util.*;
import java.io.*;

/*
240720
BJ2668 숫자고르기 - 골5
 */

public class Main {

    /*
    첫째줄에서 뽑은 정수 집합 = 첫째줄 뽑은 수 아래 수 집합
    최대로 많이 뽑는 방법 찾기...
     */

    static int n;
    static int[] arr;

    static boolean[] v1;
    static boolean[] v2;

    static boolean[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        int resultCnt = 0;
        result = new boolean[n + 1];

        for (int i = 1; i <= n; ++i) {
            if (result[i]) continue;

            v1 = new boolean[n + 1];
            v2 = new boolean[n + 1];

            // i부터 탐색 시켜보고
            dfs(i);

            // v1이랑 v2 상태가 똑같으면
            boolean check = true;
            for (int j = 1; j <= n; ++j) {
                if (v1[j] != v2[j]) {
                    check = false;
                    break;
                }
            }

            // v1, v2의 true인 원소를 result에서 true 처리
            if (check) {
                for (int j = 1; j <= n; ++j) {
                    if (v1[j]) {
                        result[j] = true;
                        resultCnt++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(resultCnt).append("\n");
        for (int j = 1; j <= n; ++j) {
            if (result[j]) sb.append(j).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int cur) {
        if (v1[cur]) return;
        // 첫째 줄에서 cur을 뽑았음
        v1[cur] = true;
        v2[arr[cur]] = true;
        dfs(arr[cur]);
    }
}
