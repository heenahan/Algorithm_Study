n,k = map(int,input().split())
l = []
cnt = 0

for i in range(n):
    l.append(int(input()))

for i in range(len(l)-1,-1,-1):
    if (k == 0):
        break
    if(k//l[i] >= 1):
        a,k = divmod(k,l[i])
        cnt += a

print(cnt)