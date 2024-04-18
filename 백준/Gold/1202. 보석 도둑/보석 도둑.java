import java.io.*;
import java.util.*;

/*
   <답봄 ...>
 * 240418 시작
 * BOJ 1202 보석 도둑 - 골드 2
 */


public class Main {

    /*
     * 보석 n개 <= 30만
     * 각 보석의 무게 m[i]
     * 각 보석의 가격 v[i] <= 100만
     *
     * 가방 k개 <= 30만
     * 각 가방의 최대 수용 무게 c[i] <= 1억
     * 한 가방당 보석 1개만 넣을 수 있음
     *
     * k개의 가방에 넣을 수 있는 보석의 최대 가격?
     *
     * 왜 익숙한가 했더니 ... SDS 알골 특강에서 했던 문제였다...
     * 그리디 + pq + 정렬
     */

    static int n, k;
    static long result;

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel j) {
            return this.weight - j.weight;
        }

        @Override
        public String toString() {
            return "weight: " + weight + ", value: " + value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(jewels); // 보석 무게 오름차순 정렬

        for (int i = 0; i < k; ++i) {
            bags.add(Integer.parseInt(in.readLine()));
        }

        Collections.sort(bags); // 가방 용량 오름차순 정렬

        PriorityQueue<Jewel> pq = new PriorityQueue<>( // i번째 가방에 넣을 수 있는 보석 후보, 가치 높은 순 정렬
                new Comparator<Jewel>() {
                    @Override
                    public int compare(Jewel o1, Jewel o2) {
                        return o2.value - o1.value;
                    }
                }
        );

        int pointer = 0;
        for (int i = 0; i < k; ++i) {
            int volumeOfBag = bags.get(i);
            while (pointer < n) {
                if (jewels.get(pointer).weight <= volumeOfBag) {
                    pq.add(jewels.get(pointer++));
                } else {
                    break;
                }
            }

            if (!pq.isEmpty()) {
                result += pq.poll().value;
            }
        }

        System.out.println(result);
    }

}
