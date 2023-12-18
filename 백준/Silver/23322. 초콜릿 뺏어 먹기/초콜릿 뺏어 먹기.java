import java.util.*;
import java.io.*;

/*
BOJ 23322 초콜릿 뺏어 먹기
실버 2
어케풀어 아오 ㅡ.ㅡ
 */

public class Main {
    static int n, k;
    static int[] arr;

    static int maxnum; // 먹을 수 있는 초콜릿의 최대 개수
    static int mindate; // 해당 개수의 초콜릿을 먹기 위해 필요한 최소 날짜 < 최소?..

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken()); // 정렬된 상태로 주어짐
        }

        // k<i인 i를 골라, i-k번째 통의 초콜릿 개수와 똑같아질 때까지 i번째 통에서 초콜릿 꺼내 먹기
        // arr 오름차순 정렬

        // n에 대해 k가 클수록... 선택할 수 있는 경우의 수가 줄어든다...

        while (true) {
            int curi = k;
            for (int i = k + 1; i <= n; ++i) {
                if (arr[i] - arr[i - k] > 0) {
                    curi = i;
                    break;
                }
            }

            if (curi == k) break; // 먹을 수 있는 게 없음...

            maxnum += arr[curi] - arr[curi - k];
            arr[curi] = arr[curi - k];
            mindate++;

            Arrays.sort(arr); // 오름차순 정렬
        }
        System.out.println(maxnum + " " + mindate);
    }
}
