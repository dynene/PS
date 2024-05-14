import java.util.*;
import java.io.*;

/*
240514
BJ2607 비슷한 단어 - 실2
 */

public class Main {

    /*
    - 같은 구성을 갖거나 (= 같은 개수의 같은 알파벳으로 이루어짐)
    - 한 문자를 더하거나/빼거나/바꿔서 같은 구성을 갖게 됨
    => 비슷한 단어.

    word와 '비슷한 단어' 개수 세기
     */

    static int n;
    static int[] word;
    static int result;

    static int[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        String tmp = in.readLine();
        word = new int[26];
        for (int i = 0; i < tmp.length(); ++i) {
            word[tmp.charAt(i) - 'A']++;
        }

        for (int i = 0; i < n - 1; ++i) {
            String str = in.readLine();
            check = new int[26];
            for (int j = 0; j < str.length(); ++j) {
                check[str.charAt(j) - 'A']++;
            }
            // 차이나는 거 0개 = 가능
            // 차이나는 거 1개 & 차이값 +1 OR -1 = 가능
            // 차이나는 거 2개 & 차이값 +1 1개, -1 1개 = 가능
            boolean isSimilar = true;

            int difCnt = 0;
            boolean plus1 = false;
            boolean minus1 = false;

            for (int j = 0; j < 26; ++j) {
                if (word[j] == check[j]) {
                    continue;
                } else if (word[j] - check[j] == 1) { // 차이 +1
                    if (!plus1) {
                        plus1 = true;
                        continue;
                    }
                } else if (word[j] - check[j] == -1) { // 차이 -1
                    if (!minus1) {
                        minus1 = true;
                        continue;
                    }
                }
                // continue 조건에 못 들어간 경우...
                isSimilar = false;
                break;
            }

            if (isSimilar) result++;
        }

        System.out.print(result);
    }
}
