n = int(input())
nums = list(map(int,input().split()))
operator = list(map(int,input().split()))

max_val = -1e9
min_val = 1e9

#점검을 두번 하고 있다.
def dfs(depth,current):
    global max_val, min_val
    if depth == n:
        max_val = max(max_val,current)
        min_val = min(min_val,current)
        return 
    #숫자 -> 여기서 반복이 불필요하게 이어지고 있음
    #1부터 항상 시작하는 것이 아닌 다음 숫자부터 다시 시작하도록 변경 필요
    for j in range(len(operator)): #연산자
        # 연산을 하기 위해
        if operator[j] > 0:
            operator[j] -= 1
            if j == 0:
                dfs(depth+1,current + nums[depth])
                operator[j] += 1
            elif j == 1:
                dfs(depth+1,current - nums[depth])
                operator[j] += 1
            elif j == 2:
                dfs(depth+1,current * nums[depth])
                operator[j] += 1
            else:
                dfs(depth+1,int(current / nums[depth]))
                operator[j] += 1
dfs(1,nums[0])

print(max_val)
print(min_val)