import java.io.*;
import java.util.*;

public class Solution {
	/*
	 * 간적크..
	 */
	static int n;
	static long[] x;
	static long[] y;
	static double e;
	
	static List<Turnel> turnelList;
	static int[] parents;
	
	static class Turnel implements Comparable<Turnel> {
		int from, to;
		double dist;
		
		public Turnel(int from, int to) {
			this.from = from;
			this.to = to;
			this.dist = ((x[from]-x[to])*(x[from]-x[to])+(y[from]-y[to])*(y[from]-y[to]))*e;
		}
		
		@Override
		public int compareTo(Turnel o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine());
		for(int z=1;z<=t;++z) {
			n = Integer.parseInt(in.readLine());
			x = new long[n];
			y = new long[n];
			
			st = new StringTokenizer(in.readLine());
			for(int i=0;i<n;++i) { // x좌표
				x[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for(int i=0;i<n;++i) { // y좌표
				y[i] = Long.parseLong(st.nextToken());
			}
			
			e = Double.parseDouble(in.readLine());
			
			/*
			 * MST 만들기... 그때의 result 구하기.
			 * 크루스칼? 프림?
			 */

			turnelList = new ArrayList<Turnel>();
			for(int i=0;i<n-1;++i) {
				for(int j=i+1;j<n;++j) {
					turnelList.add(new Turnel(i, j));
				}
			}
			
			// 간선 리스트를 가중치 기준으로 오름차순 정렬
			Collections.sort(turnelList);
			
			// V개의 정점으로 make set 작업
			make();
			
			//
			double result=0; // MST 비용
			int cnt=0; // 연결된 간선 개수
			for(Turnel turnel : turnelList) { // 비용이 적은 간선 순으로 꺼내서 처리
				if(union(turnel.from, turnel.to)) {
					// 사이클 발생하지 않음
					result += turnel.dist;
					if(++cnt == n-1) break;
				}
			}
			long result2 = Math.round(result);
			sb.append("#").append(z).append(" ").append(result2).append("\n");
		}
		System.out.println(sb);
	}
	
	static void make() {
		parents = new int[n];
		for(int i=0;i<n;++i) { // 각 정점에 대해 makeset 작업 -> 자기 자신의 부모를 자신으로 갖는 최소단위 서로소집합 만들기
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; // 사이클이 발생했다는 뜻
		else {
			parents[bRoot] = aRoot;
			return true;
		}
	}
}
