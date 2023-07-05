import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        // 총회 시작, 총회 끝, 스트리밍 끌
        int[] times = Arrays.stream(input)
                            .mapToInt(t -> T.changeToMinutes(t.split(":")))
                            .toArray();

        // 기록은 시간 순
        String record = "";
        Set<String> participation = new HashSet<>();
        int count = 0;
        while ((record = br.readLine()) != null) {
            String timeOfString = record.split(" ")[0];
            String name = record.split(" ")[1];
            int time = T.changeToMinutes(timeOfString.split(":"));
            if (time <= times[0]) { // 총회 시작 시간 전에 채팅 남겼으면 들어온 것
                participation.add(name);
            } else if (time >= times[1] && time <= times[2]) { // 총회 끝 이후 스트리밍 끝 전에 채팅 남겼으면 퇴장
                if (participation.contains(name)) {
                    count++;
                    participation.remove(name); // 지움
                }
            }
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

    private int changeToMinutes(String[] time) {
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        return hour * 60 + min;
    }

}