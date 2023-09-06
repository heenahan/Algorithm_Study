n,m = map(int,input().split())

infinity = int(1e9)
graph = [[infinity]*(n+1) for _ in range(n+1)]
cnt = 0

for _ in range(m):
    a,b = map(int,input().split())
    graph[a][b] = 1
    graph[b][a] = 1

for i in range(1,n+1):
    for j in range(1,n+1):
        if i==j:
            graph[i][j] = 0

for i in range(1,n+1):
    for j in range(1,n+1):
        for k in range(1,n+1):
            graph[k][j] = min(graph[j][k],graph[j][i]+graph[i][k])

min_val = 120
ans = 0

for i in range(n,0,-1):
    s = sum(graph[i][1:])
    if min_val >= s:
        min_val = s
        answer = i
print(answer)

