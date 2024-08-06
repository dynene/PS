import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        result = Integer.MAX_VALUE;

        /*
        문자열 길이 1000.
        문자열 중 a 개수 세기
         */

        int cntA = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == 'a') {
                cntA++;
            }
        }

        result = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); ++i) { // 문자열의 모든 글자에 대해서...
            int cntB = 0;
            for (int j = 0; j < cntA; ++j) { // a 개수만큼 탐색
                int tmp = (i + j) % str.length();
                if (str.charAt(tmp) == 'b') { // 탐색 중 찾은 b 개수 = i에서 a연속이 시작될 때의 교환 횟수
                    cntB++;
                }
            }
            result = Math.min(result, cntB);
        }

        System.out.println(result);
    }
}
