n = int(input())
l = list(map(int,input().split()))
visited = [1] * n
answer = []

for i in range(1,n):
    for j in range(i):
        if l[j] < l[i]:
            visited[i] = max(visited[i],visited[j]+1)

val = max(visited)
 
for i in range(n-1,-1,-1):
    if visited[i] == val:
        answer.append(l[i])
        val -= 1

print(max(visited))
print(' '.join(map(str, sorted(answer))))


