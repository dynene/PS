import java.util.*;
import java.io.*;

/*
240509
BJ11729 하노이 탑 이동 순서 - 골드 5
 */

public class Main {

    /*
    3개의 원판으로 하노이탑 하는 데 필요한 최소 횟수 + 원판 이동 순서 출력

    num-1개를 다른곳으로 치우는 식으로 하면 무조건 다음에 num-1을 넣게돼서 k가 항상 n이랑 동일해짐
    num-1개를 다른곳으로 치웠으면, 치웠던것도 목적지로 옮겨야함
     */

    static int n;
    static int k;
    static StringBuilder sb;
    static Stack<Integer> poll1;
    static Stack<Integer> poll2;
    static Stack<Integer> poll3;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(in.readLine());
//        for (int i = n; i > 0; --i) {
//            poll1.push(i);
//        }

        // n개의 원판...
        recur(n, 1, 3);

        System.out.println(k);
        System.out.print(sb);
    }

    static void recur(int num, int from, int to) {
        // 크기 n인? n개의? 원판을 from에서 to로 보내기

        if (num == 1) {
            k++;
//            System.out.println(from+" "+to);
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }
        int to2 = 0;

        if (from == 1) {
            if (to == 2) { // 1에서 2로 num개 보내야 하니까 n-1개를 1에서 3으로
                to2 = 3;
            } else if (to == 3) {
                to2 =2;
            }
        } else if (from == 2) {
            if (to == 1) {
                to2 = 3;
            } else if (to == 3) {
                to2 = 1;
            }
        } else if (from == 3) {
            if (to == 1) {
                to2 = 2;
            } else if (to == 2) {
                to2 = 1;
            }
        }

        recur(num - 1, from, to2);
        k++;
//        System.out.println(from+" "+to);
        sb.append(from).append(" ").append(to).append("\n");
        recur(num-1, to2, to);
    }
}
