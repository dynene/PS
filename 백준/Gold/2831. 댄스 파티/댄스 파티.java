/*
240915
1:00
BJ2831 댄스 파티 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    남자 n명, 여자 n명

    각 여자, 남자는 자신보다 키가 크거나/작은 사람과 춤을 추고 싶어함.
    키 동일한 남녀는 같이 춤 출 일 없음!

    각 사람의 키 / 선호 유형을 알고 있을 때
    춤을 출 쌍을 최대 몇 개 만들 수 있는가?
     */

    /*
    그리디 / 정렬 / 투포인터

     */

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        List<Integer> man = new ArrayList<>();
        List<Integer> woman = new ArrayList<>();

        int manCnt = 0;
        int womanCnt = 0; // 음수값인 사람 세기
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp < 0) {
                manCnt++;
            }
            man.add(tmp);
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp < 0) {
                womanCnt++;
            }
            woman.add(tmp);
        }

        Collections.sort(man);
        Collections.sort(woman);

        /*
        짝을 만드려면...
        더 작은 사람이 큰 사람 선호 + 더 큰 사람이 작은 사람 선호 해야 함
        큰 사람 선호 => 자신보다 큰 사람 중 가장 작은 사람
        작은 사람 선호 => 자신보다 작은 사람 중 가장 큰 사람 을 만나야 함...
         */
        int result = 0;

        // 1. 작 성향 남자 + 큰 성향 여자 매칭
        int manpt = manCnt - 1;
        int womanpt = womanCnt;

        while (true) {
            if (manpt < 0 || womanpt > n - 1) break;
            if (man.get(manpt) + woman.get(womanpt) < 0) {
                // 매칭 ㄱㄴ
                result++;
                manpt--;
                womanpt++;
            } else {
                // 매칭 ㅂㄱㄴ
                manpt--;
            }
        }

        // 2. 큰 성향 남자 + 작 성향 여자 매칭
        manpt = manCnt;
        womanpt = womanCnt - 1;

        while (true) {
            if (manpt > n - 1 || womanpt < 0) break;
            if (man.get(manpt) + woman.get(womanpt) < 0) {
                result++;
                manpt++;
                womanpt--;
            } else {
                womanpt--;
            }
        }

        System.out.println(result);
    }
}
