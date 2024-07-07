import java.util.*;
import java.io.*;

/*
240707
BJ2531 회전초밥 - 실1
 */

public class Main {

    static int n, d, k, c;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] cnt = new int[d + 1];
        int[] belt = new int[n];
        for (int i = 0; i < n; ++i) {
            belt[i] = Integer.parseInt(in.readLine());
        }

        // 처음 거 세기
        int curCnt = 0;
        for (int i = 0; i < k; ++i) {
            if (cnt[belt[i]] == 0) {
                curCnt++;
            }
            cnt[belt[i]]++;
        }

        int result = 0;

        for (int i = 0; i < n; ++i) {
            if (curCnt >= result) {
                if (cnt[c] == 0) result = curCnt + 1;
                else result = curCnt;
            }

            // 첫번째 거 빼기
            cnt[belt[i]]--;
            if (cnt[belt[i]] == 0) curCnt--;

            // 다음 거 더하기
            int next = (i + k) % n;
            if (cnt[belt[next]] == 0) curCnt++;
            cnt[belt[next]]++;
        }

        System.out.print(result);
    }
}
