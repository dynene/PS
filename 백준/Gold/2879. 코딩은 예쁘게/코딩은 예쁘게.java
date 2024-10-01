/*
241001
21:13
BJ2879 코딩은 예쁘게 - 골3
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] tab;
    static int[] correctTab;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        tab = new int[n];
        for (int i = 0; i < n; ++i) {
            tab[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        correctTab = new int[n];
        for (int i = 0; i < n; ++i) {
            correctTab[i] = Integer.parseInt(st.nextToken());
        }

        /*
        코드 인덴트를 전부 고치기 위해 필요한 최소 편집 횟수 구하기
        <편집 1번당 >
        1. 연속된 줄을 그룹으로 선택한다
        2. 선택된 줄의 앞에 탭 1개를 추가하거나 삭제한다
        * 선택된 줄 중 탭 0개인 줄 있으면 탭 삭제 불가능
         */

        int result = 0;

        //
        int[] gap = new int[n];
        for (int i = 0; i < n; ++i) {
            gap[i] = correctTab[i] - tab[i];
        }

        while (true) {
            // 처음으로 0이 아닌 거 찾기 = groupStart
            int groupStart = -1;
            // gap[groupStart]와 처음으로 부호 다르거나 0 만나면, 그 앞까지 = groupEnd
            int groupEnd = n - 1;

            for (int i = 0; i < n; ++i) {
                if (gap[i] != 0) {
                    groupStart = i;
                    break;
                }
            }

            // groupStart가 -1이면 모두 고쳐진 것 > 종료
            if (groupStart == -1) {
                break;
            } else { // -1이 아니면 아직 더 편집해야 하는 것
                result++;
            }

            // group 내 편집 수행
            for (int i = groupStart; i < n; ++i) {
                if (gap[groupStart] * gap[i] <= 0) { // 부호가 다르거나, 0이 나오면... > 이 앞까지가 그룹 끝
                    groupEnd = i - 1;
                    break;
                }
            }

            // +면 1 빼고, -면 1 더하고
            int num;
            if (gap[groupStart] > 0) num = -1;
            else num = 1;

            for (int i = groupStart; i <= groupEnd; ++i) {
                gap[i] += num;
            }
        }


        System.out.println(result);
    }
}
