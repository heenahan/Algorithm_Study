from collections import deque

n = int(input())
m = int(input())
people = [[0] * (n+1) for _ in range(n+1)]
visited= [0 for _ in range(n+1)]
ans = 0

for i in range(m):
    a,b = map(int,input().split())
    people[a].append(b)
    people[b].append(a)

def bfs(k):
    q = deque()
    q.append(k)
    visited[k] = 1
    
    while q:
       person = q.popleft()
       for i in people[person]:
           if visited[i]==0:
               q.append(i)
               visited[i] = visited[person] + 1

bfs(1)

ans = 0
for i in range(2,n+1):
    if visited[i] < 4 and visited[i] != 0:
        ans += 1
print(ans)