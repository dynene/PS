import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
240331
BJ2002 추월 - 실버 1
 */

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());

        int result = 0;

        List<String> list = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            list.add(str);
        }

        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            String top = list.get(0);
            if (str.equals(top)) {
                list.remove(0);
            } else {
                result++;
                for (int j = 0; j < list.size(); ++j) {
                    if (list.get(j).equals(str)) {
                        list.remove(j);
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
