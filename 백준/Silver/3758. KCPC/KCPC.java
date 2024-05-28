import java.util.*;
import java.io.*;

/*
240528
BJ3758 KCPC - 실2
 */

public class Main {

    static int T;
    static int n, k, t, m;

    /*
    문제별 기본 0점, 제출한 것중 최고점이 해당 문제의 최종 점수가 됨
    팀의 최종 점수 = 문제별 점수 총합
    팀의 순위 = (해당 팀보다 높은 점수 받은 팀의 수) + 1

    점수 동일한 팀의 경우)
    1. 풀이 제출 횟수 더 적은 팀이 우선
    2. 제출 횟수도 동일한 팀의 경우) 마지막 제출 시간 빠른 팀이 우선

    // 팀별로 관리해야 할 것
    1. 문제별 점수
    2. 마지막 제출 시간
    3. 제출 횟수

    팀 개수 n
    문제 개수 k
    우리 팀 ID t
     */

    static class Team implements Comparable<Team> {
        int id;
        int[] scores;
        int lastSubmit;
        int submitCnt;
        int totalScore;

        Team(int id) {
            this.id = id;
            this.scores = new int[k + 1];
            this.lastSubmit = 0;
            this.submitCnt = 0;
            this.totalScore = 0;
        }

        @Override
        public int compareTo(Team o) {
            if (this.totalScore > o.totalScore) {
                return -1;
            } else if (this.totalScore < o.totalScore) {
                return 1;
            } else { // 점수 동일한 경우
                if (this.submitCnt < o.submitCnt) {
                    return -1;
                } else if (this.submitCnt > o.submitCnt) {
                    return 1;
                } else { // 제출 횟수 동일한 경우
                    return this.lastSubmit - o.lastSubmit;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(in.readLine());

        for (int z = 1; z <= T; ++z) {
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            List<Team> teamList = new ArrayList<>();
            for (int a = 1; a <= n; ++a) {
                teamList.add(new Team(a));
            }

            for (int a = 1; a <= m; ++a) {
                st = new StringTokenizer(in.readLine());
                int i = Integer.parseInt(st.nextToken()); // 팀 id
                int j = Integer.parseInt(st.nextToken()); // 문제 번호
                int s = Integer.parseInt(st.nextToken()); // 획득 점수

                Team team = teamList.get(i - 1);
                team.submitCnt++;
                team.lastSubmit = a;
                if (team.scores[j] < s) {
                    if (team.scores[j] != 0) {
                        team.totalScore -= team.scores[j];
                    }
                    team.totalScore += s;
                    team.scores[j] = s;
                }
            }

            Collections.sort(teamList);
            for (int i = 0; i < n; ++i) {
                if (teamList.get(i).id == t) {
                    sb.append(i + 1).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}
