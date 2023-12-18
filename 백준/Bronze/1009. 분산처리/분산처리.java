import java.util.*;
import java.io.*;

public class Main {

    static int t;
    static int a, b; // 총 데이터의 개수 a^b개

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(in.readLine());

        for (int i = 0; i < t; ++i) {
            st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // mod 10

            // 제곱수의 끝자리수는 제곱 4번에 결정됨. 그 후로는 아무리 제곱해도 끝자리수 계속 반복
            // 따라서 b를 mod 4 연산 해주면 된다
            // b가 4의 배수 => 4 / b가 4의 배수가 아님 => b mod 4
            // ... 로 b를 재설정하여, a^b를 구하면 해당 수의 끝자리 숫자가 원래 a^b의 끝자리 수와 일치하게 된다.
            // 우리에게 필요한 것은 해당 끝자리 수이므로, 재설정한 b에 대해 a^b % 10 을 구하면 된다.
            // 이때, 이 값이 0인 경우 10번째 컴퓨터가 처리하는 것임에 유의한다.

            b %= 4;
            if(b==0) b=4;

            int tmp = (int) Math.pow(a, b) % 10;
            if(tmp == 0) sb.append("10").append("\n");
            else sb.append(tmp).append("\n");
        }

        System.out.println(sb.toString());
    }
}
