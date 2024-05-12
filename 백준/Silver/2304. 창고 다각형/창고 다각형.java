import java.util.*;
import java.io.*;

/*
240512
BJ2304 창고 다각형 - 실2
 */

public class Main {
    static class Pole implements Comparable<Pole> {
        int l;
        int h;

        Pole(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Pole o) {
            return this.l - o.l;
        }
    }

    static int n;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        List<Pole> list = new ArrayList<>();


        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            list.add(new Pole(l, h));
        }

        Collections.sort(list);

        int top = 0;
        for (int i = 1; i < n; ++i) {
            if (list.get(top).h < list.get(i).h) {
                top = i;
            }
        }

        // 위치순 정렬돼있으면 시작부터 -> <- 끝부터 탐색해 나가기 . . .
        int leftTop = list.get(0).h;
        int rightTop = list.get(n - 1).h;

        for (int i = 1; i <= top; ++i) {
//            System.out.println(result);
            result += (list.get(i).l - list.get(i-1).l) * leftTop;
            if (list.get(i).h > leftTop) {
                leftTop = list.get(i).h;
            }
        }

        for (int i = n - 2; i >= top; --i) {
//            System.out.println(result);
            result += (list.get(i+1).l - list.get(i).l) * rightTop;
            if(list.get(i).h > rightTop) {
                rightTop = list.get(i).h;
            }
        }

        result += list.get(top).h;
        System.out.println(result);
    }
}
