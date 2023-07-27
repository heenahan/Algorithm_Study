n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * m for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

max_val = 0

# depth가 4일떄 out
def dfs(depth, x, y, val):
    global max_val

    if depth == 4:
        max_val = max(max_val, val)
        return

    for i in range(4):  # 4방향으로 이동하도록 수정
        nx, ny = x + dx[i], y + dy[i]

        if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny]:
            visited[nx][ny] = 1
            dfs(depth + 1, nx, ny, val + graph[nx][ny])
            visited[nx][ny] = 0

# 2 갈래 처리
def two_way(x, y):
    global max_val
    for l in range(4):
        tmp = graph[x][y]
        for k in range(3):
            t = (l + k) % 4
            nx = x + dx[t]
            ny = y + dy[t]

            if not (0 <= nx < n and 0 <= ny < m):
                tmp = 0
                break
            tmp += graph[nx][ny]

        max_val = max(max_val, tmp)  # 최댓값 업데이트

for i in range(n):
    for j in range(m):
        visited[i][j] = 1
        dfs(1, i, j, graph[i][j])
        visited[i][j] = 0

        two_way(i, j)  # two_way 함수 호출 추가

print(max_val)
