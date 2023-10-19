/*
 * 231018
 * BOJ 2133 타일 채우기
 * 골드 4
 * 
 * 메모리 KB
 * 시간 ms
 */

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		/*
		 * 홀수 : 절대 완성할 수 없음 (무조건 0)
		 */
		
		if(n%2==1) {
			System.out.println(0);
			return;
		}
		else if (n==2) {
			System.out.println(3);
			return;
		}
		// n이 짝수인 경우
		int[] dp = new int[n+1];
		dp[2] = 3;
		dp[4] = 11;
		
		int sum = dp[2];
		for(int i=4;i<=n;i+=2) {
			dp[i] = sum*2 + 2 + dp[i-2];
			sum+=dp[i];
		}
		
		
		System.out.println(dp[n]);
		
	}

}
