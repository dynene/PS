import java.util.*;
import java.io.*;

/*
240809
BJ1253 좋다 - 골4
re
 */

public class Main {

    static int n; // 최대 2000
    static int[] arr; // 수 최대 10억

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        /*
        값 같아도 위치 다르면 다른 수
         */
        int result = 0;


        for (int i = 0; i < n; ++i) {
            // 다른 수 2개의 합으로 나타낼 수 있는지 확인 > 자기 자신은 포함되면 안 됨
            int start = 0;
            int end = n - 1;
            while (start < end && start >= 0 && end < n) {
                if (arr[start] + arr[end] == arr[i]) {
                    if (start == i) {
                        start++;
                        continue;
                    }
                    if (end == i) {
                        end--;
                        continue;
                    }

                    result++;
                    break;
                } else if (arr[start] + arr[end] < arr[i]) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(result);

    }
}
