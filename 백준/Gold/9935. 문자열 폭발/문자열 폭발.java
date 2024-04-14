import java.util.*;
import java.io.*;

/*
240414
BJ 9935 문자열 폭발 - 골드 4
 */
public class Main {

    /*
    폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
    남아있는 문자가 없는 경우 "FRULA"를 출력
     */

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String boomstr = in.readLine();
        int boomlen = boomstr.length();

        // 폭발 문자열의 각 글자에 인덱스를 부여 (어차피 중복 글자 없으니까)
        // 매 글자마다, 인덱스 판단해서 이어지는 경우 스택2에 쌓기

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < boomlen; ++i) {
            map.put(boomstr.charAt(i), i); // 0부터... boomstr 길이까지... 넣기...
        }

        Stack<Character> stack = new Stack<>();
        Stack<Integer> boomstack = new Stack<>();

        // 문제: 폭발 문자열 길이가 1일 때를 고려해야 함 !!!
        // 어떻게?...
        //

        for (int i = 0; i < str.length(); ++i) {
            char cur = str.charAt(i);

            // 어쨋든 일단 넣음
            stack.push(cur);

            // 폭발 문자열에 포함되는 글자인 경우
            if (map.containsKey(cur)) {
                // 1번째 글자이거나 (value가 0이거나)
                // boomstack의 top + 1 글자이거나 (value가 boomstack.peek() + 1 이거나)
                int value = map.get(cur);

                if (value == 0 || (!boomstack.isEmpty() && value == boomstack.peek() + 1)) { // 첫번째 문자인 경우 OR 순서 맞게 쌓인 경우
                    boomstack.push(value);

                    if (boomstack.peek() == boomlen - 1) { // 폭발 문자열 다 쌓인 경우
                        // boomstack에서 빼고..
                        // stack에서도 빼고..
                        for (int j = 0; j < boomlen; ++j) {
                            stack.pop();
                            boomstack.pop();
                        }
                    }
                } else { // 폭발 문자열에 포함되지만, 폭발 조건에 맞지 않는 경우!
                    boomstack.clear();
                }
            } else { // 포함되지 않는 글자인 경우
                // 이때... 쌓이고 있던 boomstack이 있다면 처리를 해 줘야 한다.
                boomstack.clear();
            }

//
//            System.out.println(stack.toString());
//            System.out.println(boomstack.toString());
//            System.out.println();
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }


    }

}
