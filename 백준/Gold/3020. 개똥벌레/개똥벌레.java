import java.util.*;
import java.io.*;

/*
240524
BJ3020 개똥벌레 - 골5
RE
*/

public class Main {
    
    /*
    아래, 위 번갈아가며 장애물 세워져 있음
    높이 h
    존재하는 장애물 수가 최소인 구간의 장애물 수 + 그러한 구간의 수 구하기
    */

    static int n, h;

    static int[] top, bottom;
    static int minBreak;
    static int minCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        top = new int[n / 2];
        bottom = new int[n / 2];

        for (int i = 0; i < n / 2; ++i) {
            int cur = Integer.parseInt(in.readLine());
            int cur2 = Integer.parseInt(in.readLine());
            bottom[i] = cur;
            top[i] = cur2;
        }

        Arrays.sort(top);
        Arrays.sort(bottom);

                minCnt = 0;
        minBreak = n;
        for (int i = 1; i <= h; ++i) {
            int botCnt, topCnt;

            int left = 0;
            int right = n / 2;
            int height = h + 1 - i;
            while (left < right) {
                int mid = (left + right) / 2;

                if (top[mid] >= height) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            topCnt = n / 2 - right;

            left = 0;
            right = n / 2;
            height = i;
            while (left < right) {
                int mid = (left + right) / 2;
                if (bottom[mid] >= height) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            botCnt = n / 2 - right;

            if (minBreak == botCnt + topCnt) {
                minCnt++;
            } else if (botCnt + topCnt < minBreak) {
                minBreak = botCnt + topCnt;
                minCnt = 1;
            }
        }

        System.out.println(minBreak + " " + minCnt);
    }
}
