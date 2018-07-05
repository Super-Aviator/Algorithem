package unit3.practice;

public class T1 {
    public static void printLots(int[] L, int[] P) {
        for(int i=0;i<P.length;i++)
            System.out.println(L[P[i]]);
    }

    public static void main(String[] args) {
        printLots(new int[]{1,3,4,5,6,34,34,234,43,345,2,3}, new int[]{3,5,8,10});
    }
}
