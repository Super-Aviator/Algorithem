package unit1to2.code;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        //System.out.println(Math.round(12.56));
        /*System.out.println(Integer.parseInt("064",8));
        System.out.println(null==null);
        int[] a=new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(Arrays.copyOf(a,10)));
        */

        int[] items=new int[6];
        for(int i=0;i<5;i++)
            items[i]=i;
        int i=1;
        System.arraycopy(items,i,items,i+1,5-i);
        //int[] b=Arrays.copyOf(a,2);
        System.out.println(Arrays.toString(items));
    }
}
