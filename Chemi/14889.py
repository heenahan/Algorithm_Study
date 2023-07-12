import itertools

a = int(input())
nums = [i for i in range(a)]
arr = [list(map(int, input().split())) for _ in range(a)]
min_diff = 101

# 필요한것 : 인원수 n, 조합, 제외조건 추가

def cal_diff(team,arr):
    total = 0
    for i in range(a//2):
        for j in range(i+1, a//2):
            member_a,member_b = team[i],team[j]
            total += arr[member_a][member_b] + arr[member_b][member_a]
    return total

combinations = itertools.combinations(nums, a // 2)
for comb in combinations:
    other_comb = tuple(i for i in nums if i not in comb)
    # 조합은 챙겼고 비교 과정 필요
    res = abs(cal_diff(comb,arr) - cal_diff(other_comb,arr))
    min_diff = min(min_diff, res)

print(min_diff)


