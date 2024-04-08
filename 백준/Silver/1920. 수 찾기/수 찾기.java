import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
240408
BJ1920 수 찾기 - 실버 4
 */

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        m = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        boolean included;
        for (int i = 0; i < m; ++i) {
            int cur = Integer.parseInt(st.nextToken());
            included = false;
            // 여기서 확인
            // 이분 탐색?

            int start = 0;
            int end = n - 1;
            while (true) {
                if (start > end) break;
                int mid = (start + end) / 2;
                if (cur < arr[mid]) {
                    end = mid - 1;
                } else if (cur > arr[mid]) {
                    start = mid + 1;
                } else {
                    // 정답 찾음
                    included = true;
                    break;
                }
            }


            if (included) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
