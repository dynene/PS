import java.util.*;
import java.io.*;

/*
240609
BJ19637 IF문 좀 대신 써줘 - 실3
 */

public class Main {

    // 출력할 수 있는 칭호가 여러 개인 경우 가장 먼저 입력된 칭호 하나만 출력한다.

    static int n, m;
    static String[] names;
    static int[] values;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        names = new String[n];
        values = new int[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            names[i] = st.nextToken();
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; ++i) {
            int cur = Integer.parseInt(in.readLine());

            int start = 0;
            int end = n - 1;

            while (true) {
                if (start >= end) {
                    sb.append(names[start]).append("\n");
                    break;
                }
                int mid = (start + end) / 2;

                // cur==values[mid]일 때 바로 끝내면 안 됨. 앞쪽에 같은값 더 있는지 확인해줘야 함 ㅠ
                if (values[mid] < cur) {
                    start = mid+1;
                } else {
                    end = mid;
                }
            }
        }

        System.out.print(sb);
    }
}
