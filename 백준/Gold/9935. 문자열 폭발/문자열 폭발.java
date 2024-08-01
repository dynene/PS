import java.util.*;
import java.io.*;

/*
240801
BJ9935 문자열 폭발 - 골4
다시 풀기~
 */

public class Main {

    static String str;
    static String boomStr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        str = in.readLine();
        boomStr = in.readLine();

        Stack<Character> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        int curCnt = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            stack.push(c);

            if (c == boomStr.charAt(curCnt)) {
                curCnt++;

                if (curCnt == boomStr.length()) { // 다 모였으면.. 터트리기..
                    for (int j = 0; j < boomStr.length(); ++j) {
                        stack.pop();
                    }

                    if (!stack2.isEmpty() && stack.peek() == boomStr.charAt(stack2.peek() - 1)) {
                        curCnt = stack2.pop();
                    } else {
                        curCnt = 0;
                    }
                }
            } else if (c == boomStr.charAt(0)) {
                stack2.push(curCnt); // 지금까지 쌓던 거 넣어놓고...
                curCnt = 1;
            } else {
                // stack2에 쌓인 거 다 지워야 함
                stack2.clear();
                curCnt = 0;
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }
    }
}