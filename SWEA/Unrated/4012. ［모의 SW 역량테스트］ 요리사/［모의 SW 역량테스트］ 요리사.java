import java.io.*;
import java.util.*;

public class Solution {
	static int n; // 식재료 수
	static int[][] s; // 시너지 배열
	static int[] ingr;
	
	static int taste1, taste2;
	static int mindiff;
	
	static int[] comb;
	static int[] dish1, dish2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine()); //테케 개수 입력받기
		for(int z=1;z<=t;++z) { // 테케 시작
			
			mindiff=Integer.MAX_VALUE;
			
			n = Integer.parseInt(in.readLine()); // 식재료 개수
			
			s = new int[n][n]; // 시너지 배열 n*n으로 초기화
			for(int i=0;i<n;++i) { // 시너지 입력받기
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<n;++j) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 일단 n/2개씩 나누기
			// n C n/2 조합..
			ingr = new int[n]; // n개의 식재료를 나타내는 배열
			comb = new int[2];
			dish1 = new int[n/2]; // 요리1 재료 담기
			dish2 = new int[n/2]; // 요리2 재료 담기
			
			cook(0, 0);
			
			// 결과 저장
			sb.append("#").append(z).append(" ").append(mindiff).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void cook(int cnt, int idx) {
		if(cnt==n/2) {
			// n/2개 모두 고름 => 조합 완성 => 두 요리 맛의 차이 구하기
			int num1=0, num2=0; // 각 요리에 지금까지 담긴 재료 수
			
			for(int i=0;i<n;++i) { // 각 식재료에 대해
				if(ingr[i]==0) { // ingr값이 0이면 요리1 재료
					// 이 요리에 포함되는 재료들 중 2개씩 골라서 그 두 재료의 시너지를 taste1에 더해주기
					// n/2 P 2
					dish1[num1++]=i;
				}
				else { // ingr값 1이면 요리2 재료
					dish2[num2++]=i;
				}
			}
		
			taste1=0;
			taste2=0;
			for(int i=0;i<n/2;++i) {
				for(int j=0;j<n/2;++j) {
					taste1 += s[dish1[i]][dish1[j]];
					taste2 += s[dish2[i]][dish2[j]];
				}
			}
			if(Math.abs(taste1-taste2) < mindiff) {
				mindiff = Math.abs(taste1-taste2);
			}
			
			return;
		}
		
		for(int i=idx;i<n;++i) {
			ingr[i] = 1;
			cook(cnt+1, i+1);
			ingr[i] = 0;
		}
	}
}
