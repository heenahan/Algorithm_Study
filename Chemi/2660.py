from collections import Counter
n = int(input())
graph = [[1e9]*(n+1) for _ in range(n+1)]
while True:
    a,b = map(int,input().split())
    if a == -1 and b == -1:
        break
    graph[a][b] = 1
    graph[b][a] = 1

for i in range(1,n+1):
    for j in range(1,n+1):
        if i==j:
            graph[i][j] = 0

for k in range(1,n+1):
    for i in range(1,n+1):
        for j in range(1,n+1):
            graph[i][j] = min(graph[i][j],graph[k][j]+graph[i][k])

res = []
for i in range(1,n+1):
    res.append(max(graph[i][1:]))

min_val = min(res)
print(min_val, res.count(min_val))
for i in range(len(res)):
    if min_val == res[i]:
        print(i+1, end = ' ')


