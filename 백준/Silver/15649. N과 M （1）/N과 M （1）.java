import java.util.Scanner;
import java.util.*;

public class Main { // nCm 구하기, 1<=m<=n<=8
	private static int n, r;
	private static int numbers[]; // 결과 집합
	private static boolean isSelected[];

	public static void main(String[] args) {
		//헐.
		//main + ctrl + space + enter
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		r=sc.nextInt();
		numbers=new int[r];
		isSelected=new boolean[n];
		
		permutation(0);
		
	}
	
	public static void permutation(int cnt) {
		if(cnt==r) {
			for(int i=0;i<r;++i) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
            return;
		}
		for (int i = 0; i < n; ++i) {
			if(isSelected[i]) continue;
			
			numbers[cnt] = i+1; // 어느 자리에 넣을 것인지
			isSelected[i]=true;
			permutation(cnt+1); // 재귀 호출
			isSelected[i]=false;
		}
	}


}
