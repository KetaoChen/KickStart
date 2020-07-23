package KickStart_2020.RoundD_2020;
import java.io.*;
import java.util.*;

public class B_Alien_Piano_DP implements Runnable
{


    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            n = in.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            getRes(t + 1);
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int T, n;
    static int[] arr;

    static void getRes(int t) {
        int res = n;
//        for (int i = 0; i < 4; i++) {
//            res = Math.min(res, helper(i));
//        }
        int[][] dp = new int[n][4];
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                dp[i][0] = dp[i - 1][3] + 1;
                dp[i][1] = dp[i - 1][0];
                dp[i][2] = Math.min(dp[i][1], dp[i - 1][1]);
                dp[i][3] = Math.min(dp[i][2], dp[i - 1][2]);
            }
            else if (arr[i] == arr[i - 1]){
                for (int k = 0; k < 4; k++) {
                    dp[i][k] = dp[i - 1][k];
                }
            }
            else {
                dp[i][3] = dp[i - 1][0] + 1;
                dp[i][2] = dp[i - 1][3];
                dp[i][1] = Math.min(dp[i][2], dp[i - 1][2]);
                dp[i][0] = Math.min(dp[i][1], dp[i - 1][1]);
            }
        }
        for (int i = 0; i < 4; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        w.println("Case #" +  t +  ": " + res);
    }

//    static int helper(int start) {
//        int cur = start;
//        int res = 0;
//        for (int i = 1; i < n; i++) {
//            if (arr[i] == arr[i - 1]) continue;
//            if (arr[i] > arr[i - 1]) cur++;
//            else cur--;
//            if (cur >= 4 || cur < 0) {
//                res++;
//                cur = (cur + 4) % 4;
//            }
//        }
//        return res;
//    }
//
//    static int min(int[] nums, int i, int j) {
//        int res = nums[i];
//        for (int k = i; k <= j; k++) {
//            res = Math.min(res, nums[k]);
//        }
//        return res;
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
        new Thread(null, new B_Alien_Piano_DP(),"Main",1<<27).start();
    }

}

