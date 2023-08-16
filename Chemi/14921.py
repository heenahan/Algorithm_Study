n = int(input())
l = list(map(int,input().split()))
l.sort()
left = 0
right = n-1
min_val = 1e9

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

# n = int(input())
# l = list(map(int,input().split()))
# l.sort()
# left = -1
# right = -1
# min_val = 1e9

# def find(min_val,new_val):
#     if abs(new_val) <= abs(min_val):
#         return new_val
#     else:
#         return min_val

# for i in range(len(l)-1):
#     if l[i] < 0 and l[i+1] >= 0:
#         left = i
#         right = i+1

# if left ==-1 and right == -1:
#     if l[0] >= 0:
#         print(l[0]+l[1])
#     else:
#         print(l[-1]+l[-2])
#     exit(0)

# while(left >= 0 and right < len(l)):
#     if l[left] + l[right] == 0:
#         print(0)
#         break
#     elif l[left] +  l[right] > 0:
#         min_val = find(min_val,  l[left]+l[right])
#         left -= 1
#     else:
#         min_val = find(min_val,  l[left]+l[right])
#         right += 1
# print(min_val)

# 93%

# 93%

# 반례 : 왼쪽이 끝나서 더이상 탐색할 수 없음애도 오른쪽 값에 더 최선의 결과각 남아있음
# 5
# -6 1 2 10 20
