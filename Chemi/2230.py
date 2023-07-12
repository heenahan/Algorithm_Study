n,m = map(int,input().split())
l = []

for i in range(n):
    l.append(int(input()))
l.sort() # nlogn : 병합정렬
min_val = l[-1] - l[0]
end = 0

for start in range(len(l)):
    while(end <= len(l)-1): 
        diff = l[end]-l[start]
        if diff >= m:
            min_val = min(diff,min_val)
            break
        else:
            end += 1

print(min_val)
print(1e9)