import java.util.*;
import java.io.*;

/*
240710
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int x = Integer.parseInt(in.readLine());
        int n = Integer.parseInt(in.readLine());

        int sum = 0;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sum += a * b;
        }

        if (sum == x) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
}
