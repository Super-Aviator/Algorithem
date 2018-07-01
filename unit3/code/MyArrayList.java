package unit3.code;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private int size;
    private AnyType[] items;
    private static final int DEFAULT_CAPACITY=10;

    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity){//自定义数组容量
        items=(AnyType[]) new Object[initialCapacity];
    }

    public int capacity(){
        return items.length;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newSize){//扩充或缩减容量
        if(newSize<size) //缩减后的数组小于size(不包含等于)，有丢失数据的危险，不予操作。
            return;

        AnyType[] old=items;
        items=Arrays.copyOf(old,newSize);
    }

    public void trimToSize(){
        ensureCapacity(size);
    }

    public AnyType get(int index){
        checkIndex(index);

        return items[index];
    }

    public AnyType set(int index,AnyType val){
        checkIndex(index);

        AnyType result=items[index];
        items[index]=val;
        return result;
    }

    public void add(AnyType val){
        add(size,val);
    }

    public void add(int index, AnyType val){
        checkIndex(index);

        if(size+1>=capacity())
            ensureCapacity(2*size+1);

        System.arraycopy(items,index,items,index+1,size-index);//使用System.arraycopy进行移动数组
        items[index]=val;
        size++;
    }

    public boolean remove(AnyType val){
        Iterator<AnyType> ite=iterator();
        if(val==null){
            while(ite.hasNext()){
                if(ite.next()==null){
                    ite.remove();
                    return true;
                }
            }
        }else{
            while(ite.hasNext()){
                if(ite.next().equals(val)){
                    ite.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(int index){
        checkIndex(index);

        System.arraycopy(items,index+1,items,index,size-index-1);
        items[--size]=null;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new Iterator<AnyType>() {
            private int current=0;
            @Override
            public boolean hasNext() {
                return current!=size;
            }

            @Override
            public AnyType next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                return items[current++];
            }

            @Override
            public void remove(){
                MyArrayList.this.remove(--current);
            }
        };
    }

    private void checkIndex(int index){
        if(index<0||index>=capacity())
            throw new ArrayIndexOutOfBoundsException(index+" Out Of Bounds!!!");
    }

    public String toString(){
        Iterator<AnyType> ite=iterator();
        if(!ite.hasNext())   return "[]";
        StringBuilder sb=new StringBuilder();
        sb.append("[");

        while(true){
            sb.append(ite.next());
            if(!ite.hasNext())
                return sb.append("]").toString();
            sb.append(" , ");
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> m=new MyArrayList<>();
        System.out.println("m.capacity()"+m.capacity());
        m.ensureCapacity(5);
        System.out.println("m.capacity()"+m.capacity());

        for(int i=0;i<100;i++)
            m.add(i);

        System.out.println("m.size()"+m.size());
        System.out.println("m.capacity()"+m.capacity());
        System.out.println(m);

        System.out.println();

        m.add(1,100);
        System.out.println(m+" size:"+m.size);
        m.add(20,100);
        System.out.println(m+" size:"+m.size);
        m.remove(1);
        System.out.println(m+" size:"+m.size);
        m.remove(new Integer(100));
        System.out.println(m+" size:"+m.size);
        m.remove(new Integer(3));
        System.out.println(m);


        System.out.println();
        System.out.println(m);

        System.out.println("m.get(1):"+m.get(1));
        m.set(1,10000);
        System.out.println("m.set(1,10000):"+m);

        System.out.println("\n"+"foreach");
        for(int i:m)
            System.out.print(i+" ");

        System.out.println("\n"+"while and remove");
        Iterator ite=m.iterator();
        while(ite.hasNext()){
            System.out.print(ite.next()+" ");
            ite.remove();
        }

        System.out.println("\n"+m);
    }
}
