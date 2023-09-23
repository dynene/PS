#include <iostream>
#include <list>
using namespace std;

int main() {
	int n; //정렬할 수의 개수
	cin >> n;

	list<int> arr;
	for (int i = 0; i < n; ++i) {
		int tmp;
		cin >> tmp;
		arr.push_back(tmp);
	}

	arr.sort();

	list<int>::iterator it = arr.begin();

	for (; it!=arr.end(); ++it) {
		cout << *it << "\n";
	}
	return 0;
}