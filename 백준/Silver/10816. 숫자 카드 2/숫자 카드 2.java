import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
240415
BJ10816 숫자 카드 2 - 실버 4
 */

public class Main {

    static int n;
    static int m;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
        아니 머지... 왜 모르겟지...

        n개의 수...를 가지고 map을 만들어
         */

        n = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        arr = new int[20000003];
        for (int i = 0; i < n; ++i) {
            int tmp = Integer.parseInt(st.nextToken());
            arr[tmp+10000000]++;
        }
//        Arrays.sort(arr);

        m = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < m; ++j) {
            int tmp = Integer.parseInt(st.nextToken());
            sb.append(arr[tmp+10000000]).append(" ");
        }

        System.out.print(sb);

//        // 정답 출력
//        for (int i = 0; i < m; ++i) {
//            System.out.print(result[i] + " ");
//        }
    }
}
