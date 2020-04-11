package CodeJam.Round1A;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;


public class SquareDance implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int R = in.nextInt();
            int C = in.nextInt();
            int[][] arr = new int[R][C];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    arr[j][k] = in.nextInt();
                }
            }
            getRes(R, C, arr, w, i);
        }
        w.flush();
        w.close();
    }

    private static void getRes(int r, int c, int[][] arr, PrintWriter w, int t) {
        // direction: 0: up, 1: right, 2: down, 3: left
        int[][][] next = new int[4][r][c];
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (d == 0) next[d][i][j] = i - 1;
                    else if (d == 1) next[d][i][j] = j + 1;
                    else if (d == 2) next[d][i][j] = i + 1;
                    else next[d][i][j] = j - 1;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[r][c];
        int round = 1;

        long res = 0, all = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                all += arr[i][j];
                int sum = 0, count = 0;
                for (int d = 0; d < 4; d++) {
                    if (d == 0 && next[d][i][j] < 0 || d == 1 && next[d][i][j] >= c || d == 2 && next[d][i][j] >= r || d == 3 && next[d][i][j] < 0) {
                        continue;
                    }
                    count++;
                    sum += d == 0 || d == 2 ? arr[next[d][i][j]][j] : arr[i][next[d][i][j]];
                }
                if (arr[i][j] * count < 1.0 * sum) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = round;
                }
            }
        }
        res = all;

        while (!q.isEmpty()) {
            int s = q.size();
            // remove the nodes in the queue.
            for (int k = 0; k < s; k++) {
                int[] cur = q.poll();
                int i = cur[0], j = cur[1];
                all -= arr[i][j];
                arr[i][j] = 0;
                if (next[0][i][j] >= 0) {
                    next[2][next[0][i][j]][j] = next[2][i][j];
                    if (visited[next[0][i][j]][j] != round) {
                        q.offer(new int[]{next[0][i][j], j});
                        visited[next[0][i][j]][j] = round;
                    }
                }
                if (next[1][i][j] < c) {
                    next[3][i][next[1][i][j]] = next[3][i][j];
                    if (visited[i][next[1][i][j]] != round) {
                        q.offer(new int[]{i, next[1][i][j]});
                        visited[i][next[1][i][j]] = round;
                    }
                }
                if (next[2][i][j] < r) {
                    next[0][next[2][i][j]][j] = next[0][i][j];
                    if (visited[next[2][i][j]][j] != round) {
                        q.offer(new int[]{next[2][i][j], j});
                        visited[next[2][i][j]][j] = round;
                    }
                }
                if (next[3][i][j] >= 0) {
                    next[1][i][next[3][i][j]] = next[1][i][j];
                    if (visited[i][next[3][i][j]] != round) {
                        q.offer(new int[]{i, next[3][i][j]});
                        visited[i][next[3][i][j]] = round;
                    }
                }
            }

            res += all;
            s = q.size();
            round++;

            for (int k = 0; k < s; k++) {
                    int[] cur = q.poll();
                    int i = cur[0], j = cur[1], sum = 0, count = 0;
                    for (int d = 0; d < 4; d++) {
                        if (d == 0 && next[d][i][j] < 0 || d == 1 && next[d][i][j] >= c || d == 2 && next[d][i][j] >= r || d == 3 && next[d][i][j] < 0) {
                            continue;
                        }
                        count++;
                        sum += d == 0 || d == 2 ? arr[next[d][i][j]][j] : arr[i][next[d][i][j]];
                    }
                    if (arr[i][j] * count < 1.0 * sum) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = round;
                }
            }
        }


        w.println("Case #" + t + ": " + res);
    }

    static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars==-1)
                throw new InputMismatchException();

            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                }
                catch (IOException e)
                {
                    throw new InputMismatchException();
                }

                if(numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
        public int nextInt()
        {
            int c = read();

            while(isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-')
            {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do
            {
                if(c<'0'||c>'9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

    public static void main(String args[]) throws Exception
    {
        new Thread(null, new SquareDance(),"Main",1<<27).start();
    }

}
