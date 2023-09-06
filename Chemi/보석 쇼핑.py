def solution(gems):
    goal = len(set(gems))
    l,r = 0,0
    ans = [0,len(gems)-1]
    d = {}
    d[gems[0]] = 1
    
    while l < len(gems) and r < len(gems):
        if (len(d) == goal):
            if r - l < ans[1] - ans[0]:
                # 여기서 +1 해주면 안된다..미자믹 테케
                ans = [l,r]
            else:
                d[gems[l]] -= 1
                if d[gems[l]] == 0:
                    del d[gems[l]]
                l += 1
        else:
            r += 1
            # 예외를 앞에서 터뜨려
            if r == len(gems):
                break
            
            d[gems[r]] = d.get(gems[r],0) + 1
    
    return [ans[0]+1,ans[1]+1]

# DRRDDER
# DDEERR
