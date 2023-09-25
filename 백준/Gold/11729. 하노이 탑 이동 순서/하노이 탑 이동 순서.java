import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        bw.write(String.format("%d\n", (int) Math.pow(2, n) - 1));
        hanoii(n, 1, 2, 3);

        br.close();
        bw.close();
    }

    static void hanoii(int n, int st, int tmp, int ed) throws IOException {
        if (n <= 0) {
            return;
        }
        hanoii(n - 1, st, ed, tmp);
        bw.write(String.format("%d %d\n", st, ed));
        hanoii(n - 1, tmp, st, ed);
    }
}