import java.util.*;
import java.io.*;

/*
RE
240727
BJ14658 하늘에서 별똥별이 빗발친다 - 골3
 */

public class Main {

    /*
    구역 크기 n*m
    트램펄린 크기 l*l
    별똥별 수 k개
     */

    static int n, m, l, k;
    static int[][] arr;

    static class Star {
        int x;
        int y;

        Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

//        arr = new int[n][m];
        List<Star> stars = new ArrayList<>();

        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Star(x, y));
        }

        // 별똥별 두 개씩 묶어서, 두 별똥별의 x, y좌표 하나씩 이용해서 트램펄린 배치하는 경우 모두 따져보기
        int maxCnt = 0;
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < k; ++j) {
                int x = stars.get(i).x;
                int y = stars.get(j).y;

                int cnt = 0;
                for (Star s : stars) {
                    if (s.x >= x && s.x <= x + l && s.y >= y && s.y <= y + l) {
                        cnt++;
                    }
                }

                maxCnt = Math.max(maxCnt, cnt);
            }
        }

        System.out.println(k - maxCnt);
    }

}
