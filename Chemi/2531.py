from collections import defaultdict
n,d,k,c = map(int,input().split())
l = []
max_val  = 0

for i in range(n):
    l.append(int(input()))
l.extend(l)


# # 시간초과 풀이 1%
# # for i in range(n):
# #     s = set()
# #     for j in range(i,i+k):
# #         if j >= len(l):
# #             j -= len(l)
# #         s.add(l[j])
# #     if c not in s:
# #         s.add(c)
# #     max_val = max(len(s),max_val)

# # print(max_val)

# # 시간초과 풀이 3%
# # for i in range(n):
# #     if i == 0:
# #         tmp = l[i:i+k]
# #     else:
# #         tmp.pop(0)
# #         if i+k-1 >= len(l):
# #             tmp.append(l[i+k-1-len(l)])
# #         else:
# #             tmp.append(l[i+k-1])
# #     if c not in tmp:
# #         tmp.append(c)
# #     max_val = max(len(set(tmp)),max_val)
    
# # print(max_val)

left = 0
right = 0
d_dict = defaultdict(int)

d_dict[c] += 1

# k만큼 가지기
while right < k:
    d_dict[l[right]] += 1
    right += 1

while right < len(l):
    max_val = max(max_val, len(d_dict))
    
    d_dict[l[left]] -= 1
    if d_dict[l[left]] == 0:
        del d_dict[l[left]]

    d_dict[l[right]] += 1

    left += 1
    right += 1
print(max_val+1)
