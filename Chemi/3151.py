n = int(input())
l = list(map(int,input().split()))
l.sort()
m = {}
p = {}

cnt = 0

for i in l:
    if i < 0:
        m[i] = m.get(m[i],0)+1
    else:
        p[i] = p.get(p[i],0)+1




