import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 230920
 * BOJ 2749 - 피보나치 수3
 * 골드2
 */

public class Main {
	
	/*
	 * 피사노 주기
	 * 피보나치 수를 k로 나눌 때, 그 나머지는 항상 주기를 갖게 된다.
	 * 주기의 길이 p
	 * n번째 피보나치 수를 m으로나눈 나머지는
	 * n%p번째 피보나치 수를 m으로 나눈 나머지와 같다
	 * m = 10^k (k>2)일 때의 주기 p = 15 * 10^(k-1)
	 */
	
	static long n;
	
	static int[] fib;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 bufferedreader
		
		n = Long.parseLong(in.readLine());
		
		// n번째 피보나치 수를 1,000,000으로 나눈 나머지 구하기
		// 이 문제에서 m = 1,000,000 = 10^6
		// 이때의 주기 p = 15 * 10^5 = 1,500,000
		// 따라서 n%(1,500,000)번째 피보나치 수를 10^6으로 나눈 나머지를 구해야 함
		
		// p번째 피보나치 수까지를 m으로 나눈 값 구하고
		// fib[n%p] 구하기
		
		int m = 1000000;
		int p = 1500000;
		
		fib = new int[p+1];
		fib[0]=0;
		fib[1]=1;
		fib[2]=1;
		
		for(int i=3;i<=p;++i) {
			fib[i] = (fib[i-1]+fib[i-2]) % m;
		}
		
		System.out.print(fib[(int) (n%p)]);
	}
}
