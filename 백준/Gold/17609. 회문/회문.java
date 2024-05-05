import java.util.*;
import java.io.*;

/*
240505
BJ17609 회문 - 골드 5
 */
public class Main {

    /*

     */

    static int t;
    static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(in.readLine());

        for (int z = 0; z < t; ++z) {
            str = in.readLine();

            // 회문 판단 -> str 앞, 뒤에서 하나씩 짚어 보기
            // 같지 않은 경우 한 번까지는 그냥 넘겨보고 (양쪽으로), 두 번부턴 break
            int start = 0;
            int end = str.length() - 1;
            int result = recur(start, end, 0); // 함수로 빼야 해 ...

            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }

    public static int recur(int start, int end, int deleted) {
        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
            } else {
                if (deleted == 0) {
                    int left = recur(start + 1, end, deleted + 1);
                    int right = recur(start, end - 1, deleted + 1);
                    if (left == 0 || right == 0) return 1;
                    else return 2;
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }
}
