/*
241110
BJ2015 수들의 합 4 - 골4
https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%EB%B0%B1%EC%A4%80-2015-%EC%88%98%EB%93%A4%EC%9D%98-%ED%95%A9-4-java
 */

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] arr;
    static int[] arrsum;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); // 20만
        k = Integer.parseInt(st.nextToken()); // -20억...20억

        arr = new int[n];
        arrsum = new int[n];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arrsum[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            arrsum[i] = arrsum[i - 1] + arr[i];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        long answer = 0;

        map.put(0, 1);
        for (int i = 0; i < n; ++i) {
            answer += map.getOrDefault(arrsum[i] - k, 0);
            map.put(arrsum[i], map.getOrDefault(arrsum[i], 0) + 1);
        }

        System.out.println(answer);

        /*
        i번째부터 j번째까지의 부분합 = arrsum[j] - arrsum[i]
        k = arrsum[j] - arrsum[i]인 i,j 조합이 몇 개 있는지 찾기
        현재 arrsum[j]를 보고 있다면, 이전까지 arrsum[j] - k가 몇 번 나왔는가?

        => 현재 x번째를 보고 있다면 arrsum[x]-k가 몇 번 나왔는지를 답에 더하고/arrsum[x]가 나왔다고 카운팅

         */


    }
}
