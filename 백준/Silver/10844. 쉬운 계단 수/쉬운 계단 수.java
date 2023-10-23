
import java.io.*;
import java.util.*;

public class Main {
	
	static final int mod = 1000000000;
	
	static int n;
	static long[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		
		dp = new long[n+1][10]; // 0~9
		
		/*
		 * 1 2 3 4 5 6 7 8 9
		 */
		for(int i=1;i<=9;++i) {
			dp[1][i] = 1;
		}
		
		if(n!=1) { // n이 1인 경우 처리!!!
            
			// 2자리 수 중 i로 끝나는 계단 수의 개수
			dp[2][0] = dp[1][1];
			dp[2][1] = dp[1][0]+dp[1][2];
			// 1~8은 전부 동일 방식
			dp[2][9] = dp[1][8]; // 9로 끝나는 경우는 앞자리 수가 8인 경우밖에 없음
			
			// 전체에 적용
			for(int i=2;i<=n;++i) {
				dp[i][0] = dp[i-1][1] % mod;
				for(int j=1;j<=8;++j) {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
				}
				dp[i][9] = dp[i-1][8] % mod;
			}
		}
		

		
		// 결과 구하기 => dp[n][0]~dp[n][9]까지의 합
		long sum = 0;
		for(int i=0;i<=9;++i) {
			sum += dp[n][i];
		}
		
		System.out.println(sum % mod);
	}

}
