import java.util.*;
import java.io.*;

/*
240522
BJ20437 문자열 게임 2 - 골5
 */

public class Main {

    static int t;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(in.readLine());

        for (int z = 0; z < t; ++z) {
            boolean check = false;

            String w = in.readLine();
            int k = Integer.parseInt(in.readLine());
            if (k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            num = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                num[w.charAt(i) - 'a']++; // 알파벳별로 개수 세기
            }

            int result1 = Integer.MAX_VALUE;
            int result2 = -1;
            for (int i = 0; i < w.length(); ++i) {
                if (num[w.charAt(i) - 'a'] < k) continue;

                int cnt = 1;
                for (int j = i + 1; j < w.length(); ++j) {
                    if (w.charAt(i) == w.charAt(j)) cnt++;
                    if (cnt == k) {
                        check=true;
                        result1 = Math.min(result1, j + 1 - i);
                        result2 = Math.max(result2, j + 1 - i);
                        break;
                    }
                }
            }

            if (check) {
                sb.append(result1).append(" ").append(result2).append("\n");
            } else {
                sb.append(-1).append("\n");
            }

        }

        System.out.println(sb);
    }
}
