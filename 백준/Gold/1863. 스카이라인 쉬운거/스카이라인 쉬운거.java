import java.util.*;
import java.io.*;

/*
240720
BJ1863 스카이라인 쉬운거 - 골4
 */

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());

        Stack<Integer> stack = new Stack<>();

        int result = 0;

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (stack.isEmpty() || stack.peek() < y) {
                if (y != 0) stack.push(y);
            } else { // y가 현재 스택 top보다 작은 값인 경우
                // 스택 top이 y보다 작거나 같아질 때까지...
                while (!stack.isEmpty()) {
                    // 스택 top을 poll 하고... 건물 개수 하나 count
                    if (stack.peek() > y) {
                        stack.pop();
                        result++;
                    } else {
                        break;
                    }
                }

                if (y != 0 && (stack.isEmpty() || stack.peek() < y)) stack.push(y);
            }
        }

        while (!stack.isEmpty()) {
            stack.pop();
            result++;
        }

        System.out.print(result);
    }
}
