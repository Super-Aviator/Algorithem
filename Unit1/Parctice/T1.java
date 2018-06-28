package Unit1.Parctice;

import java.util.Arrays;

public class T1 {
    public static int select(int[] nums, int k) {//从nums数组中找到大小为第k个数
        if (k > nums.length) return 99999;
        int[] a = new int[k];//用于缓存大于等于k的数
        for (int i = 0; i < k; i++)
            a[i] = nums[i];
        Arrays.sort(a);

        for (int i = k; i < nums.length; i++) {
            int t = nums[i], j;

            for (j = 0; j < k; j++)
                if (t < a[j]) a[j] = t;//找到较小的数直接替换掉大的数，保证数组中永远保存较小的k个数
            if (j == k) continue;
        }

        System.out.println(Arrays.toString(a));
        return a[k - 1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{10, 23, 12, 3, 4, 5, 634, 234};
        System.out.println(select(a, 1));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

/*

package Unit1.Parctice;

        import java.util.HashMap;
        import java.util.Map;

public class T2 {
    public static int[] xdir;
    public static int[] ydir;
    public static int in;
    private static boolean[][] bool;
    private static int[][] dir = {{0, 1, 0, -1}, {-1, 0, 1, 0}};

    public static boolean isOutOfBound(char[][] matrix,int x,int y){
        if(x<0||x>=matrix.length) return true;
        if(y<0||y>=matrix[0].length) return true;
        return false;
    }

    public static void findWord(char[][] matrix, String word) {
        bool = new boolean[matrix.length][matrix[0].length];
        xdir=new int[word.length()];
        ydir=new int[word.length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0;  j< matrix[0].length; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    xdir[in]=i;
                    xdir[in++]=j;
                    findWord(matrix, word, 1, i, j);
                }

            }
        }
    }

    public static void findWord(char[][] matrix, String word, int index, int x, int y) {
        if (index == word.length()) {
            for(int i=0;i<in;i++)
                System.out.print(xdir[i]+":"+ydir[i]+"  ");
            System.out.println();
            System.exit(0);
        }

        for (int i = 0; i < 4; i++) {
            int x1 = x + dir[0][i];
            int y1 = y + dir[1][i];
            if(isOutOfBound(matrix,x1,y1)||!bool[x1][y1])  continue;
            if (matrix[x1][y1] == word.charAt(index)) {
                bool[x1][y1] = true;
                xdir[in]=x1;
                xdir[in++]=y1;
                findWord(matrix, word, ++index, x1, y1);
                bool[x1][y1] = false;
            }

        }
    }

    public static void main(String[] args) {
        findWord(new char[][]{
                {'t', 'h', 'i', 's'},
                {'w', 'a', 't', 's'},
                {'o', 'a', 'h', 'g'},
                {'f', 'g', 'd', 't'}
        }, "two");
    }

}
*/

