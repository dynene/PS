import java.util.*;
import java.io.*;

/*
240907
16:40
BJ18114 블랙 프라이데이 - 골5
 */

public class Main {

    // 물건 개수 n <= 5000, 선택할 수 있는 물건 최대 3개
    // 합해서 맞춰야 할 무게 c <= 10^8
    // 각 물건 무게 <= 10^8 => 100,000,000 3개 더해도 int 안넘는데..
    // 합해서 c 만족하는 조합 있으면 1, 없으면 0 출력하기
    // 같은 물건 중복 선택 불가능!!!

    static int n, c;
    static int[] weights;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        weights = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(weights);

        // 1개로 만들기
        // c 있는지 탐색
        int idx = find(c);
        if (idx >= 0) {
            System.out.println(1);
            return;
        }

        // 2개로 만들기
        // 1개 고르고 + 나머지 탐색 // int 가능
        for (int i = 0; i < n; ++i) {
            int left = c - weights[i];
            if (left < 0) break;
            else {
                // left 있는지 탐색
                idx = find(left);
                if (idx >= 0 && idx != i) {
                    System.out.println(1);
                    return;
                }
            }
        }


        // 3개로 만들기
        // 조합 2개 + 나머지 탐색
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) continue;

                int left = c - weights[i] - weights[j];
                if (left < 0) break;
                idx = find(left);
                if (idx >= 0 && idx != i && idx != j) { // 찾더라도, weights[i]나 weights[j]면 안 됨
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }

    static int find(int num) {
        int start = 0;
        int end = n - 1;
        while (true) {
            if (start > end) break;

            int mid = (start + end) / 2;

            if (weights[mid] == num) {
                return mid;
            } else if (weights[mid] > num) {
                end = mid - 1;
            } else if (weights[mid] < num) {
                start = mid + 1;
            }
        }
        return -1;
    }
}
