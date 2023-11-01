import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dac(n, 0, 0));
		
	}
	
	static int dac(int cur, int x, int y) {
		if(cur==2) {
			int[] tmp = {map[x][y], map[x+1][y], map[x+1][y+1], map[x][y+1]};
			Arrays.sort(tmp);
			return tmp[2];
		}
		int half = cur/2;
		int[] tmp = {dac(half, x, y), dac(half, x+half, y), dac(half, x+half, y+half), dac(half, x, y+half)};
		Arrays.sort(tmp);
		return tmp[2];
	}

}
