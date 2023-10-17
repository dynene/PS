import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * n*n 게임판 크기 4<=n<=100
	 * 각 칸의 수 0~9
	 * 가장 오른쪽 아래 항상 0
	 */
	
	/*
	 * 각 칸의 수 = 현재 칸에서 갈 수 있는 거리
	 * 0: 종착점
	 * 항상 현재 칸에 적힌 수만큼 오른쪽이나 아래로 가야 함 (한번에 쭉)
	 * 가장 왼쪽 위 => 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수?
	 */
	
	static int n;
	static int[][] arr;	
	static long[][] result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		arr = new int[n][n];
		result = new long[n][n];
		
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result[0][0] = 1;
		
		// 각 칸으로 이동하는 경우의 수...를 기록하기?
		for(int i=0;i<n;++i) {
			for(int j=0;j<n;++j) { // 어차피 오른쪽, 아래쪽으로만 이동하므로 이렇게 탐색해도 괜찮을듯?
				if(arr[i][j]==0) continue; // 0이면 중단점
				
				if(i+arr[i][j] < n) { // 아래로 이동 가능
					result[i+arr[i][j]][j] += result[i][j];
				}
				
				if(j+arr[i][j] < n) { // 오른쪽으로 이동 가능
					result[i][j+arr[i][j]] += result[i][j];
				}
			}
		}
		
		System.out.println(result[n-1][n-1]);
	}

}
