import java.util.*;
import java.io.*;

/*
240801
BJ4949 균형잡힌 세상 - 실4...
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = in.readLine();
            if (".".equals(str)) {
                break;
            }

            boolean check = true;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        check = false;
                        break;
                    } else {
                        stack.pop();
                    }
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        check = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (check && stack.isEmpty()) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }

        System.out.print(sb);
    }
}
