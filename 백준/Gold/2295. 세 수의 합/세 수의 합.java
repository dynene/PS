/*
241027
5:54
BJ2295 세 수의 합 - 골4
 */
import java.util.*;
import java.io.*;

public class Main {

    static int n; // 1000
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());


        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            int tmp = Integer.parseInt(in.readLine());
            arr[i] = tmp;
        }

        /*
        a+b+c=d일때, d도 집합에 포함되는 경우?
        d의 최댓값 구하기
         */

        // 일단 두 수의 합으로 나올 수 있는 모든 경우의 수 set에 넣기
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;++i){
            for(int j=i;j<n;++j){
                set.add(arr[i]+arr[j]);
            }
        }

        // set 탐색하면서, arr[i]에서 arr[j]를 뺀 값이 set에 있는지 체크
        // = arr[i]가 arr[a]+arr[b]+arr[c]로 구성 가능한지 체크 가능
        int result = -1;
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                int tmp = arr[i]-arr[j];
                if(set.contains(tmp)) {
                    if(result < arr[i]) {
                        result = arr[i];
                    }
                }
            }
        }

        System.out.println(result);

    }
}
