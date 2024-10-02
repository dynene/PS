/*
241003
2:52
BJ1062 가르침 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        for (int i = 0; i < n; ++i) {
            words[i] = in.readLine();
        }

        if (k < 5) { // 모든 단어 공통 a, c, i, n, t이 포함됨 (0,2,8,13,19)
            System.out.println(0);
            return;
        }

        int result = 0;

        // k개 알파벳 조합 다 해보기 ...
        int[] p = new int[26];
        // k-5개만큼의 1...
        for (int i = 0; i < k - 5; ++i) {
            p[26 - 1 - i] = 1;
        }

        do {
            // 지금 조합에서 acint 포함했는지 확인
            if (p[0] == 1 || p[2] == 1 || p[8] == 1 || p[13] == 1 || p[19] == 1) continue;

            //a,c,i,n,t 체크
            p[0] = p[2] = p[8] = p[13] = p[19] = 1;

//            System.out.println("p: "+Arrays.toString(p));

            // n개 단어에 대해, words[i].charAt(4) ~ words[i].charAt(길이-4) 까지 보면서... 읽을 수 있는 단어인지 확인
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                boolean check = true;
                for (int j = 4; j < words[i].length() - 4; ++j) {
                    if (p[words[i].charAt(j) - 'a'] == 0) {  // 안 가르친 단어
                        check = false;
                        break;
                    }
                }

                if (check) {
//                    System.out.println(words[i] + " 읽을 수 있음");
                    cnt++;
                }
            }
//            System.out.println("cnt: "+cnt);
            if (cnt > result) result = cnt;

            //a,c,i,n,t 체크 해제
            p[0] = p[2] = p[8] = p[13] = p[19] = 0;

        } while (np(p));


        System.out.println(result);
    }

    static boolean np(int[] p) { // 이정도면 좀 알아서 각인 되라고 제발~~~~
        int i = 26 - 1;
        while (i > 0 && p[i - 1] >= p[i]) --i;
        if (i == 0) return false;

        int j = 26 - 1;
        while (p[i - 1] >= p[j]) --j;
        swap(p, i - 1, j);

        int k = 26 - 1;
        while (i < k) swap(p, i++, k--);

        return true;
    }

    static void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }
}
