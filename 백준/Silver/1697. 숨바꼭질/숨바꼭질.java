import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 수빈이 시작점, 0~10만
		int k = sc.nextInt(); // 동생 시작점, 0~10만
		
		boolean visited[] = new boolean[100001];
		
		/*
		 * n에서 출발, +1 / -1 / *2 연산으로 k 만들 때 필요한 최소 연산 횟수는?
		 */
		
		if(n==k) { // 둘이 시작점이 같은 경우
			System.out.println(0);
			return;
		}
		
		// 연산 세 가지 차례대로 수행하고, 케이스마다 3가지씩 또 수행하면서 k 최대한 빨리 도착하는 경우 찾기
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {n, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			visited[cur[0]] = true;
			
			if(cur[0]+1==k||cur[0]-1==k||cur[0]*2==k) {
				System.out.println(cur[1]+1);
				break;
			} else {
				if(cur[0]*2 <= 100000)
					if(visited[cur[0]*2]==false)
						queue.offer(new int[] {cur[0]*2, cur[1]+1});
				if(cur[0]+1 <= 100000)
					if(visited[cur[0]+1]==false)
						queue.offer(new int[] {cur[0]+1, cur[1]+1});
				if(cur[0]-1 > 0)
					if(visited[cur[0]-1]==false)
						queue.offer(new int[] {cur[0]-1, cur[1]+1});

			}
		}
	}
}
