import java.util.*;
import java.io.*;

public class Main {

    static int n, k;

    /*
    같은 원소가 k개 이하 들어있는 최장 연속 부분 수열 길이 구하기

     */

    static int[] arr;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 같은 정수를 k개 이하 포함한 최장 연속 부분 수열 길이..
        int[] cnt = new int[100001];

        int start = 0;
        int end = 0;
        while (end < n) {

            if(cnt[arr[end]] < k) {
                cnt[arr[end]]++;
                end++;
            } else {
                cnt[arr[start]]--;
                start++;
            }

            if (result < end - start) {
                result = end - start;
            }
        }

        System.out.print(result);
    }
}
