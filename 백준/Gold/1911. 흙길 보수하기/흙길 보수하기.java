import java.util.*;
import java.io.*;

/*
240821
17:47
BJ1911 흙길 보수하기 - 골5
 */

public class Main {

    static int n, l;

    static class Pool implements Comparable<Pool> {
        int start;
        int end;

        Pool(int start, int end) {
            this.start = start; // start부터
            this.end = end; // end-1까지 웅덩이
        }

        @Override
        public int compareTo(Pool o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); // 웅덩이 수
        l = Integer.parseInt(st.nextToken()); // 널빤지 길이

        List<Pool> pools = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            pools.add(new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(pools);

        /*
        n길이= 웅덩이 개수 다 가릴 때까지
        pools 하나씩 뽑아서..
        하나 깔고,
         */

        int cnt = 0;

        int lastPlank = 0; // 널빤지 깔아둔 마지막 좌표
        int pointer = 0; // 어느 웅덩이를 덮고 있는지 가리킬 포인터
        while (true) {
            if (pointer >= n) break;

            Pool cur = pools.get(pointer++);
            if (cur.start > lastPlank) { // cur.start부터 cur.end-1까지 덮도록 깔기
                // l * (깔 타일 개수) >= cur.end - cur.start(웅덩이 길이) 가 되도록 하는 최소 (개수) 값만큼 깔아야함
                int curCnt = (int) Math.ceil((double) (cur.end - cur.start) / l);
                cnt += curCnt;
                // 덮고 나서의 lastPlank 업데이트하기.
                lastPlank = (cur.start - 1) + (curCnt * l);
            } else {
                if (cur.end - 1 <= lastPlank) {
                    continue;
                } else {
                    // lastPlank+1 부터 cur.end-1까지 덮도록 깔기
                    int curCnt = (int) Math.ceil((double) (cur.end - (lastPlank + 1)) / l);
                    cnt += curCnt;
                    lastPlank = lastPlank + (curCnt * l);
                }

            }
        }
        
        System.out.println(cnt);

    }
}
