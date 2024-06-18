import java.util.*;
import java.io.*;

/*
240618
BJ11501 주식 - 실2
 */

public class Main {

    /*
    날의 수 <= 100만

    매일 취할 수 있는 행동
    1. 주식 하나 사기
    2. 가지고 있는 주식 팔기
    3. 아무것도 안 하기

    부호 있는 64bit 정수형?
     */

//    static class Node implements Comparable<Node> {
//        int value;
//        int day;
//
//        Node(int value, int day) {
//            this.value = value;
//            this.day = day;
//        }
//
//        @Override
//        public int compareTo(Node o) {
//            if (this.value < o.value) {
//                return 1;
//            } else if (this.value == o.value) {
//                if (this.day > o.day) {
//                    return 1;
//                } else if (this.day == o.day) {
//                    return 0;
//                } else {
//                    return -1;
//                }
//            } else { // this.value < o.value
//                return -1;
//            }
//        }
//    }

    static int n;
    static int[] nums;
    static long profit;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(in.readLine());
        for (int z = 1; z <= t; ++z) {
            n = Integer.parseInt(in.readLine()); // 날의 수
            st = new StringTokenizer(in.readLine());

//            PriorityQueue<Node> pq = new PriorityQueue<>();
            nums = new int[n]; //날별 주가
            for (int i = 0; i < n; ++i) {
                nums[i] = Integer.parseInt(st.nextToken());
//                pq.add(new Node(nums[i], i));
            }

            profit = 0;

            /*
            pq에 주가 내림차순, 인덱스 오름차순으로 넣고
            하나씩 뽑아서
            그거보다 앞에 있는 날에 다 사기
            처리한 날 수 == n 될때 끝

            1       10      100     50      30

             */

//            int processed = 0;
//            while (!pq.isEmpty() && processed < n - 1) {
//                // 하나 뽑고
//                Node cur = pq.poll();
//
//                if(cur.day < processed) continue;
//
//                // 이거보다 앞에 있는 날에 모두 사고 + 이 날에 팔았을 때의 수익 profit에 계산해서 더하기
//                for(int i = processed; i < cur.day; ++i) {
//                    profit += cur.value - nums[i];
//                }
//
//                // 처리한 날 수 업데이트
//                processed = cur.day + 1;
//            }

            int maxValue = nums[n-1];
            for(int i = n-2; i>=0; --i) {
                if(maxValue > nums[i]) {
                    profit += maxValue - nums[i];
                } else if(maxValue < nums[i]) {
                    maxValue = nums[i];
                }
            }

            sb.append(profit).append("\n");
        }

        System.out.print(sb);
    }
}
