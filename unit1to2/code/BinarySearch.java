package unit1to2.code;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch1(int[] a,int dest){//二分查找的递归例子(驱动程序)
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        return binarySearch1(a,0,a.length-1,dest);
    }

    public static int binarySearch1(int[] a,int lo,int hi,int dest){
        if(lo>hi)   return -1;
        int mid=(lo+hi)/2;
        if(a[mid]==dest)    return mid;
        if(a[mid]>dest) return binarySearch1(a,lo,mid-1,dest);
        if(a[mid]<dest) return binarySearch1(a,mid+1,hi,dest);
        return -1;
    }

    public static int binarySearch2(int[] a,int dest){//二分查找的非递归例子
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        int lo=0,hi=a.length-1;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(a[mid]==dest)    return mid;
            if(a[mid]>dest) hi=mid-1;
            else if(a[mid]<dest) lo=mid+1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch1(new int[]{12,3,4,5,6,76,4,34,2,23},2));
        System.out.println(binarySearch2(new int[]{12,3,4,5,6,76,4,34,2,23},2));
    }
}
