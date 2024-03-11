import java.util.*;
import java.io.*;

/*
240311
BJ1654 랜선 자르기 - 실버 2

항상 divide by zero 같은 예외 경우 고려하기!!
Integer.MAX_VALUE 해놓고 그 값에 +1 하는 코드를 쓰면 어떡하니 ...
 */

public class Main {

    /*
    k개의 랜선을 가지고 길이가 모두 동일한 n개 이상의 랜선 만들 때
    만들 수 있는 랜선의 최대 길이?

    k=3 n=3
    1000 / 1000 / 1인 경우 (반례!!)

    k=5, n=5이고
    각 길이가 2 / 2 / 2 / 2 / 2인 경우
     */

    static int k, n;
    static int[] length; // 길이 최대값: int 범위 이내

    static long maxlen;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        length = new int[k];
        maxlen = 1;

        for (int i = 0; i < k; ++i) {
            int tmp = Integer.parseInt(in.readLine());
            length[i] = tmp;
        }

        Arrays.sort(length); // 길이 오름차순 정렬

        // 이분 탐색...
        long start = 1;
        long end = Integer.MAX_VALUE;

        while (true) {
//            System.out.println("start: " + start + " end: " + end);

            if (start > end) break;

            long mid = (start + end) / 2;
//            System.out.println("mid: " +mid);

            // 길이 mid일 때 만들 수 있는 랜선 개수 구하기
            long cnt = 0;
            for (int i = 0; i < k; ++i) {
                cnt += length[i] / mid;
            }

            if (cnt < n) { // n개 이상 랜선 만들 수 없는 경우

                // mid가 너무 긺 ..
                // 길이 더 짧게 해야 함
                end = mid - 1;

            } else { // n개 이상 랜선 만들 수 있는 경우

                // mid 길이 더 길게 할 수 있는지 확인해야 함
                // mid 길이를 최대한 늘리면서 쪼갠 조각이 n개 이상이 되는 mid 최댓값 찾기
                if (maxlen < mid) {
                    maxlen = mid;
                }
                start = mid + 1;

            }
        }

        System.out.println(maxlen);
    }
}