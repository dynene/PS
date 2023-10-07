
/*
 * 231007
 * BOJ 2805 - 나무 자르기
 * 실버 2
 * 
 * 메모리 KB
 * 시간 ms
 */

import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * 1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000
	 * 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0
	 * 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
	 */
	
	static int n, m;
	static int[] tree;
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 나무 수
		m = Integer.parseInt(st.nextToken()); // 가져갈 나무 길이
		
		tree = new int[n]; // 각 나무의 높이
		
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<n;++i) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		// 모든 tree[i]-result를 더한 값이 m 이상이 되는 첫 번째 result값 찾기
		// result를 큰 값부터 줄여나가야 하는 거 아님?
		
		int start=0, end=tree[n-1];
		int mid;
		
		while(true) {

			if(start>end) {
				result = (start+end)/2;
				break;
			}

			mid = (int) Math.floor((start+end)/2);
			
			// 절단기 높이 mid로 했을 때 가져갈 수 있는 나무 높이
			long cur = 0;
			for(int i=0;i<n;++i) {
				if(tree[i]>mid) {
					cur += tree[i]-mid;
				}
			}

			if(cur<m) { // 더 아래서 잘라야 함
				end = mid-1;
			} else if(cur>m) { // 더 위에서 잘라도 되는지 확인해야..
				start = mid+1;
			} else {
				result = mid;
				break;
			}
			

		}
		
		System.out.println(result);
	}
}
