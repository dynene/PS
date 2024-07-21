import java.util.*;
import java.io.*;

/*
240721
BJ22866 탑 보기 - 골3
 */

public class Main {

    /*
    각 건물에서 볼 수 있는 건물 개수 세기
     */

    static int n;
    static int[] arr;

    static class Building {
        int height;
        int location;

        Building(int a, int b) {
            this.height = a;
            this.location = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(in.readLine());
        arr = new int[n + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] resultCnt = new int[n + 1];
        int[] leftDist = new int[n + 1];
        int[] rightDist = new int[n + 1];

        // 왼쪽에서 하나씩 보면서, 현재 stack top보다 작으면 넣지 않고 크면 넣음
        Stack<Building> stack = new Stack<>();
        for (int i = 1; i <= n; ++i) {
            while (!stack.isEmpty() && stack.peek().height <= arr[i]) {
                stack.pop();
            }
            resultCnt[i] += stack.size();
            if (!stack.isEmpty()) {
                leftDist[i] = stack.peek().location;
            }
            stack.push(new Building(arr[i], i));
        }

        // 오
        stack = new Stack<>();
        for (int i = n; i > 0; --i) {
            while (!stack.isEmpty() && stack.peek().height <= arr[i]) {
                stack.pop();
            }
            resultCnt[i] += stack.size();
            if (!stack.isEmpty()) {
                rightDist[i] = stack.peek().location;
            }
            stack.push(new Building(arr[i], i));
        }

        // 종합하기...
        for (int i = 1; i <= n; ++i) {
//            System.out.println(leftDist[i] + " "+rightDist[i]);
            sb.append(resultCnt[i]);
            if (resultCnt[i] > 0) {
                sb.append(" ");
                if(leftDist[i]==0) sb.append(rightDist[i]);
                else if(rightDist[i]==0) sb.append(leftDist[i]);
                else {
                    if(i - leftDist[i] <= rightDist[i] - i) {
                        sb.append(leftDist[i]);
                    } else {
                        sb.append(rightDist[i]);
                    }
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
