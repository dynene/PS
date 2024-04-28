import java.util.*;
import java.io.*;

/*
240428
BJ1253 좋다 - 골드 4
 */

public class Main {

    /*
    좋은 수 = 주어진 n개 수 중 다른 두 수의 합으로 나타낼 수 있는 수
    n개의 수 중, 좋은 수의 개수 세기
    위치 다르면 다른 수로 취급!

    n <= 2000
    -10억 <= 수 <= 10억
     */

    static int n;
    static int[] arr;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(in.readLine());
        arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 일단 정렬
        Arrays.sort(arr);

        // 이분 탐색으로 찾아야 할듯 한데...
        // ㅋㅋ 모든 수에 대해 + 모든 나머지 수에 대해 + ... 더해서 그 수가 나오는 경우가 있는지 찾기 > 걍 완탐임..
        // 앞뒤에서 줄이기 . . .
        
        // 투포인터가 뭔데...

        for (int i = 0; i < n; ++i) {
            int cur = i; // 이 수(cur)가 '좋은 수'인지 판단할 차례 !

            //
            int start = 0;
            int end = n - 1;

            while (start < end) {
                if (start < 0 || end >= n) break;

                if (arr[start] + arr[end] == arr[cur]) { // 찾음
                    if (start == cur || end == cur) { // cur 자신이 포함된 경우는 제외해야 함
                        if (start == cur) { // 다음 수로 건너뛰기. start는 더해가는 방향
                            start++;
                        }
                        if (end == cur) { // end는 줄여가는 방향
                            end--;
                        }
                    } else {
                        result++;
                        break;
                    }
                } else { // 아직 못찾
                    if (arr[start] + arr[end] < arr[cur]) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }

        }

        System.out.println(result);
    }
}
