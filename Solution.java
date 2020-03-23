import java.io.*;
import java.util.InputMismatchException;


public class Solution implements Runnable
{


    static class TrieNode {
        TrieNode[] children;
        int count;

        public TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }


    public static void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'A'] == null) {
                cur.children[c - 'A'] = new TrieNode();
            }
            cur = cur.children[c - 'A'];
            cur.count++;
        }
    }

    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String s = in.nextLine();
            String[] ss = s.split(" ");
            int n = Integer.parseInt(ss[0]);
            int K = Integer.parseInt(ss[1]);
            String[] arr = new String[n];

            for (int j = 0; j < n; j++) {
                arr[j] = in.nextLine();
            }
            getRes(i, n, K, arr, w);
        }
        w.flush();
        w.close();
    }

    private void getRes(int t, int n, int K, String[] arr, PrintWriter w) {
        // K = n / K;
        TrieNode root = new TrieNode();
        for (String s : arr) {
            insert(root, s);
        }

        int[] res = postOrder(root, 0, K);

        w.println("Case #" + t + ": " + res[0]);
    }

    // res[0] len add to the res, res[1] used nodes
    private static int[] postOrder(TrieNode root, int level, int K) {
        int[] res = new int[2];
        int used = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                // System.out.println(i);
                int[] add = postOrder(root.children[i], level + 1, K);
                res[0] += add[0];
                used += add[1];
            }
        }
        int cur = root.count - used;
        res[0] += cur / K * level;
        res[1] += used + cur / K * K;
        // System.out.println(level + " " + res[0] + " " + res[1]);
        return res;
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
        new Thread(null, new Solution(),"Main",1<<27).start();
    }

}
