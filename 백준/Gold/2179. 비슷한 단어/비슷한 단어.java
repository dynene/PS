import java.util.*;
import java.io.*;

/*
240619
BJ2179 비슷한 단어 - 골4
 */

public class Main {

    /*
    두 단어의 접두사 길이 = 두 단어의 유사도
    n <= 2만
    각 단어 길이 <= 100
    유사도 가장 높은 두 단어를 입력 순서대로 출력
    */

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(in.readLine());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            words.add(str);
        }

        int maxSimilarity = 0;
        int smallIdx = -1;
        int bigIdx = -1;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int similarity = 0;
                while (similarity < Math.min(words.get(i).length(), words.get(j).length())) {
                    if (words.get(i).charAt(similarity) == words.get(j).charAt(similarity)) {
                        similarity++;
                    } else break;
                }

                if(similarity > maxSimilarity) {
                    smallIdx = i;
                    bigIdx = j;
                    maxSimilarity = similarity;
                }
            }
        }

        System.out.println(words.get(smallIdx));
        System.out.println(words.get(bigIdx));
    }
}
