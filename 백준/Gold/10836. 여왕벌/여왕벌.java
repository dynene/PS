import java.util.*;
import java.io.*;

/*
240901
17:40
BJ10836 여왕벌 - 골3
 */

public class Main {

    /*
    m*m 크기 벌집
    매일 애벌레가 성장함
    첫날 => 1
    n일동안 반복 (최대 100만)

    애벌레 커지는 정도 => 하루에 +0 / +1 / +2 중 하나.
    1. 0열, 0행 애벌레 => 자라는 정도 스스로 결정. (입력으로 주어짐)
    2. 나머지 애벌레 => 자신의 좌, 좌상, 상 애벌레 다 자란 후, "그날 가장 많이 자란 애벌레가 자란 만큼" 자란다.
     */

    static int m, n;

    static int[][] hive;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        hive = new int[m][m];

//        int[][] grow = new int[n][3];

        // n일동안 반복
        int[] nums = new int[3];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            nums[0] = Integer.parseInt(st.nextToken());
            nums[1] = Integer.parseInt(st.nextToken());
            nums[2] = Integer.parseInt(st.nextToken());

            for (int j = m - 1; j > 0; --j) {
                // inc값 결정
                if (nums[0] > 0) {
                    nums[0]--;
                } else if (nums[1] > 0) {
                    nums[1]--;
                    hive[j][0] += 1;
                } else {
                    nums[2]--;
                    hive[j][0] += 2;
                }
            }
            for (int j = 0; j < m; ++j) {
                if (nums[0] > 0) {
                    nums[0]--;
                } else if (nums[1] > 0) {
                    nums[1]--;
                    hive[0][j] += 1;
                } else {
                    nums[2]--;
                    hive[0][j] += 2;
                }
            }
        }


        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (j == 0) { // 0열이면 hive 값 그대로
                    sb.append((hive[i][j] + 1) + " ");
                } else { // 1열부터 m-1열이면 hive[0][열] 값으로
                    sb.append((hive[0][j] + 1) + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
