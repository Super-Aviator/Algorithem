package Unit1.Code;

import java.math.BigInteger;

/**
 * 求x的n次幂的高效算法
 */
public class Pow {
    public static long pow(long x, int n) {//可能存在溢出
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0)
            return pow(x * x, n / 2);
        else if (n % 2 != 0)
            return pow(x * x, n / 2) * x;
        return 0;
    }

    public static BigInteger pow(BigInteger x, int n) {//pow的BigInteger版本
        if (n == 0) return new BigInteger("0");
        if (n == 1) return x;
        if (n % 2 == 0)
            return pow(x.multiply(x), n / 2);
        else if (n % 2 != 0)
            return pow(x.multiply(x), n / 2).multiply(x);
        return new BigInteger("0");
    }

    public static void main(String[] args) {
        System.out.println(pow(2,64));
        System.out.println(pow(new BigInteger("2"),40));
    }
}
