import java.util.*;
import java.io.*;

/*
240901
BJ2166 다각형의 면적 - 골5
 */

public class Main {

    static int n;
    static int[][] points;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        points = new int[n + 1][2];

        /*
        신발끈 공식이 그래서 뭔데...
        1. 다각형을 구성하는 좌표를 나열한 후, 맨 마지막에 첫 번째 좌표를 한 번 더 추가한다.
        2. 각 좌표의 x좌표를 다음 좌표의 y좌표와 곱하여 모두 더한다.
        3. 각 좌표의 y좌표를 다음 좌표의 x좌표와 곱하여 모두 더한다.
        4. 2의 값에서 3의 값을 뺀 뒤 2로 나눈다.
         */

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        points[n][0] = points[0][0];
        points[n][1] = points[0][1];

        long first = 0;
        long second = 0;

        for (int i = 0; i < n; ++i) {
            first += (long) points[i][0] * points[i + 1][1];
            second += (long) points[i][1] * points[i + 1][0];
        }
        long result = Math.abs(first - second);

        System.out.printf("%.1f", 1d * result / 2);
    }
}
