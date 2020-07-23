package KickStart_2020.RoundD_2020;

import java.io.*;
import java.util.*;
public class C_Beauty_Of_Tree_InExclusive implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            n = in.nextInt();
            A = in.nextInt();
            B = in.nextInt();
            cntA = new long[n + 1];
            cntB = new long[n + 1];

            // initialize the parent nodes array in multiplication.
            p = new int[n + 1][20];
            p[1][0] = -1;
            for (int i = 2; i <= n; i++) {
                p[i][0] = in.nextInt();
            }
            for (int k = 1; k < 20; k++) {
                for (int i = 1; i <= n; i++) {
                    int fa = p[i][k - 1];
                    p[i][k] = fa == -1 ? -1 : p[fa][k - 1];
                }
            }
            getRes(t + 1);
        }

        w.flush();
        w.close();
    }

    static int getKthP(int node, int k) {
        for (int x = 0; x < 20; x++) {
            if ((k >> x & 1) == 1) {
                node = p[node][x];
                if (node == -1) return -1;
            }
        }
        return node;
    }

    static PrintWriter w;
    static int T, n, A, B;
    static int[][] p;
    static long[] cntA, cntB;

    static void getRes(int t) {
        double sum = 0;
        for (int i = n; i > 0; i--) {
            helper(i);
        }

        for (int i = 1; i <= n; i++) {
            sum += (cntA[i] + cntB[i]) * n - cntA[i] * cntB[i];
        }
        w.println("Case #" +  t +  ": " + sum / ((long) n * n));
    }

    static void helper(int node) {
        cntA[node]++;
        cntB[node]++;
        int pa = getKthP(node, A);
        if (pa != -1) {
            cntA[pa] += cntA[node];
        }
        int pb = getKthP(node, B);
        if (pb != -1) {
            cntB[pb] += cntB[node];
        }
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
        new Thread(null, new C_Beauty_Of_Tree_InExclusive(),"Main",1<<27).start();
    }

}
