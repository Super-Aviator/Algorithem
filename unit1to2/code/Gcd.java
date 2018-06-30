package unit1to2.code;

/**
 * 计算最大公因子（greatest common divisor）的欧几里得算法！
 * 算法假设m>=n，如果n<m，则第一次循环将他们交换，例如a%b=c,a=b,b=c.
 * 当a<b时可以将他们交换
 */
public class Gcd {
    public static int gcd(int m,int n){
        int remain=0;
        while(n!=0){
            remain=m%n;
            m=n;
            n=remain;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(1989,1590));
    }
}
