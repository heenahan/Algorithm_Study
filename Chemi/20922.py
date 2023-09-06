
# n,k = map(int,input().split())
# l = list(map(int,input().split()))
# d = {}
# ans = []
# cnt = 0

# for i in l:
#     if i in d:
#         d[i] += 1
#         if d[i] > k:
#           ans.append(cnt)
#           d = {}
#           cnt = 0
#     else:
#         d[i] = 1
#     cnt += 1
# ans.append(cnt)
# print(max(ans))

from collections import defaultdict

n,k = map(int,input().split())
l = list(map(int,input().split()))
dic = defaultdict(int)
left,right = 0,0
ans = 0

while right < n:
    if dic[l[right]] >= k:
        dic[l[left]] -= 1
        left += 1
    else:
        dic[l[right]] += 1
        right += 1
        ans = max(ans, right-left)
print(ans)

