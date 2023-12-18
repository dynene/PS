import java.util.*;
import java.io.*;

public class Main {

    /*
    10000보다 작거나 같은 "셀프 넘버" 한 줄에 하나씩 출력하기
    d(73) = 73 + 7 + 3 = 83  // 이때 73은 83의 생성자가 된다.
    셀프 넘버 : 생성자가 없는 숫자
     */

    static int[] arr;

    public static void main(String[] args) {
        // 이거 걍 에라토스테네스의 체 하듯이 하면 되는 거 아닌가?
        // 아닐지도.........

        arr = new int[10001];

        for (int i = 1; i <= 10000; ++i) {
            if (arr[i] == 1) {
                continue;
            } else {
                int tmp = i;
                while(true) {
                    int dn = d(tmp);
                    if(dn<=10000) {
                        arr[dn] = 1;
                        tmp = dn;
                    } else break;
                }
            }
        }

        for(int i=1;i<=10000;++i){
            if(arr[i]==0) System.out.println(i);
        }
    }

    static int d(int n) {
        int result = n;

        while (true) {
            result += n % 10;

            if (n < 10) break;
            else n /= 10;
        }

        return result;
    }
}
