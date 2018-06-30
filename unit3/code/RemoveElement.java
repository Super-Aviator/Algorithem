package unit3.code;

import unit3.StopWatch;

import java.util.*;

public class RemoveElement {
    public static void removeElement(List<Integer> list) {
        Iterator<Integer> li=list.iterator();
        while(li.hasNext()){
            li.next();
            li.remove();
        }
    }

    public static void main(String[] args) {
        StopWatch sw=new StopWatch();
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<400000;i++)
            list.add(i);
        removeElement(list);
        System.out.println(sw.elapsedTime());

        LinkedList<Integer> li=new LinkedList<>();
        for(int i=0;i<400000;i++)
            li.add(i);
        sw=new StopWatch();
        removeElement(li);
        System.out.println(sw.elapsedTime());

        System.out.println(Arrays.toString(test(2)));
    }

    public static <T> T[] test(T t){
        T[] x=(T[])new Object[10];
        for(int i=0;i<10;i++)
            x[i]= t;
        return x;
    }
}
