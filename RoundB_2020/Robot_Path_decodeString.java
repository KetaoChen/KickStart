package RoundB_2020;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;


public class Robot_Path_decodeString implements Runnable
{


    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String s = in.nextLine();
            getRes(s, w, i);
        }
        w.flush();
        w.close();
    }

    static int index;
    static int mod = (int) 1e9;
    private static void getRes(String s, PrintWriter w, int t) {
        index = 0;
        Map<Character, Long> map = helper(s);

        long south = (1 + map.getOrDefault('S', 0L) - map.getOrDefault('N', 0L) + mod) % mod;
        long east = (1 + map.getOrDefault('E', 0L) - map.getOrDefault('W', 0L) + mod) % mod;
        if (south == 0) south = mod;
        if (east == 0) east = mod;
        w.println("Case #" + t + ": " + east + " " + south);
    }

    private static Map<Character, Long> helper(String s) {
        Map<Character, Long> count = new HashMap<>();
        int times = 0;
        while (index < s.length()) {
            char c = s.charAt(index++);
            if (Character.isLetter(c)) {
                // System.out.println("find " + c);
                count.put(c, count.getOrDefault(c, (Long) 0L) + 1L);
            }
            else if (Character.isDigit(c)) {
                times = c - '0';
                index++;
                Map<Character, Long> next = helper(s);
                for (char ch : next.keySet()) {
                    count.put(ch, (count.getOrDefault(ch, 0L) + times * next.get(ch)) % mod);
                }
            }
            else if (c == ')') {
                return count;
            }
        }
        // System.out.println(count.size());
        return count;
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
        new Thread(null, new Robot_Path_decodeString(),"Main",1<<27).start();
    }

}
