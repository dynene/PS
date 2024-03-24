import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
240324
BJ 1446 지름길 - 실버 1

thx to ejb...
 */

public class Main {

    static int n, d;

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int length;

        Edge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }


        @Override
        public int compareTo(Edge o) { // end 기준으로 정렬해야 함에 유의!!
            if (this.end > o.end) {
                return 1;
            } else if (this.end < o.end) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (end > d || length > end - start) continue; // 그냥 가는 것보다 느리거나, 도착점보다 뒤에서 끝나거나

            edgeList.add(new Edge(start, end, length));
        }

        Collections.sort(edgeList);
        // ㅇㅅㅇ ..

        int[] mindist = new int[d + 1];
        for (int i = 0; i <= d; ++i) {
            mindist[i] = i;
        }

        for (Edge edge: edgeList) {
            // mindist 갱신
            mindist[edge.end] = Math.min(mindist[edge.end], mindist[edge.start] + edge.length);
            for (int j = 1; j <= d - edge.end; ++j) {
                mindist[j + edge.end] = Math.min(mindist[j + edge.end], mindist[edge.end] + j);
            }
        }
        System.out.println(mindist[d]);
    }

}
