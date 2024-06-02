import java.util.*;
import java.io.*;

/*
240530
BJ13144 List of Unique Numbers - 골4
 */

public class Main {
    // 투 포인터? 큐?

    static int n; // 최대 10만
    static int[] arr;
    static boolean[] check;

    static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        arr = new int[n];
        check = new boolean[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        while (left < n) {
            while (right < n && !check[arr[right] - 1]) {
                check[arr[right] - 1] = true;
                right++;
            }
            result += (right - left);

            check[arr[left] - 1] = false;
            left++;
        }

        System.out.println(result);
    }
}
