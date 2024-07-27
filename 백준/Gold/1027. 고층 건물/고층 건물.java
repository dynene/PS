import java.util.*;
import java.io.*;

/*
240727
BJ1027 고층 건물 - 골4
 */

public class Main {

    /*
    A에서 B를 보려면...
    A와 B 옥상을 잇는 선분이 다른 빌딩을 지나거나 / 접하지 않아야 함

    가장 많은 빌딩이 보이는 빌딩 번호 + 거기서 보이는 빌딩 수 구하기
     */

    static int n;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        heights = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> availList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            availList.add(new ArrayList<>());
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int[] p = new int[n];
        Arrays.fill(p, 0);
        p[n - 1] = 1;
        p[n - 2] = 1;
        do {
            int a = -1;
            int b = -1;
            for (int i = 0; i < n; ++i) {
                if (p[i] == 1) {
                    if (a == -1) a = i;
                    else {
                        b = i;
                        break;
                    }
                }
            }

            // a에서 b를 볼 수 있는지 확인하기
            // 식 만들기
            double d1 = (double) (heights[b] - heights[a]) / (b - a);
            double d2 = heights[a] - (d1 * a);

            // a와 b 사이의 빌딩을 식에 대입하기
            boolean available = true;
            for (int i = a + 1; i < b; ++i) {
                if (heights[i] >= (d1 * i) + d2) {
                    // 하나라도 걸리면 안 보이는 것...
                    available = false;
                    break;
                }
            }

            if (available) {
                availList.get(a).add(b);
                availList.get(b).add(a);
            }

        } while (np(p));

        int maxCount = 0;
        for (int i = 0; i < n; ++i) {
            if (availList.get(i).size() > maxCount) {
                maxCount = availList.get(i).size();
            }
        }

        System.out.println(maxCount);
    }

    private static boolean np(int[] p) {
        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i])
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
