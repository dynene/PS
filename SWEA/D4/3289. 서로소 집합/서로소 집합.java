import java.io.*;
import java.util.*;

public class Solution {

	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int z=1;z<=t;++z) {
			sb.append("#").append(z).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			make();
			
			for(int i=0;i<m;++i) {
				st = new StringTokenizer(in.readLine());
				int check = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				if(check==0) { // union
					union(a, b);
				}
				else { // find
					if(find(a)==find(b)) sb.append(1);
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void make() {
		parents = new int[n];
		for(int i=0;i<n;++i) {
			parents[i]=i;
		}
	}
	
	static boolean union(int a, int b) {
		int rA = find(a);
		int rB = find(b);
		if(rA==rB) return false;
		else {
			parents[rB] = rA;
			return true;
		}
	}
	
	static int find(int a) {
		if(parents[a]==a) return a;
		else return parents[a] = find(parents[a]);
	}
}
