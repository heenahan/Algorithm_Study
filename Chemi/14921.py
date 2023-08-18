n = int(input())
l = list(map(int,input().split()))
l.sort()
left = 0
right = n-1

ans = l[left] + l[right]
while left < right:
    tmp = l[left] + l[right]
    if abs(ans) > abs(tmp):
        abs = tmp
    if tmp < 0 :
        left += 1
    else:
        right -= 1
print(ans)

