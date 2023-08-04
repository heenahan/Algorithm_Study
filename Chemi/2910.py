n,c= map(int,input().split())
l = list(map(int,input().split()))

d = {}
ans = []

for i in l:
    if i in d:
        d[i] += 1
    else:
        d[i] = 1

sorted_d = sorted(d.items(), key = lambda d: d[1], reverse=True)

for a in sorted_d:
    num,cnt = a
    for _ in range(cnt):
        ans.append(num)

print(' '.join(map(str,ans)))