
/*
 * 메모리 KB
 * 시간 ms
 */

import java.util.*;
import java.io.*;

public class Solution {

	/*
	 * 자연수 n, r nCr을 1234567891로 나눈 나머지?
	 * 
	 * n, r 최대 100만
	 */

	static int n, r;
	static final int DIVIDENUM = 1234567891;
	static long[] fac;

	/*
	 * 페르마의 소정리 p가 소수이면, 모든 정수 a에 대해 a^p=a (mod p) 이다. p가 소수이고 a가 p의 배수가 아니면,
	 * a^p-1=1 (mod p) 이다. -> a^(p-2)=1/a (mod p)
	 * 
	 * => 이 문제에서 p = 1234567891... 소수 맞음
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 팩토리얼 초기화
		fac = new long[1000001];
		fac[0] = 1;
		for(int i=1; i<1000001; i++) {
			fac[i] = (fac[i-1]*i) % DIVIDENUM;
		}
		
		int t = Integer.parseInt(in.readLine());
		for(int z=1;z<=t;++z) {
			long result = -1;
			
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			// nCr을 dividenum으로 나눈 나머지?
			// nCr = n! / r!(n-r)! 를 dividenum을 나눈 나머지...
			// 여기서 a가 r!(n-r)!이라 할 때, a^(p-2) = a^(-1) (mod p)
			// => a의 (p-2)승을 p로 나눈 나머지는 a^(-1)이다.

			long a = fac[n]%DIVIDENUM;
			long b = ((fac[r]%DIVIDENUM) * (fac[n-r]%DIVIDENUM)) % DIVIDENUM;
			
			long b2 = pow(b, DIVIDENUM-2);
			result = (a*b2) % DIVIDENUM;
			
			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
    static long pow(long num, int p) {
        if(p==0)
            return 1;
        
        long half = pow(num, p/2);
        
        if(p%2==0) {
            return ((half%DIVIDENUM) * (half%DIVIDENUM))%DIVIDENUM;
        }else
            return (((half*num)%DIVIDENUM) * (half%DIVIDENUM))%DIVIDENUM;
    }

}
