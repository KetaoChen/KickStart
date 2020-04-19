package RoundB_2020;

import java.io.*;
import java.util.InputMismatchException;


public class Wandering_Robot_Dp_coordinate implements Runnable
{


    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int col = in.nextInt();
            int row = in.nextInt();
            int l = in.nextInt();
            int u = in.nextInt();
            int r = in.nextInt();
            int d = in.nextInt();
            getRes(row, col, l, u, r, d, w, i);
        }
        w.flush();
        w.close();
    }


    private static void getRes(int row, int col, int l, int u, int r, int d, PrintWriter w, int t) {
        if (check(1, 1, l, u, r, d) || row == 1 || col == 1) {
            w.println("Case #" + t + ": " + 0.0);
            return;
        }


        double fall = 0;
        double[][] dp = new double[row + 1][col + 1];
        dp[1][1] = 1;


        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (i == 1 && j == 1) continue;

                if (i == 1) {
                    if (check(i, j, l, u, r, d)) {
                        fall +=  dp[i][j - 1] / 2;
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i][j - 1] / 2;
                }
                else if (j == 1) {
                    if (check(i, j, l, u, r, d)) {
                        fall += dp[i - 1][j] / 2;
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i - 1][j] / 2;
                }
                else if (i == row && j == col) {
                    if (check(i, j, l, u, r, d)) {
                        fall += dp[i - 1][j] + dp[i][j - 1];
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                else if (i == row) {
                    if (check(i, j, l, u, r, d)) {
                        fall += dp[i - 1][j] / 2 + dp[i][j - 1];
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i - 1][j] / 2 + dp[i][j - 1];
                }
                else if (j == col) {
                    if (check(i, j, l, u, r, d)) {
                        fall += dp[i][j - 1] / 2 + dp[i - 1][j];
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i][j - 1] / 2 + dp[i][j - 1];
                }
                else {
                    if (check(i, j, l, u, r, d)) {
                        fall += dp[i - 1][j] / 2 + dp[i][j - 1] / 2;
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = dp[i - 1][j] / 2 + dp[i][j - 1] / 2;
                }
            }
        }

        w.println("Case #" + t + ": " + (1 - fall));
    }

    private static boolean check(int i, int j, int l, int u, int r, int d) {
        return i >= u && i <= d && j >= l && j <= r;
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
        new Thread(null, new Wandering_Robot_Dp_coordinate(),"Main",1<<27).start();
    }

}
