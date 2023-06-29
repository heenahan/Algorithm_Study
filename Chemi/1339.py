n = int(input())

words = []
words_dict = {}

for i in range(n):
    words.append(input())

## 숫자를 안정해주는 법 생각하기 ABCDE, FGHIA = >  a : 10000 + 1, b : 1000, c = 100

for i in range(n):
    for j in range(len(words[i])):
        if words[i][j] in words_dict:
            words_dict[words[i][j]] += 10**(len(words[i])-j-1)
        else:
            words_dict[words[i][j]] = 10**(len(words[i])-j-1)

point = 9
ans  = 0

sorted_dict= dict(sorted(words_dict.items(),key=lambda x : x[1], reverse = True))
print(sorted_dict)

for value in sorted_dict.values():
    ans += point * value
    point -= 1
print(ans)