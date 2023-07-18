n = int(input())
m = int(input())
seat = [0]*(n+1)
nums = []

for i in range(m):
    vip = int(input())
    seat[vip] = 1

cnt = 0

for i in range(len(seat)):
    if seat[i] == 1:
        nums.append(cnt)
        cnt = 0
    else:
        cnt += 1
nums.append(cnt)


print(nums)