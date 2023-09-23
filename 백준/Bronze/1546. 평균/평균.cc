//boj 1546. 평균 - 브1
#include <iostream>
using namespace std;

int main() {
	int numOfSubject;
	double score[1000];

	cin >> numOfSubject; //과목 개수 입력받기 
	for (int i = 0; i < numOfSubject; ++i) { //과목별 원 점수 입력받기
		cin >> score[i];
	}
	
	//원 점수 중 최고점 구하기
	double maxScore = score[0];
	int maxSubject;
	for (int i = 0; i < numOfSubject; ++i) {
		if (maxScore < score[i]) {
			maxScore = score[i];
			maxSubject = i;
		}
	}

	//각 과목 점수를 고치기
	for (int i = 0; i < numOfSubject; ++i) {
		score[i] = score[i] / maxScore * 100;
	}

	//새로운 평균 출력하기
	double sumOfScore = 0;
	for (int i = 0; i < numOfSubject; ++i) {
		sumOfScore += score[i];
	}
	double avg = sumOfScore / numOfSubject;

	cout << avg;
}