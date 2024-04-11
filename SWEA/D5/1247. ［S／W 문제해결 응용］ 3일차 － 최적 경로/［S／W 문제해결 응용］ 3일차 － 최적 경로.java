import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 회사 출발 -> 고객 n명 -> 집 도착
	 * 가장 짧은 경로의 이동거리
	 * 
	 * (x1,y1) - (x2,y2) 거리 = |x2-x1| + |y2-y1|
	 */
	static int n; // 고객의 수
	static int result;
	static Point start;
	static Point end;
	static Point[] customers;
	
	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine());
		
		for(int z=1;z<=t;++z) {
			n = Integer.parseInt(in.readLine());
			customers = new Point[n];
			result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(in.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int i=0;i<n;++i) { // 회사, 집, n명 고객 좌표
				customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 탐색 ㄱㄱ
			int[] p = new int[n];
			for(int i=0;i<n;++i) {
				p[i] = i;
			}
			
			do {
				// 현재 조합으로 최단경로 구하기
				int len = Math.abs(start.x-customers[p[0]].x) + Math.abs(start.y-customers[p[0]].y);
				for(int i=1;i<n;++i) { // 
					len += Math.abs(customers[p[i]].x-customers[p[i-1]].x) + Math.abs(customers[p[i]].y-customers[p[i-1]].y);
				}
				len += Math.abs(customers[p[n-1]].x-end.x) + Math.abs(customers[p[n-1]].y-end.y);
				
				if(len<result) result=len;
				
			} while(np(p));
			
			// 결과 저장
			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
		
	}// 메인 종료
	
	private static boolean np(int p[]) {
		int i = n-1;
		while(i>0 && p[i-1]>=p[i]) --i;
		if(i==0) return false;
		
		int j=n-1;
		while(p[i-1]>=p[j]) --j;
		int tmp=p[i-1];
		p[i-1]=p[j];
		p[j]=tmp;
		
		int k=n-1;
		while(i<k) {
			tmp=p[i];
			p[i]=p[k];
			p[k]=tmp;
			i++;
			k--;
		}
		
		return true;
	}
	
}
