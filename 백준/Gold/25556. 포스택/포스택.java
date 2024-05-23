import java.util.*;
import java.io.*;

/*
240523
BJ25556 포스택 - 골5
 */

public class Main {

    /*
    1~N이 임의 나열된 수열 A
    스택 4개

    1. A의 원소를 앞 원소부터 순서대로 4개 스택 중 하나에 push
    2. A의 모든 원소를 스택에 넣었다면, 4개 중 원하는 스택에서 하나씩 pop하여 모든 원소를 꺼냄
    3. 꺼낸 수들을, 꺼낸 순서대로 왼 <- 오 방향으로 나열. (처음 꺼낸 수가 맨 뒤)

    주어진 순열에 대해 청소 가능 여부 판단하기
     */

    static int n;
    static int[] stack;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        stack = new int[4];
        int pt = 0;
        boolean available = false;

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            int cur = Integer.parseInt(st.nextToken());
            available = false;

            for (int j = 0; j < 4; ++j) {
                if (stack[j] < cur) {
                    stack[j] = cur;
                    available = true;
                    break;
                }
            }

            if (!available) {
                System.out.println("NO");
                return;
            }
        }

        // 스택 top 보다 작은 수가 들어가야 하는 상황이 오면 끝남......
        System.out.println("YES");
    }
}
