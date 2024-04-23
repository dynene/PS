import java.util.*;
import java.io.*;

/*
240422
BJ17615 볼 모으기 - 실버 1
*/

public class Main {

    /*
    규칙에 따라 볼을 이동하여 같은 색끼리 모으되 최소 이동횟수 찾기

    1. 무조건 각 색 그룹의 가장 왼쪽/오른쪽 끝 공을 옮기게 된다.
    2. 빨/파 중 한 가지 색의 공만 옮길 수 있음
    3. 이동 방향은 좌/우 모두 가능
    4. 이동 시, 타 색상 공 그룹은 모두 건너뛸 수 있음

     */

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        arr = new int[n];
        String str = in.readLine();
        int numofred = 0;
        int numofblue = 0;
        for (int i = 0; i < n; ++i) {
            if (str.charAt(i) == 'R') {
                arr[i] = 1; // 1: 빨간 공
                numofred++;
            } else {
                arr[i] = 2; // 2: 파란 공
            }
        }
        numofblue = n - numofred;


        if (arr[0] == arr[n - 1]) { // 양쪽 끝 색 같은 경우 ..
            int color = 0;
            int numofcolor = 0;
            if (arr[0] == 1) { // 둘다 빨간색인 경우
                color = 1;
                numofcolor = numofred;
            } else { // 둘다 파란색인 경우
                color = 2;
                numofcolor = numofblue;
            }

            // 왼쪽 그룹, 오른쪽 그룹 중 어디가 더 긴지 확인
            int leftcnt = 0;
            while (leftcnt < n) {
                if (arr[leftcnt] == color) {
                    leftcnt++;
                } else {
                    break;
                }
            }
            int rightcnt = 0;
            while (n - 1 - rightcnt >= 0) {
                if (arr[n - 1 - rightcnt] == color) {
                    rightcnt++;
                } else {
                    break;
                }
            }
            int existgroup = Math.max(leftcnt, rightcnt);
            // 전체 빨간 공 수 - 더 긴 그룹 길이 vs 전체 파란 공 수 중 작은 값이 답
            System.out.println(Math.min(numofcolor - existgroup, n - numofcolor));
        } else { // 끝 색 다른 경우...
            // 왼 끝 그룹 수 / 오른쪽 끝 그룹 수 세기
            int leftcnt = 0;
            while (leftcnt < n) {
                if (arr[leftcnt] == arr[0]) {
                    leftcnt++;
                } else {
                    break;
                }
            }
            int rightcnt = 0;
            while (n - 1 - rightcnt >= 0) {
                if (arr[n - 1 - rightcnt] == arr[n - 1]) {
                    rightcnt++;
                } else {
                    break;
                }
            }

            if (leftcnt > rightcnt) {
                if (arr[0] == 1) {
                    System.out.println(Math.min(numofblue - rightcnt, numofred - leftcnt));
                } else {
                    System.out.println(Math.min(numofred - rightcnt, numofblue - leftcnt));
                }
            } else {
                if (arr[n - 1] == 1) {
                    System.out.println(Math.min(numofblue - leftcnt, numofred - rightcnt));
                } else {
                    System.out.println(Math.min(numofred - leftcnt, numofblue - rightcnt));
                }
            }
        }

    }
}
