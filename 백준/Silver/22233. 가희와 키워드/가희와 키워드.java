import java.util.*;
import java.io.*;

/*
240611
BJ22233 가희와 키워드 - 실3
 */

public class Main {

    /*
    글 하나당 키워드 최대 10개
    키워드는 각각 ','로 구분
     */

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            set.add(str);
        }

        for (int i = 0; i < m; ++i) {
            String str = in.readLine();
            String[] arr = str.split(","); // 이거 나 왜모르지
            for (int j = 0; j < arr.length; ++j) {
                set.remove(arr[j]);
            }
            sb.append(set.size()).append("\n");
        }

        System.out.print(sb);
    }
}