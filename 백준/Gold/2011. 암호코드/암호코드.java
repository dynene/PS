import java.util.*;
import java.io.*;

/*
240806
BJ2011 암호코드 - 골5
 */

public class Main {

    static int MOD = 1000000;

    static char[] cyper;
    static int cyperLen;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        cyper = in.readLine().toCharArray();
        cyperLen = cyper.length;

        if (cyper[0] == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[cyperLen + 1];
        dp[cyperLen] = 1;
//        if (cyper[cyperLen - 1] != '0') dp[cyperLen - 1] = 1;
        dp[cyperLen - 1] = 1;

        for (int i = cyperLen - 2; i >= 0; --i) {
            boolean type;
            boolean zero = false;

            if (cyper[i] == '0') {
                if (cyper[i + 1] == '0') {
                    System.out.println(0);
                    return;
                }
                type = false;
            } else if (cyper[i] == '1') {
                if (cyper[i + 1] == '0') { //
                    zero = true;
                    type = false;
                } else {
                    type = true;
                }
            } else if (cyper[i] == '2') {
                if (cyper[i + 1] == '0') {
                    zero = true;
                    type = false;
                } else if (cyper[i + 1] - '0' <= 6) {
                    type = true;
                } else {
                    type = false;
                }
            } else { // 3 이상
                if (cyper[i + 1] == '0') {
                    System.out.println(0);
                    return;
                }
                type = false;
            }

            if (type) {
                dp[i] = (dp[i + 1] + dp[i + 2]) % MOD;
            } else {
                dp[i] = dp[i + 1] % MOD;
            }

            if (zero) {
                if(i>1) {
                    i--;
                    dp[i] = dp[i + 1];
                }
            }

        }

//        System.out.println(Arrays.toString(dp));

        System.out.println(dp[0]);
    }
}
