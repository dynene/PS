
import java.io.*;
import java.util.*;

public class Main { // 실1
	/*
	 * 절댓값 힙
	 * 1. 배열에 정수 x(!=0)을 넣는다
	 * 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
	 *    절댓값 가장 작은 값이 여러 개인 경우, 가장 작은 수를 선택한다.
	 *    배열이 비어있는 경우 0을 출력한다.
	 *    
	 * 연산 개수 1<=n<=10만
	 * x!=0: 1번 수행
	 * x==0: 2번 수행
	 * x는 int 범위 내의 값
	 */
  
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력 결과 저장할 stringbuilder
		int n = Integer.parseInt(in.readLine()); // 연산 개수 n
		PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> { // 우선순위 큐=기본으로 최소힙, 비교 메소드 오버라이딩해서 사용
			// a,b의 절댓값 구하기
			int aAbs = Math.abs(a);
			int bAbs = Math.abs(b);
			if(aAbs==bAbs) { // 절댓값 같은 경우
				if(a>b) return 1; // a, b값을 기준으로 대소 비교
				else return -1;
			}
			else { // 절댓값 다른 경우
				if(aAbs>bAbs) return 1; // a,b의 절댓값을 기준으로 대소 비교
				else return -1;
			}
			// x가 0으로 들어오는 경우는 없으므로 a, b가 같은 경우는 고려하지 않아도 됨
		});
		
		for(int i=1;i<=n;++i) { // 연산 횟수만큼 반복
			int x = Integer.parseInt(in.readLine()); // 연산 정보 x
			if(x!=0) { // x가 0이 아니면
				heap.add(x); // x를 heap에 add
			}
			else { // x==0이면
				if(heap.peek()==null) { // heap에 원소  있는지 확인
					// 없으면 0 출력
					sb.append(0).append("\n");
				}
				else { // 있으면 heap의 최상위 노드 출력
					sb.append(heap.poll()).append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}
}
