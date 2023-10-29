import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static int min, max;	
	static boolean visited[][];
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1}; //상하좌우
	
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		
		min = 101;
		max = 0;
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(min>map[i][j]) min=map[i][j];
				if(max<map[i][j]) max=map[i][j];
			}
		}
		
		// 새로운 영역 발견할 때마다 count ++ 해서 세고
		// n~그 count 값의 최댓값 세기.. 이래도 시간 초과가 안 날까?..
		
		for(int z=min-1;z<=max+1;++z) {
			// 높이가 z일 때의 안전 영역 개수 세기
			visited = new boolean[n][n];
			int cnt=0;
			
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					if(visited[i][j]==false && map[i][j]>z) {
						cnt++;
						dfs(z, i, j);
					}
				}
			}
			
			if(result<cnt) result=cnt;
		}
		
		System.out.println(result);
	}
	
	static void dfs(int height, int x, int y) {
		
		for(int i=0;i<4;++i) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0 && nx<n && ny>=0 && ny<n) { // map 범위 안이라면
				if(visited[nx][ny]==false && map[nx][ny]>height) {
					visited[nx][ny] = true;
					dfs(height, nx, ny);
				}
			}
		}
	}

}
