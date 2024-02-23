import java.io.*;

public class Main {

    /*
     * 문자열 S와 T가 주어졌을 때, S를 T로 바꾸기
     * 가능한 연산
     * 1. 문자열 뒤에 A 추가
     * 2. 문자열 뒤에 B 추가하고 문자열 뒤집기
     * 주어진 조건으로 S를 T로 만들 수 있는지 여부 알아내기
     *
     * 1<= S길이 <=49
     * 2<= T길이 <=50
     * S길이 < T길이
     *
     * 가능 1 불가능 0
     */

    static String s, t;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        s = in.readLine();
        t = in.readLine();

        // 접근 : s에서 붙여나가려면 2^50 가지를 다 해봐야 함 => 무조건 시간초과...
        // s에서 붙이는 게 아니라 't에서 떼어 나간다'는 발상!!

        // 연산 1 - t의 맨 뒤에서 A를 뺀다
        // 연산 2 - t를 뒤집고 맨 뒤에서 B를 뺀다

        recur(t);

        System.out.println(result);
    }

    static void recur(String cur) {
        int len = cur.length(); // 전체 문자열 길이

        if (len == s.length()) { // 작업 수행 끝(s랑 길이가 같음) => 확인 필요
            if (cur.equals(s)) {
                result = 1;
            }
            return;
        }

        // 아직 더 빼봐야 하는 경우 => A 빼기 / B 빼기 모두 가능한지 확인
        if(cur.charAt(cur.length()-1) == 'A') {
            recur(cur.substring(0, cur.length()-1));
        }
        if(cur.charAt(0) == 'B') {
            String cur2 = cur.substring(1);
            StringBuffer sb = new StringBuffer(cur2);
            String reversedStr = sb.reverse().toString();
            recur(reversedStr);
        }
    }

}
