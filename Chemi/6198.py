# n = int(input())
# forest = []
# stack = []
# ans = 0
# for i in range(n):
#     forest.append(int(input()))
# while forest:
#     a = forest.pop()
#     for i in range(1,len(stack)+1):
#         b = stack[len(stack)-i]
#         if b < a:
#             if forest and max(forest) <= a:
#                 stack.pop(len(stack)-i)
#             ans += 1
#         else:
#             break
#     stack.append(a)
# print(ans)


n = int(input())
 
arr = [int(input()) for i in range(n)]
stk = []
ans = 0
 
for i in range(n):
    while stk and stk[-1] <= arr[i]:
        stk.pop()
 
    stk.append(arr[i])
    ans += len(stk) -1
 
print(ans)