a = '0'+input()
b = '1'+input()
lcs = [[""] * len(b) for _ in range(len(a))]

for i in range(len(a)):
    for j in range(len(b)):

        if a[i] == b[j]:
            lcs[i][j] = lcs[i-1][j-1]+a[i]
        else:
            if len(lcs[i-1][j]) > len(lcs[i][j-1]):
                lcs[i][j] = lcs[i-1][j]
            else:
                lcs[i][j] = lcs[i][j-1]

max_val = lcs[-1][-1]

print(len(max_val))
print(max_val)

    c  a  p  c  a  k

a   ''  (a,ca)=>a (a,cap)=>a
c    (ac,ca)=>c or a (ac,cap) => a or c
a    (aca,ca) => ca (aca,cap) max(ca,a or c)
y
K
p
