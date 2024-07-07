import java.util.*;
import java.io.*;

/*
RE
240707
BJ7490 - 골5
 */

public class Main {

    static int n;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int t = Integer.parseInt(in.readLine());
        for (int z = 0; z < t; ++z) {
            n = Integer.parseInt(in.readLine());

            dfs(1, 1, 0, 1, "1");
            
            // 결과 sb에 넣기
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int cur, int num, int sum, int sign, String str) {
        if (n == cur) { // 수식 완성
            // 값 0인지 확인하기
            if ((num * sign) + sum == 0) {
                sb.append(str).append("\n");
            }
            return;
        }

        // ' '
        dfs(cur + 1, (num * 10) + (cur + 1), sum, sign, str + " " + (cur + 1));
        // '+'
        dfs(cur + 1, cur + 1, sum + (num * sign), 1, str + "+" + (cur + 1));
        // '-'
        dfs(cur + 1, cur + 1, sum + (num * sign), -1, str + "-" + (cur + 1));
    }
}
