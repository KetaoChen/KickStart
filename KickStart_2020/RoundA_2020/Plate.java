package KickStart_2020.RoundA_2020;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Plate implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int K = in.nextInt();
            int p = in.nextInt();
            int[][] arr = new int[n][K];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < K; k++) {
                    arr[j][k] = in.nextInt();
                }
            }
            getRes(i, n, K, p, arr, w);
        }
        w.flush();
        w.close();
    }

    private void getRes(int t, int n, int K, int P,  int[][] arr, PrintWriter w) {
        int[][] dp = new int[n + 1][P + 1];
        // int[][] prefix = new int[n][K + 1];

        for (int i = 1; i <= n ;i++) {
            for (int p = 1; p <= P; p++) {
                int sum = 0;
                for (int k = 0; k < Math.min(p, K); k++) {
                    sum += arr[i - 1][k];
                    dp[i][p] = Math.max(dp[i][p], Math.max(dp[i - 1][p], dp[i - 1][p - k - 1] + sum));
                }
            }
        }
        //        for (int i = 0; i <= n; i++) {
        //            for (int p = 0; p <= P; p++) {
        //                System.out.print(dp[i][p] + " ");
        //            }
        //            System.out.println();
        //        }

        w.println("Case #" + t + ": " + dp[n][P]);
    }

    // the base is n. The prime mod is mod.
//    final static int p =(int) (1e9 + 7);
//    public static long[] getInvArray(int n) {
//        long[] inv = new long[n + 1];
//        inv[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            inv[i] = ((p - p / i) * inv[p % i] % p + p) % p;
//        }
//        return inv;
//    }


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
        new Thread(null, new Plate(),"Main",1<<27).start();
    }

}
