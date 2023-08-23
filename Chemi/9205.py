from collections import deque

def bfs(house, conv, penta):
    q = deque()
    q.append(house)
    visited = set()
    visited.add(house)

    while q:
        x, y = q.popleft()

        if abs(penta[0] - x) + abs(penta[1] - y) <= 1000:
            return "happy"

        for i in range(len(conv)):
            conv_x, conv_y = conv[i][0], conv[i][1]
            if (conv_x, conv_y) not in visited and abs(conv_x - x) + abs(conv_y - y) <= 1000:
                q.append((conv_x, conv_y))
                visited.add((conv_x, conv_y))

    return "sad"

def main():
    t = int(input())
    ans = []

    for _ in range(t):
        n = int(input())
        house = tuple(map(int, input().split()))
        conv = []
        for _ in range(n):
            conv.append(tuple(map(int, input().split())))
        penta = tuple(map(int, input().split()))
        ans.append(bfs(house, conv, penta))

    print('\n'.join(ans))

if __name__ == "__main__":
    main()
