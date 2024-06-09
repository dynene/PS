import java.io.*;
import java.util.*;

public class Main {

    static String s;
    static String result;

    /*
    0, 1로 이루어진 문자열 s
    s를 구성하는 문자에서 절반의 0/절반의 1 제거해서 만들 수 있는 문자열 중 사전순으로 가장 빠른 것 구하기
    s 길이 <= 500
    */

    /*
    사전순으로 빠르다 = 0은 뒤에서 지우고 1은 앞에서 지우기?
    ㅇㅅㅇ...
    1. 0 개수, 1 개수 세기
    2. 
    */    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        s = in.readLine();
        StringBuilder sb = new StringBuilder();

        int numOf0 = 0;

        for(int i=0;i<s.length();++i){
            char c = s.charAt(i);
            if(c=='0') {
                numOf0++;
            }
        }

        boolean[] check = new boolean[s.length()];
        int left0 = numOf0 / 2;
        int left1 = (s.length() - numOf0) / 2;
        for(int i=0;i<s.length();++i){
            // 앞에서부터 보면서 (s.length()-numOf0)/2 개의 1 지우기
            if(s.charAt(i)=='1' && left1>0) {
                check[i]=true;
                left1--;
            }
            
            // 뒤에서부터 보면서 numOf0/2 개의 0 지우기
            if(s.charAt(s.length()-1 - i)=='0' && left0>0){
                check[s.length()-1 - i] = true; 
                left0--;
            }
        }

        for(int i=0;i<s.length();++i){
            if(!check[i]){
                sb.append(s.charAt(i));
            }
        }

        System.out.print(sb);
    }
}
