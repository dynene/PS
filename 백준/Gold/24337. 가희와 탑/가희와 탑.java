import java.io.*;
import java.util.*;

/*
240711
BJ24337 가희와 탑 - 골3
 */

public class Main {

    /*
    가희: 1번 건물 볼 수 있음
    k번 건물보다 왼쪽 건물 모두가 k번 건물보다 높이가 낮다면, k번 건물 볼 수 있음
    단비: n번 건물 볼 수 있음
    k번 건물보다 오른쪽 건물 모두가 k번 건물보다 높이 낮다면, k번 건물 볼 수 있음

    건물 개수 n / 왼쪽에서 볼 수 있는 건물 수 a / 오른쪽에서 볼 수 있는 건물 수 b
    사전순으로 가장 앞서는, n개의 건물 높이 정보 출력하기

    건물 높이 같을 수도 있음
    모든 높이 >= 1
     */

    static int n;
    static int a;
    static int b;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        /*
        이걸 어케하지
         */

        if (a + b - 1 > n) {
            System.out.print(-1);
            return;
        }

        List<Integer> list = new LinkedList<>();

        for (int i = 1; i < a; ++i) {
            list.add(i);
        }
        list.add(Math.max(a, b));
        for (int i = b - 1; i > 0; --i) {
            list.add(i);
        }

        for (int i = 0; i < n - (a + b - 1); ++i) {
            list.add(1, 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }
}
