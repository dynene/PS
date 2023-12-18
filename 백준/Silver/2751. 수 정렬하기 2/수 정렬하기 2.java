import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(in.readLine());
        List<Integer> arr = new ArrayList<>();

        for(int i=0;i<n;++i){
            arr.add(Integer.parseInt(in.readLine()));
        }

        Collections.sort(arr);
        
        for(int i=0;i<n;++i){
            sb.append(arr.get(i)).append("\n");
        }

        out.write(sb.toString());
        out.flush();
        out.close();
    }
}
