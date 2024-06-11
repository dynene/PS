import java.util.*;
import java.io.*;

/*
240611
BJ1927 최소 힙 - 실2
 */

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            int x = Integer.parseInt(in.readLine());
            if (x == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }

            } else {
                pq.add(x);
            }
        }

        System.out.print(sb);
    }
}
