import java.util.*;
import java.io.*;

/*
240627
BJ1138 한 줄로 서기 - 실2
 */

public class Main {

    static int n;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(in.readLine());

        st = new StringTokenizer(in.readLine());
        arr = new int[n];

        for (int i = 1; i <= n; ++i) {
            int tmp = Integer.parseInt(st.nextToken()); // 키가 i인 사람 왼쪽에 tmp명이 있었음
            /*
            arr의 처음부터 tmp개의 0을 건너뛰고 그 자리에 채운다.
             */
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if(arr[j]==0) {
                    if(tmp==cnt) {
                        arr[j] = i;
                        break;
                    } else {
                        cnt++;
                    }
                } else {
                    continue;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            sb.append(arr[i]).append(" ");
        }

        System.out.print(sb);
    }
}

