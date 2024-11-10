/*
241110
BJ2579 계단 오르기 - 실3
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    1. 계단 한번에 1개 or 2개
    2. 연속 3개 밟기 불가능 (시작점 제외)
    3. 마지막 계단 반드시 밟아야 함
     */

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new int[n+1];
        for(int i=1;i<=n;++i){
            arr[i] = Integer.parseInt(in.readLine());
        }

        int[] dp = new int[n+1];
        /*
        i번 계단을 밟을 수 있는 경우의 수
        1) i-1번 계단을 밟고, i번 계단을 밟은 경우
        i번 계단 점수+i-1번 계단 점수+(i-2번은 밟기 불가능)+i-3번까지의 최댓값(여긴 무조건 밟아야함)
        2) i-2번 계단을 밟고, i번 계단을 밟은 경우
        i-2번까지의 최댓값+i번 계단 점수
         */

        if(n==1) {
            System.out.println(arr[1]);
            return;
        }

        dp[1] = arr[1];
        dp[2] = arr[1]+arr[2];
        for(int i=3;i<=n;++i){
            dp[i] = Math.max(arr[i]+arr[i-1]+dp[i-3], arr[i]+dp[i-2]);
        }

        System.out.println(dp[n]);

    }
}
