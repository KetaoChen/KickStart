    import CodeJam.RoundC.Shortest_Meeting_Path;

    import java.io.*;
    import java.util.*;


    public class Solution implements Runnable
    {


        @Override
        public void run() {
            InputReader in = new InputReader(System.in);
            PrintWriter w = new PrintWriter(System.out);
            int t = (int) in.nextLong();
            for (int i = 1; i <= t; i++) {
                int N = (int) in.nextLong();
                int D = (int) in.nextLong();
                Long[] arr = new Long[N];
                for (int j = 0; j < N; j++) {
                    arr[j] = in.nextLong();
                }
                w.println("Case #" + i + ": " + getRes(D, arr));
            }
            w.flush();
            w.close();
        }


        private static int getRes(int D, Long[] arr) {
            Map<Long, Integer> map = new HashMap<>();
            long max = 0, min = Long.MAX_VALUE, count = 0;
            for (long num : arr) {
                max = Math.max(max, num);
                map.put(num, map.getOrDefault(num, 0) + 1);
                count = Math.max(count, map.get(num));
                if (map.get(num) >= 2) {
                    min = Math.min(min, num);
                }
            }

            if (D == 2) {
                for (long num : arr) {
                    if (map.get(num) >= 2) {
                        return 0;
                    }
                }
                return 1;
            }
            else {
                for (long key : arr) {
                    if (map.get(key) >= 3) {
                        return 0;
                    }
                    if (map.containsKey(key * 2)) {
                        return 1;
                    }
                    if (max > min) {
                        return 1;
                    }
                }
                return 2;
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
            new Thread(null, new Shortest_Meeting_Path(),"Main",1<<27).start();
        }

    }
