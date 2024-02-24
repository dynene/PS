import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int cnt = 1;
        List<Integer> list = new ArrayList<>();
        while (true) {
            if (list.size() > 1000) break;
            for (int i = 0; i < cnt; ++i) {
                list.add(cnt);
            }
            cnt++;
        }

        int result = 0;
        for(int i=a;i<=b;++i){
            result += list.get(i-1);
        }

        System.out.println(result);
    }
}
