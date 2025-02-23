/*
* 250223
* BOJ2309 일곱 난쟁이 - 브1
*/

#include <iostream>
#include <list>

using namespace std;


int main() {
	int arr[9];

	for (int i = 0; i < 9; ++i) {
		cin >> arr[i];
	}

	for (int i = 0; i < 9; ++i) {
		for (int j = i; j < 9; ++j) {
			if (i == j) continue;
			// i, j 제외하고 총합 더해서 100과 같은지 비교
			int sum = 0;
			for (int a = 0; a < 9; ++a) {
				if (a != i && a != j) {
					sum += arr[a];
				}
			}
			if (sum == 100) {
				// 찾았으면 정렬해서 출력하고 종료
				list<int> list;
				for (int a = 0; a < 9; ++a) {
					if (a != i && a != j) {
						list.push_back(arr[a]);
					}
				}
				list.sort();
				for (int i : list) {
					cout << i << "\n";
				}
				return 0;
			}
		}
	}

	return 0;
}