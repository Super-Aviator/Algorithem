package unit1to2.practice;

public class T6 {
    public static void permute(String str) {
        permute(str.toCharArray(), 0, str.length() - 1);
    }

    public static void permute(char[] str, int lo, int hi) {
        if (hi == lo) {
            System.out.println(String.valueOf(str));
        } else {
            for (int i = lo; i <= hi; i++) {
                swap(str, lo, i);
                permute(str, lo + 1, hi);
                swap(str, lo, i);
            }
        }
    }

    public static void swap(char[] str, int lo, int hi) {
        char temp = str[lo];
        str[lo] = str[hi];
        str[hi] = temp;
    }

    public static void main(String[] args) {
        permute("ab");
    }
}
