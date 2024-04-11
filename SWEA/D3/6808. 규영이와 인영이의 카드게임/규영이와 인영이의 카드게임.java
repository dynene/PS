import java.io.*;
import java.util.*;

public class Solution {
	static int[] me, op;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(in.readLine());
		
		for(int z=1;z<=t;++z) {
			me = new int[9]; // 규영이 카드
			op = new int[9]; // 인영이 카드
			
			int[] check = new int[18]; // 규영이 무슨 카드 받았는지 체크하기 위한 배열
			st = new StringTokenizer(in.readLine()); // 규영이가 받은 카드 배열 읽기
			for(int i=0;i<9;++i) {
				check[Integer.parseInt(st.nextToken())-1] = 1; // 규영이가 받은 카드 번호 인덱스를 1로 체크
			}
			int i1=0, i2=0;
			for(int i=0;i<18;++i) { // 1~18에 대해
				if(check[i]==0) {
					op[i1++] = i+1;
				}
				else {
					me[i2++] = i+1;
				}
			}
			
			int win=0;
			int lose=0;
			
			Arrays.sort(op);
			do {
				int myscore=0;
				int opscore=0;
				for(int i=0;i<9;++i) {
					if(me[i] > op[i]) myscore += me[i]+op[i];
					else opscore += me[i]+op[i];
				}
				if(myscore > opscore) win++;
				else if(myscore < opscore) lose++;
			} while(np(op));
			
			sb.append("#").append(z).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean np(int[] p) {
		int n = p.length;
		int i = n-1;
		while(i>0 && (p[i-1]>=p[i])) --i;
		if(i==0) return false;
		
		int j=n-1;
		while(p[i-1]>=p[j]) --j;
		
		int tmp = p[i-1];
		p[i-1] = p[j];
		p[j] = tmp;
		
		int k=n-1;
		while(i<k) {
			tmp = p[i];
			p[i] = p[k];
			p[k] = tmp;
			i++;
			k--;
		}
		return true;
	}
}
