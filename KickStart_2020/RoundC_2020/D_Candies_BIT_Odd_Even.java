package KickStart_2020.RoundC_2020;

import java.io.*;
import java.util.InputMismatchException;


public class D_Candies_BIT_Odd_Even implements Runnable
{


    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int q = Integer.parseInt(s[1]);
            int[] arr = new int[n];
            s = in.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(s[j]);
            }
            getRes(arr);

            long res = 0;
            for (int j = 0; j < q; j++) {
                s = in.nextLine().split(" ");
                char c = s[0].charAt(0);
                int left = Integer.parseInt(s[1]), right = Integer.parseInt(s[2]);
                if (c == 'Q') {
                    res += cal(left - 1, right - 1);
                }
                else {
                    update(left - 1, right);
                }
            }
            w.println("Case #" + i + ": " + res);
        }
        w.flush();
        w.close();
    }

    static long[] oddSum;
    static long[] evenSum;
    static long[] oddMulSum;
    static long[] evenMulSum;
    static long[] nums;


    private static void getRes(int[] arr) {
        nums = new long[arr.length];
        oddSum = new long[arr.length + 1];
        evenSum = new long[arr.length + 1];
        oddMulSum = new long[arr.length + 1];
        evenMulSum = new long[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            update(i, arr[i]);
        }
    }

    private static long cal(int left, int right) {
        long evenMul = calEvenMulRange(right + 1) - calEvenMulRange(left);
        long evenSum = calEvenSum(right + 1) - calEvenSum(left);

        long oddMul = calOddMulRange(right + 1) - calOddMulRange(left);
        long oddSum = calOddSum(right + 1) - calOddSum(left);

        long even = evenMul - evenSum * left;
        long odd = oddMul - oddSum * left;

        return left % 2 == 0 ? even - odd : odd - even;
    }

    private static long calOddMulRange(int index) {
        long res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += oddMulSum[i];
        }
        return res;
    }

    private static long calEvenMulRange(int index) {
        long res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += evenMulSum[i];
        }
        return res;
    }

    private static long calEvenSum(int index) {
        long res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += evenSum[i];
        }
        return res;
    }

    private static long calOddSum(int index) {
        long res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += oddSum[i];
        }
        return res;
    }

    private static void update(int i, int val) {
        long change = val - nums[i];
        nums[i] = val;
        if (i % 2 == 0) {
            for (int index = i + 1; index <= nums.length; index += lowbit(index)) {
                evenSum[index] += change;
                evenMulSum[index] += change * (i + 1);
            }
        }
        else {
            for (int index = i + 1; index <= nums.length; index += lowbit(index)) {
                oddSum[index] += change;
                oddMulSum[index] += change * (i + 1);
            }
        }

    }

    private static int lowbit(int x) {
        return x & -x;
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
        new Thread(null, new D_Candies_BIT_Odd_Even(),"Main",1<<27).start();
    }

}
