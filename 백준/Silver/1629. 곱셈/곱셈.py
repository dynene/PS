# boj 1629 곱셈
# a를 b번 곱한 수를 c로 나눈 나머지
from sys import stdin

a, b, c = map(int, stdin.readline().split())

def mult(a, b):
    if b == 1:
        return a % c
    else:
        tmp = mult(a, b//2)
        if b % 2 == 0:
            return tmp * tmp % c
        else:
            return tmp * tmp * a % c

print(mult(a, b))