import sys
sys.setrecursionlimit(10**7)

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
cnt = 0
# 88프로 시간 초과...100만 case?

def dfs(x,y,direction):
    global cnt
    # 종료 조건
    if x==n-1 and y==n-1:
        cnt += 1
        return
    # 가로
    if direction == 0 or direction == 2:
        if y+1 < n and graph[x][y+1] == 0:
             dfs(x,y+1,0)
    
    # 세로
    if direction == 1 or direction == 2:
        if x+1 < n and graph[x+1][y] == 0:
            dfs(x+1,y,1)
    
    # 대각선
    if x+1 < n and y+1 < n and graph[x+1][y] == 0 and graph[x][y+1] == 0 and graph[x+1][y+1] == 0:
        dfs(x+1,y+1,2)

dfs(0,1,0)
print(cnt)