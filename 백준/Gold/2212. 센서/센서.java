import java.util.*;
import java.io.*;

/*
240901
01:00
BJ2212 센서 - 골5
 */

public class Main {

    /*
    n개 센서
    최대 k개의 짖ㅂ중국 설치 가능
    n개의 센서가 적어도 하나의 집중국과 통신 가능하게, 집중국 수신가능영역 길이의 합 최소화

    각 집중국의 수신 가능영역의 거리의 합의 최솟값 구하기
     */

    static int n;
    static int k;
    static int[] points;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());
        k = Integer.parseInt(in.readLine());

        points = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            points[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(points);

        if (n <= k) {
            System.out.println(0);
            return;
        }

        int[] dist = new int[n-1];
        for (int i = 0; i < n - 1; ++i) {
            dist[i] = points[i + 1] - points[i];
        }

        Arrays.sort(dist);

//        System.out.println(Arrays.toString(dist));

        int result = 0;
        for (int i = 0; i < n - k; ++i) {
            result += dist[i];
        }

        System.out.println(result);
    }
}
