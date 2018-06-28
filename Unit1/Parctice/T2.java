package Unit1.Parctice;

import java.util.HashMap;
import java.util.Map;

public class T2 {
    public static HashMap<Integer, String> map = new HashMap<>();
    private static boolean[][] bool;
    private static int sta;
    private static int[][] dir = {{0, 1, 0, -1,-1,-1,1,1}, {-1, 0, 1, 0,-1,1,1,-1}};

    public static boolean isOutOfBound(char[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix.length) return true;
        if (y < 0 || y >= matrix[0].length) return true;
        return false;
    }

    public static void findWord(char[][] matrix, String word) {
        bool = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    map.clear();
                    map.put(sta++, i + ":" + j);
                    //System.out.println(map);
                    findWord(matrix, word, 1, i, j);
                }

            }
        }
    }

    public static void findWord(char[][] matrix, String word, int index, int x, int y) {
        if (index >= word.length()) {
            for (int i : map.keySet())
                System.out.print(map.get(i) + " ");
            System.out.println();
            return;
        }

        for (int i = 0; i < 8; i++) {
            int x1 = x + dir[0][i];
            int y1 = y + dir[1][i];
            if (isOutOfBound(matrix, x1, y1) || bool[x1][y1]) continue;

            if (matrix[x1][y1] == word.charAt(index)) {
                bool[x1][y1] = true;
                map.put(sta++, x1 + ":" + y1);
                findWord(matrix, word, index + 1, x1, y1);
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
        }, "that");
    }

}
