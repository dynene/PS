import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String str = in.readLine();
        String[] strs = str.split("/");
        int k = Integer.parseInt(strs[0]);
        int d = Integer.parseInt(strs[1]);
        int a = Integer.parseInt(strs[2]);
        if (k + a < d || d == 0) {
            System.out.print("hasu");
        } else {
            System.out.print("gosu");
        }
    }
}
