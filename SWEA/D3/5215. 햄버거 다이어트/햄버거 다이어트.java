import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 원소개수 n인 집합의 모든 부분집합 구해서 그에 대해 계산
	 */
	static int n, l;
	static int[] taste, calorie;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(in.readLine());
		
		for(int z=1;z<=t;++z) {
			st = new StringTokenizer(in.readLine());
			n=Integer.parseInt(st.nextToken()); // 원소 수 n
			l=Integer.parseInt(st.nextToken()); // 칼로리 제한 l
			
			taste=new int[n]; // 각 재료의 맛
			calorie=new int[n]; // 각 재료의 칼로리
			for(int i=0;i<n;++i) { // 재료의 맛,칼로리 정보 입력받기 (각자 배열 따로 저장)
				st = new StringTokenizer(in.readLine());
				taste[i]=Integer.parseInt(st.nextToken());
				calorie[i]=Integer.parseInt(st.nextToken());
			}
			
			int result=0; // 결과 저장할 변수 result
			for(int i=0; i<(1<<n); ++i) { // 0 = 아무 것도 안 넣는 경우, 1<<n = 모든 원소 다 넣는 경우
				// 각 부분집합 전체를 탐색
				int fav = 0; // 만족감
				int cal = 0; // 칼로리
				for(int j=0; j<n; ++j) {    // 원소의 수만큼 비트를 비교
					if((i & (1<<j)) != 0) { // i의 j번째 비트가 1이면 j번째 원소 선택한 것 =>결과에 더하기
						fav += taste[j]; // j번째 원소 넣었을 때의 만족감 업데이트
						cal += calorie[j]; // j번째 원소 넣었을 때의 칼로리 업데이트
						if(cal>l) break; // j번째 원소 넣었는데 칼로리 제한 넘어감 > 현재 부분집합 더 볼 필요 없음, for문 break
					}
					if(j==n-1) {
						if(fav>result) result=fav; //현재 탐색중인 부분집합의 총 만족감이 현재까지의 만족감보다 높으면 result 업데이트
					}
				}
			}
			
			System.out.println("#"+z+" "+result);
		}
	}

}
