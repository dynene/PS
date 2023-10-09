# boj 2110 공유기 설치 - 이분 탐색

n, c = map(int, input().split())

arr = []
for i in range(0, n):
    arr.append(int(input()))

arr.sort()

first = 1
last = arr[-1] - arr[0]
result = 0

while first <= last:
    mid = (first + last) // 2
    cnt = 1
    latest = arr[0]

    for i in range(0, len(arr)):
        if arr[i] - latest >= mid:
            latest = arr[i]
            cnt += 1

    if cnt >= c:
        result = mid
        first = mid + 1
    else:
        last = mid - 1

print(result)