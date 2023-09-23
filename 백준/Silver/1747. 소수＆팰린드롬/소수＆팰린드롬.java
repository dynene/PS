import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] prime;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		
		// 1. 에라토스테네스의 체 이용해서 10000 미만의 소수 모두 구하기
		prime = new boolean[1500000];
		prime[0]=prime[1]=true;
		for(int i=2;i<1500000;++i) {
			if(prime[i]==false) {
				for(int j=i*2;j<1500000;j+=i) {
					prime[j] = true;
				}
			}
		} // i: prime[i]가 false면 소수
		
		// 2. n보다 크거나 같은 첫 번째 소수 찾기
		int tmp = n;
		
		while(true) {
			
			while(true) {
				if(prime[tmp]==false) {
					break;
				} else tmp++;
			}
			
			// 3. 그 수가 팰린드롬인지 확인
			boolean isResult=true;
			String tmp2 = Integer.toString(tmp);
			for(int i=0; i<tmp2.length()/2; ++i) {
				if(tmp2.charAt(i) != tmp2.charAt(tmp2.length()-i-1)) {
					isResult=false;
					break;
				}
			}
			
			if(isResult) {
				System.out.println(tmp);
				break;
			} else {
				tmp++;
				//다시 소수부터 구하기
			}
		}
		
	}


}
