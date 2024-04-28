import java.util.*;
import java.io.*;

/*
240428
전생했더니 슬라임 연구자였던 건에 대하여... 골 4
 */


public class Main {

    static int t;
    static int n;
    static final Long MOD = 1000000007L;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(in.readLine());
        for (int z = 1; z <= t; ++z) {

            n = Integer.parseInt(in.readLine());

            if (n == 1) {
                in.readLine();
                sb.append(1).append("\n");
                continue;
            }

            st = new StringTokenizer(in.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < n; ++i) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            Long result = (long) 1;

            while (pq.size() >= 2) {
                // 맨 처음 거 두 개 뽑아서, 곱한 값을 1) pq에 넣기 2) result에 곱하기 3) result를 MOD로 % 연산 하기
                Long cur1 = pq.poll();
                Long cur2 = pq.poll();
                Long mult = cur1 * cur2;
//                System.out.println(mult);
                pq.add(mult);
                result = result * (mult % MOD) % MOD;
            }

            // (pq.size==1) = 슬라임 합성 완성 .. ^^

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
