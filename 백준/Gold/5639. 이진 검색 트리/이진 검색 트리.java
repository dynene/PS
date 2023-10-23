import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	/*
	 * 이진 검색 트리의 전위 순회 결과 가지고 후위 순회 결과 구하기
	 */

	static int[] preorder;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String tmp = null;
		int i=0;
		preorder = new int[10001]; // 노드 최대 10000개
		 
		while(true)	{
			tmp = in.readLine();
			if(tmp==null) break;
			// 입력 제대로 들어온 경우
			preorder[i++] = Integer.parseInt(tmp);
		}
		
		postorder(0, i-1);
	}
	
	static void postorder(int start, int end) {
		if (start > end) return;
		
		int mid = start+1; // end보다 큰 값이 하나도 없으면 새로운 끝값도 end가 돼야 함
		
		for(int i=start+1; i<end+1; ++i) {
			if(preorder[start] < preorder[i]) {
				mid = i;
				break; // 새로운 mid 찾았으면 그 뒤로는 전부 mid보다 큰 값 이므로 break; 
			}
		}
		
		// postorder 순서대로 실행
		postorder(start+1, mid-1);
		postorder(mid, end);
		System.out.println(preorder[start]);
	}
}
