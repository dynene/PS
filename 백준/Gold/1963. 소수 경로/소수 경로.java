/*
 * 230920
 * BOJ 1963 소수 경로
 * 골드 4
 * 
 * 메모리: 22464KB
 * 시간: 156ms
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static String a; // 바꿀 소수
	static String b; // 목표 소수
	
	static boolean[] prime;
	
	public static class Num {
		String curnum;
		int cnt;
		public Num(String curnum, int cnt) {
			this.curnum = curnum;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 1. 에라토스테네스의 체 이용해서 10000 미만의 소수 모두 구하기
		prime = new boolean[10000];
		prime[0]=prime[1]=true;
		for(int i=2;i<10000;++i) {
			if(prime[i]==false) {
				for(int j=i*2;j<10000;j+=i) {
					prime[j] = true;
				}
			}
		} // i: prime[i]가 false면 소수
		
		int t = Integer.parseInt(in.readLine());
		for(int z=1;z<=t;++z) { // 테케 시작
			st = new StringTokenizer(in.readLine());
			a = st.nextToken(); // 시작 소수
			b = st.nextToken(); // 끝 소수
			
			int result = -1;
			
			boolean[] visited = new boolean[10000];
			for(int i=0;i<1000;++i) {
				visited[i]=true; // 1000 이하는 탐색하지 않도록, visited를 true로 미리 설정해 둔다.
			}
			Queue<Num> queue = new ArrayDeque<>();
			queue.offer(new Num(a, 0));
			visited[Integer.parseInt(a)] = true;
			
			while(!queue.isEmpty()) { // bfs 진행
				Num cur = queue.poll(); // queue의 첫 번째 원소 뽑고
				if(cur.curnum.equals(b)) { // b를 찾았으면 result에 답 저장하고 break;
					result = cur.cnt;
					break;
				}
				// b 도달하지 못한 경우
				for(int i=0;i<4;++i) { // 1~4번째 자리에 대해
					StringBuilder tmp = new StringBuilder(cur.curnum);
					for(int j=0;j<10;++j) { // 0~9로 바꿔봄
						tmp.setCharAt(i, (char)(j+'0'));
						String tmp2 = tmp.toString();
						int next = Integer.parseInt(tmp2);
						if(visited[next]==false && prime[next]==false) { // 바꾼 수가 아직 찾아본 적 없는 수이고 && 소수이면
							visited[next] = true; // 방문 체크하고
							queue.offer(new Num(tmp2, cur.cnt+1)); // 큐에 넣기
						}
					}
				}
			}

			if(result==-1) {
				sb.append("Impossible\n");
			} else {
				sb.append(result).append("\n");
			}
		}
		System.out.println(sb);
	}
}
