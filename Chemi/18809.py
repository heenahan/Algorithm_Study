from collections import deque

n,m,g,r = map(int,input().split())
graph = [list(map(int,input().split())) for i in range(n)]

by_soil = []
soil = []
flower = 0

def bfs(i,j):
    visited = [[0,0]*m for _ in range(n)]
    q = deque()
    q.append([i,j])


for i in range(n):
    for j in range(n):
        if graph[i][j] == 2:
            by_soil.append([i,j])
        elif graph[i][j] == 1:
            soil.append([i,j])

# if visited[i][j][0]==1 and visited[i][j][1] == 1:
# 꽃 조건
# if visited[i][j][0]==1 or visited[i][j][1] == 1:
# 꽃 탈락 조건

# 배양 계획을 미리 설정해두고 탐색을 시작
# 1지점을 r로 시작? g로 시작? 경우를 모두 고려(조합?), 완전탐색, 방문여부 항상 초기화

# bfs) green, red에 관한 for문을 돌려야 함

for i in range(n):
    for j in range(m):
        if graph[i][j] == 2:
            
            # bfs(i,j)