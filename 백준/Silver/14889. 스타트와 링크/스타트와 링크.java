import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
240416
BJ14889 스타트와 링크 - 실버 1
 */
public class Main {

    /*
    능력치 Sij = i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
    팀의 능력치 = 팀에 속한 모든 쌍의 능력치 Sij의 합이다.
    Sij는 Sji와 다를 수도 있으며,
    i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.
    
    아졸려;;;;;ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹ
    */

    static int n;
    static int[][] s;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        result = Integer.MAX_VALUE;

        n = Integer.parseInt(in.readLine());
        s = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] p = new int[n - 1];
        for (int i = n / 2; i < n - 1; ++i) {
            p[i] = 1;
        }

        // 20번째 사람이 무조건 ~~ 팀이라고 하고, 이 사람이랑 같은 팀 할 n/2 - 1 명의 사람 구하기 ... ?
        do {
            // 현재의 p를 가지고 능력치 합 구하기
            int[] team1 = new int[n / 2]; // 마지막 애랑 같은 팀인 애들 = p[i]가 1
            int[] team2 = new int[n / 2]; // 다른 팀인 애들 = p[i]가 0
            int t1cnt = 0;
            int t2cnt = 0;
            team1[(n / 2) - 1] = n - 1;
            for (int i = 0; i < n - 1; ++i) {
                if (p[i] == 1) {
                    team1[t1cnt++] = i;
                } else if (p[i] == 0) {
                    team2[t2cnt++] = i;
                }
            }

            // team1에서 2명씩 묶어서 능력치 합 구하기
            int[] p2 = new int[n / 2];
            for (int i = (n / 2) - 2; i < n / 2; ++i) {
                p2[i] = 1;
            }

            int val1 = 0;
            int val2 = 0;
            do {
                // p2[i]의 값이 1인 원소들 idx a, b
                // s[team1[a]][team1[b]] 더하기 ..
                int a = 0;
                int b = 0;
                for (int i = 0; i < n / 2; ++i) {
                    if (p2[i] == 1) {
                        if (a == 0) a = i;
                        else b = i;
                    }
                }

                val1 += s[team1[a]][team1[b]];
                val1 += s[team1[b]][team1[a]];
                val2 += s[team2[a]][team2[b]];
                val2 += s[team2[b]][team2[a]];

            } while (np(p2));

            if (result > Math.abs(val2 - val1)) {
                result = Math.abs(val2 - val1);
            }
        } while (np(p));

        System.out.print(result);
    }

    private static boolean np(int[] p) {
        int n = p.length;
        int i = n - 1;
        while (i > 0 && (p[i - 1] >= p[i]))
            --i;

        if (i == 0)
            return false;

        int j = n - 1;
        while (p[i - 1] >= p[j])
            --j;
        swap(p, i - 1, j);

        int k = n - 1;
        while (i < k)
            swap(p, i++, k--);

        return true;
    }

    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }
}
