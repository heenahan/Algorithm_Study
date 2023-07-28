n = int(input())
target = int(input())

graph = [[0]*n for _ in range(n)]
x,y = n//2,n//2

graph[x][y] = 1
dx,dy = [0,1,0,-1],[-1,0,1,0]

num,direction,count = 0,0,0

while num <= n*n:
    nx,ny = x + dx[direction], y+dy[direction]
    graph[nx][ny] = num

    if num == target:
        target_x , target_y = nx,ny

    x,y = nx,ny
    count += 1 # 갱신용

    if count == (num // 2) + 1:
        direction = (direction + 1) %4
        count = 0

    num += 1
