import java.util.*;
import java.io.*;

/*
240627
BJ2292 벌집 - 브2
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        int result = 1;
        long tmp = 1;
        int cnt = 0;
        while (true) {
            tmp += cnt * 6;
            if(tmp >= n) {
                System.out.print(cnt + 1);
                return;
            } else {
                cnt++;
            }
        }

        /*
        1
        2~7 : 6
        8~19 : 12
        20~37 : 18
        38~61 : 24
         */
    }
}
