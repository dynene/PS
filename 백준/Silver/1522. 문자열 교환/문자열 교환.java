import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        result = Integer.MAX_VALUE;

        // 일단 그룹 수 세기
        int cnt = 0;
        for (int i = 1; i < str.length(); ++i) {
            if (str.charAt(i - 1) != str.charAt(i)) cnt++;
        }
        if (str.charAt(0) != str.charAt(str.length() - 1)) cnt++;


        if (cnt <= 2) { // 1이거나 2면 교환할 필요 X
            System.out.println(0);
            return;
        }

        // a그룹 수 = b그룹 수 = cnt/2

        for (int i = 1; i <= cnt / 2; ++i) {
            // 기준점 = i번째 b그룹의 시작 인덱스
            int standard = 0;

            // str 탐색하면서 i번째 b그룹 찾기
            int tmp = 0;
            for (int j = 0; j < str.length(); ++j) {
                if (str.charAt(j) == 'b') {
                    if (j == 0) {
                        tmp++;
                    } else {
                        if (str.charAt(j - 1) != str.charAt(j)) {
                            tmp++;
                        }
                    }

                    if (tmp == i) { // i번째 b그룹 시작점 찾음
                        standard = j;
                        break;
                    }
                }
            }
            
            // start = 기준점
            int start = standard;
            // end = 기준점에서 왼쪽으로 이동하면서 처음 만난 b
            int end = standard;
            while (true) {
                end--;
                if (end == -1) end = str.length() - 1;

                if (str.charAt(end) == 'b') {
                    break;
                }
            }
            /*
            1. end 왼쪽으로 이동하면서 첫 a 찾기
            2. start와 end 교환
            3. start 오른쪽으로 이동하면서 첫 b 찾고 1로 돌아가기
             */
            String str2 = new String(str);

            int change = 0;

            boolean cond = false;
            while (!cond) {
                // end 왼쪽 이동하면서 첫 a 찾기
                while (true) {
                    end--;
                    if (end == -1) end = str2.length() - 1;

                    if (end == start) {
                        cond = true;
                        break;
                    }
                    if (str2.charAt(end) == 'a') break;
                }

                if (!cond) {
                    // start와 end 교환
                    char[] tmpArr = str2.toCharArray();
                    tmpArr[start] = 'a';
                    tmpArr[end] = 'b';
                    str2 = String.valueOf(tmpArr);
                    change++;


                    // start 오른쪽으로 이동하면서 첫 b 찾고 1로 돌아가기
                    while (true) {
                        start++;
                        if (start == str2.length()) start = 0;

                        if (end == start) {
                            cond = true;
                            break;
                        }
                        if (str2.charAt(start) == 'b') break;
                    }
                }
            }

            if (result > change) {
                result = change;
            }
        }


        // 오른쪽으로 모으기...
        for (int i = 1; i <= cnt / 2; ++i) {
            // 기준점 = i번째 a그룹의 시작 인덱스
            int standard = 0;

            // str 탐색하면서 i번째 a그룹 찾기
            int tmp = 0;
            for (int j = 0; j < str.length(); ++j) {
                if (str.charAt(j) == 'a') {
                    if (j == 0) {
                        tmp++;
                    } else {
                        if (str.charAt(j - 1) != str.charAt(j)) {
                            tmp++;
                        }
                    }

                    if (tmp == i) { // i번째 a그룹 시작점 찾음
                        standard = j;
                        break;
                    }
                }
            }
            
            // start = 기준점
            int start = standard;
            // end = 기준점에서 왼쪽으로 이동하면서 처음 만난 a
            int end = standard;
            while (true) {
                end--;
                if (end == -1) end = str.length() - 1;

                if (str.charAt(end) == 'a') {
                    break;
                }
            }

            /*
            1. end 왼쪽으로 이동하면서 첫 a 찾기
            2. start와 end 교환
            3. start 오른쪽으로 이동하면서 첫 b 찾고 1로 돌아가기
             */
            String str2 = new String(str);

            int change = 0;

            boolean cond = false;
            while (!cond) {
                // end 왼쪽 이동하면서 첫 a 찾기
                while (true) {
                    end--;
                    if (end == -1) end = str2.length() - 1;

                    if (end == start) {
                        cond = true;
                        break;
                    }
                    if (str2.charAt(end) == 'b') break;
                }

                if (!cond) {
                    // start와 end 교환
                    char[] tmpArr = str2.toCharArray();
                    tmpArr[start] = 'b';
                    tmpArr[end] = 'a';
                    str2 = String.valueOf(tmpArr);
                    change++;


                    // start 오른쪽으로 이동하면서 첫 b 찾고 1로 돌아가기
                    while (true) {
                        start++;
                        if (start == str2.length()) start = 0;

                        if (end == start) {
                            cond = true;
                            break;
                        }
                        if (str2.charAt(start) == 'a') break;
                    }
                }
            }

            if (result > change) {
                result = change;
            }
        }

        System.out.print(result);
    }
}