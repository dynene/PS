
import java.util.Scanner;

public class Solution {
	static int n;
	static int[][] arr;
	static int[] dy= {1,0,-1,0};
	static int[] dx= {0,1,0,-1};
		
	public static void main(String[] args) {
//		1. 일단 지금 칸 채움
//		2. 다음 칸 좌표 찍어봄
//		3. 혹시 이미 채워져 있거나 맵 넘어가면 방향 전환
		
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int z=1;z<=t;++z) {
			n=sc.nextInt();
			arr=new int[n][n];
			
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					arr[i][j]=0;
				}
			}
			
			// 달팽이 출발..
			int dir=0;
			int x=0;
			int y=0;
			for(int i=1;i<=n*n;++i) {
				arr[x][y]=i;
				
				int nextx = x+dx[dir];
				int nexty = y+dy[dir];
				if(nextx<0 || nextx>=n || nexty<0 || nexty>=n || arr[nextx][nexty]!=0) {
					if(dir<3) dir++;
					else if (dir==3) dir=0;
				}
				
				x+=dx[dir];
				y+=dy[dir];
			}
			
			System.out.println("#"+z);
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	
}