import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] alpha = new boolean[26];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            String[] words = s.split(" ");
            decisionShorten(alpha, words);
            String result = Arrays.stream(words).collect(Collectors.joining(" "));
            bw.write(result);
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void decisionShorten(boolean[] alpha, String[] words) {
        for (int j = 0; j < words.length; j++) {
            int idx = Character.toUpperCase(words[j].charAt(0)) - 'A';
            if (!alpha[idx]) {
                alpha[idx] = true;
                StringBuilder sb = new StringBuilder(words[j]);
                String result = String.format("[%s]%s", sb.substring(0, 1), sb.substring(1));
                words[j] = result;
                return;
            }
        }
        for (int j = 0; j < words.length; j++) {
            char[] arr = words[j].toCharArray();
            for (int k = 1; k < arr.length; k++) {
                int idx = Character.toUpperCase(arr[k]) - 'A';
                if (!alpha[idx]) {
                    alpha[idx] = true;
                    StringBuilder sb = new StringBuilder(words[j]);
                    String result = String.format("%s[%s]%s", sb.substring(0, k), sb.substring(k, k + 1), sb.substring(k + 1));
                    words[j] = result;
                    return;
                }
            }
        }
    }
}