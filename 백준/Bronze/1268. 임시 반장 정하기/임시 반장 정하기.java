import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());

        // 같은 반 한 번당 한 번씩 카운트하는 게 아니라 "한번이라도 같은 반이었던 사람이 가장 많은 학생"
        // ㅁㅊㅋㅋ 바본가 ㅋㅋ
        // 문제를 똑바로 읽자... ㅋㅋ

        arr = new int[n][6];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 5; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; ++i) {
            // 각각의 학생에 대해, 나머지 각 학생과 같은 반 된 적 있는지 확인하기
            for (int j = 0; j < n; ++j) {
                if (j == i) continue; // 본인 제외
                for (int k = 0; k < 5; ++k) {
                    if (arr[i][k] == arr[j][k]) {
                        arr[i][5]++;
                        break;
                    }
                }
            }
        }

        int result = 0;
        for(int i=0;i<n;++i){
            if(arr[i][5] > arr[result][5]) {
                result = i;
            }
        }

        System.out.println(result+1);
    }
}
