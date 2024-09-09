/*
240909
23:10
BJ2304 창고 다각형 - 실2
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static class Stick implements Comparable<Stick> {
        int l;
        int h;

        Stick(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Stick o) {
            return this.l - o.l;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());

        List<Stick> sticks = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            sticks.add(new Stick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 1. 일단 위치 기준으로 정렬
        Collections.sort(sticks);

        // 2. 넓이를 구해야 하는데...

        // 최대 높이의 기둥 찾기
        int maxIdx = 0;
        int maxHeight = 0;
        for (int i = 0; i < n; ++i) {
            int curh = sticks.get(i).h;
            if (curh > maxHeight) {
                maxIdx = i;
                maxHeight = curh;
            }
        }

        int result = 0;

        // 왼쪽 끝 -> maxIdx
        Stack<Integer> stack = new Stack<>();
        stack.push(sticks.get(0).h);
        int pt = 0;
        for (int i = sticks.get(0).l; i < sticks.get(maxIdx).l; ++i) { // 모든 높이에 대해..
            if (i == sticks.get(pt + 1).l) pt++;

            if (sticks.get(pt).h > stack.peek()) {
                stack.push(sticks.get(pt).h);
            }
            result += stack.peek();
        }

        // 오른쪽 끝 -> maxIdx
        stack.clear();
        stack.push(sticks.get(n - 1).h);
        pt = n - 1;
        for (int i = sticks.get(n - 1).l; i > sticks.get(maxIdx).l; --i) {
            if (i == sticks.get(pt - 1).l) pt--;

            if (sticks.get(pt).h > stack.peek()) {
                stack.push(sticks.get(pt).h);
            }
            result += stack.peek();
        }

        result += maxHeight;
        System.out.println(result);
    }
}
