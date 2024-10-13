/*
RE
241013
BJ1781 컵라면 - 골2
https://red-tiger.tistory.com/9
 */

import java.util.*;
import java.io.*;

public class Main {

    /*

     */

    static int n;
    static int result;

    static class Problem implements Comparable<Problem> {
        int deadline;
        int reward;

        Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.deadline < o.deadline) {
                return -1;
            } else if (this.deadline == o.deadline) {
                if (this.reward > o.reward) {
                    return -1;
                } else if (this.reward == o.reward) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        StringTokenizer st;

        List<Problem> probs = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            probs.add(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(probs);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<n;++i){
            int deadline = probs.get(i).deadline;
            int reward = probs.get(i).reward;
            pq.add(reward);
            if(deadline < pq.size()) { // pq의 크기 = 현재 time
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);

    }
}
