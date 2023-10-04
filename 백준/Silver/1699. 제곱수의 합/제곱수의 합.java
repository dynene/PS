
import java.util.*;

/**
 * 230918
 * BOJ 1699
 * 실버 2
 * 자연수 N을 제곱수들의 합으로 표현할 때에 그 항의 최소개수 구하기
 * 1<=n<=10만
 */

public class Main {
	static int n;
	static int[] minnum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		if(Math.sqrt(n)%1==0) { // n이 제곱수인지 확인
			System.out.println(1);
			return;
		}
		
		// n이 제곱수가 아닌 경우
		minnum = new int[n+1];
		
		for(int i=1;i<=n;++i) { // 초기값 설정
			if(Math.sqrt(n)%1==0) minnum[i] = 1; // 제곱수인 경우 => 1
			else minnum[i] = i; // 제곱수 아닌 경우 1로 이루어진 게 최대 항의 개수 => i
		}

		
		/**
		 * 뭘 해야 하지...
		 * 제곱수
		 * 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 225, 256, 289, 324, 361, 400...
		 * 
		 * 1 = 1
		 * 2 = 1 + 1
		 * 3 = 1 + 1 + 1
		 * 4 = 2^
		 * 5 = 2^ + 1
		 * 6 = 2^ + 1 + 1
		 * 7 = 2^ + 1 + 1 + 1
		 * 8 = 2^ + 2^
		 * 9 = 3^
		 * 10 = 3^ + 1
		 * 11 = 3^ + 1 + 1
		 * 12 = 2^ + 2^ + 2^
		 * 13 = 3^ + 2^
		 * 14 = 3^ + 2^ + 1
		 * 15 = 3^ + 2^ + 1 + 1
		 * 16 = 4^
		 * 17 = 4^ + 1
		 * 18 = 3^ + 3^
		 */
		
		for(int i=1;i<=n;++i) {
			for(int j=1;i-j*j>=0;++j) {
				minnum[i] = Math.min(minnum[i], minnum[i - j*j]+1);
			}
		}
		
		System.out.println(minnum[n]);
		
	}

	
//	static void dfs(int x, int cnt) { // dfs => 당연히 시간 초과..
//		if(cnt < minnum[x]) {
//			minnum[x] = cnt;
//		} else return;
//		
//		if(x==n) return;
//		
//		for(int i=1;i<=n;++i) {
//			if(x+i*i <= n) {
//				dfs(x+i*i, cnt+1);
//			} else break;
//		}
//	}

}
