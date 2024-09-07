import java.util.*;
import java.io.*;

/*
240907
15:50
BJ20207 달력 - 골5
 */

public class Main {

    static class Schdl implements Comparable<Schdl> {
        int start;
        int end;

        Schdl(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schdl o) {
            if (this.start > o.start) {
                return 1;
            } else if (this.start == o.start) {
                if (this.end - this.start > o.end - o.start) {
                    return 1;
                } else if (this.end - this.start == o.end - o.start) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }

    static int n;
    static boolean[][] arr;
    static int[] hasSchdl;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());

        List<Schdl> schdls = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            schdls.add(new Schdl(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(schdls);

        // 일자마다 일정 있는지 여부 => boolean[366]으로 기록하기 ..
        hasSchdl = new int[366];
        arr = new boolean[366][1001];

        for (Schdl schdl : schdls) {
            // start의 비어있는 첫 칸 찾고..
            int depth = 0;
            for (int i = 1; i <= 1000; ++i) {
                if (!arr[schdl.start][i]) {
                    depth = i;
                    break;
                }
            }

            for (int i = schdl.start; i <= schdl.end; ++i) {
                // 그거에 맞춰서 start부터 end까지 채우기
                hasSchdl[i] = Math.max(hasSchdl[i], depth);
                arr[i][depth] = true;
            }
        }

        // 코팅지 크기 계산하기
        /*
        연속된 두 일자에 각각 일정이 1개 이상 있다면 이를 일정이 연속되었다고 표현한다.
        연속된 모든 일정은 하나의 직사각형에 포함되어야 한다.
        연속된 일정을 모두 감싸는 가장 작은 직사각형의 크기만큼 코팅지를 오린다.
         */
        int result = 0;

        // hasSchdl의 false->true 지점, true->false 지점 찾기
        int pt = 1;

        while (true) {
            if (pt > 365) break;

            if (hasSchdl[pt] != 0) { // 시작점
                // true->false 지점 + 여기까지의 최댓값  찾기
                int endpt = pt;
                int maxdepth = hasSchdl[pt];

                while (true) {
                    if (endpt > 365 || hasSchdl[endpt] == 0) { // endpt-1까지..
                        endpt--;
                        break;
                    } else {
                        maxdepth = Math.max(maxdepth, hasSchdl[endpt]);
                        endpt++;
                    }
                }

                // 이 지점의 넓이 구해서 더하기
                result += (endpt - pt + 1) * maxdepth;

                // pt값 업데이트
                pt = endpt + 1;
            } else {
                pt++;
            }
        }

        System.out.println(result);
    }
}
