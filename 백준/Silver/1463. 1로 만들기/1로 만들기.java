import java.util.Arrays;
import java.util.Scanner;

public class Main { // 실3

	/*
	 * 정수 x에 대해... 1. X%3==0이면 /3 2. X%2==0이면 /2 3. -1 세 가지 연산 사용해서 1 만들어야 할 때, 연산
	 * 사용 횟수 최솟값 구하기 n<=10^6
	 */

	static int n;
	static int min;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		if(n==1) { // 연산할 필요가 없음
			min=0;
		} else { // 연산 시작
			dp = new int[n+1];
			Arrays.fill(dp, -1);
			dp[1]=0;
			dp[2]=1;
			
			min=solve(n);
		}
		System.out.print(min);
	}
	
	static int solve(int x) {
		if(dp[x]==-1) { // 여기 온 적 없으면
			int a=Integer.MAX_VALUE, b=Integer.MAX_VALUE, c=Integer.MAX_VALUE;
			if(x%3==0) {
				a=solve(x/3);
			}
			if(x%2==0) {
				b=solve(x/2);
			}
			c=solve(x-1);
			
			// 최솟값을 dp[x]에 저장
			dp[x] = Math.min(a, b);
			dp[x] = Math.min(dp[x], c);
			dp[x]++;
		}
		return dp[x]; //저장된 dp값을 반환
	}
}