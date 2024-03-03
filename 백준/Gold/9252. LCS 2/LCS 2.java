import java.util.*;
import java.io.*;

/*
240227
BJ 9252 LCS 2 - 골드 4
*/

public class Main {

    static String str1, str2;
    static int[][] dp;
    static int lenOfLcs;
    static String lcs;

    /*
    if i == 0 or j == 0:  # 마진 설정
        LCS[i][j] = 0
    elif string_A[i] == string_B[j]:
        LCS[i][j] = LCS[i - 1][j - 1] + 1
    else:
        LCS[i][j] = max(LCS[i - 1][j], LCS[i][j - 1])

    1. 문자열A, 문자열B의 한글자씩 비교해봅니다.
    ** 2. 두 문자가 다르다면 LCS[i - 1][j]와 LCS[i][j - 1] 중에 큰값을 표시합니다. **
    3. 두 문자가 같다면 LCS[i - 1][j - 1] 값을 찾아 +1 합니다.
    4. 위 과정을 반복합니다.
     */

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        str1 = in.readLine();
        str2 = in.readLine();

        dp = new int[str1.length() + 1][str2.length() + 1];

        // str1과 str2의 LCS 길이와 LCS 구하기
        dp();

        // lcs 길이
        lenOfLcs = dp[str1.length()][str2.length()];

        // lcs 구하기
        /*
        1. LCS 배열의 가장 마지막 값에서 시작합니다. 결과값을 저장할 result 배열을 준비합니다.
        2. LCS[i - 1][j]와 LCS[i][j - 1] 중 현재 값과 같은 값을 찾습니다.
        2-1. 만약 같은 값이 있다면 해당 값으로 이동합니다.
        2-2. 만약 같은 값이 없다면 result 배열에 해당 문자를 넣고 LCS[i -1][j - 1]로 이동합니다.
        3. 2번 과정을 반복하다가 0으로 이동하게 되면 종료합니다. result 배열의 역순이 LCS 입니다.
         */
        StringBuilder sb = new StringBuilder();
        int n = str1.length();
        int m = str2.length();
        while (n != 0 && m != 0) {
            if (dp[n][m] == dp[n - 1][m]) {
                n--;
            } else if (dp[n][m] == dp[n][m - 1]) {
                m--;
            } else {
                n--;
                m--;
                sb.append(str1.charAt(n));
            }
        }
        lcs = sb.reverse().toString();

        // 결과 출력
        System.out.println(lenOfLcs);
        if (lenOfLcs != 0) {
            System.out.println(lcs);
        }
    }

    static void dp() {
        for (int i = 0; i <= str1.length(); ++i) {
            for (int j = 0; j <= str2.length(); ++j) {
                if (i == 0 || j == 0) { //마진 설정
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

}
