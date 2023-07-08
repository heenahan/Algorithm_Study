# l = list(input().split())
# students = set()
# ans = 0

# def convertMinute(time):
#     hours,minutes = map(int, time.split(':'))
#     return hours * 60 + minutes

# univ = list(map(convertMinute,l))
# start_time = univ[0]
# end_time = univ[1]
# stream_time = univ[2]

# while True:
#     try:
#         a = input()
#         if not a:
#             break

#         key,name = a.split()
#         time = convertMinute(key)
        
#         if time <= start_time:
#             students.add(name)
#         elif end_time <= time <= stream_time:
#             if name in students:
#                 students.remove(name)
#                 ans += 1
#     except EOFError:
#         break

# print(ans)

import sys
input = sys.stdin.readline

l = list(input().split())
students = set()
ans = 0

def convertMinute(time):
    hours,minutes = map(int, time.split(':'))
    return hours * 60 + minutes

univ = list(map(convertMinute,l))
start_time = univ[0]
end_time = univ[1]
stream_time = univ[2]

while True:
    try:
        a = input().rstrip()
        if not a:
            break

        key,name = a.split()
        time = convertMinute(key)
        
        if time <= start_time:
            students.add(name)
        elif end_time <= time <= stream_time:
            if name in students: # 탐색이 여러번 이루어질 수 잇는 지점
                students.remove(name)
                ans += 1
    except EOFError:
        break

print(ans)