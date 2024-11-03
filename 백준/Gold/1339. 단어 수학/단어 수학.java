/*
241103
19:16
BJ1339 단어 수학 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    알파벳을 0~9 숫자 중 하나로 바꿔서 n개의 수를 합하는 문제!
    n개의 단어가 주어질 때, 그 수의 합을 최대로 만들기
     */

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        int[] cnt = new int[26];

        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            for (int j = 0; j < str.length(); ++j) {
                int idx = str.length() - 1 - j;
                int alphabet = str.charAt(j) - 'A';
                cnt[alphabet] += (int) Math.pow(10, idx);
            }
        }

        int result = 0;
        Arrays.sort(cnt);
        int number = 9;
        for (int i = 25; i >= 0; --i) {
            if (cnt[i] == 0) break;

            result += cnt[i] * (number--);
        }

        System.out.println(result);
    }
}
