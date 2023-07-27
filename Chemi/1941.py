from collections import deque

graph = [list(input()) for _ in range(5)]

dx,dy = [1,-1,0,0],[0,0,-1,1]

def dfs(x,y,cnt_s,cnt_y,visited):
    if cnt_s+cnt_y == 7:
        if cnt_s >= 4:
            return 1
        return 0
    if cnt_y >= 4:
        return 0
    
    ans = 0
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0<=nx  < 5 and 0<= ny < 5 and not visited[nx][ny]:
            visited[nx][ny] = 1
            if graph[nx][ny] == 'S':
                ans += dfs(nx, ny, cnt_s + 1, cnt_y, visited)
            else:
                ans += dfs(nx, ny, cnt_s, cnt_y + 1, visited)
            visited[nx][ny] = 0
    
    return ans
            
final_ans = 0
for i in range(5):
    for j in range(5):
        visited = [[0]*5 for _ in range(5)]
        visited[i][j] = 1
        if graph[i][j] == 'S':
            final_ans += dfs(i,j,1,0,visited)

print(final_ans)

# 정답 풀이

from collections import deque

graph = [list(input()) for _ in range(5)]

dx, dy = [1, -1, 0, 0], [0, 0, -1, 1]

def bfs(x, y, visited):
    queue = deque([(x, y)])
    cnt_s, cnt_y = 0, 0

    while queue:
        x, y = queue.popleft()
        visited[x][y] = True

        if graph[x][y] == 'S':
            cnt_s += 1
        else:
            cnt_y += 1

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < 5 and 0 <= ny < 5 and not visited[nx][ny]:
                queue.append((nx, ny))
                visited[nx][ny] = True

    if cnt_s + cnt_y == 7 and cnt_s >= 4:
        return 1
    return 0

final_ans = 0
for i in range(5):
    for j in range(5):
        visited = [[False] * 5 for _ in range(5)]
        if not visited[i][j]:
            final_ans += bfs(i, j, visited)

print(final_ans)
