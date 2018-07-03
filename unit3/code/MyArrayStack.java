package unit3.code;

import java.util.NoSuchElementException;

@SuppressWarnings("unused")
public class MyArrayStack<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private E[] items;
    private int top=-1;

    @SuppressWarnings("unchecked")
    public MyArrayStack() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayStack(int initCapacity) {
        items = (E[]) new Object[initCapacity];
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public boolean isFull(){
        return top==items.length-1;
    }

    public void push(E item){
        if(isFull())
            throw new IllegalStateException("Stack overflow");
        items[++top]=item;
    }

    public E pop(){
        if(isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return items[top--];
    }

    public E peek(){
        if(isEmpty())
            return null;
        return items[top];
    }

    public static void main(String[] args) {
        MyArrayStack<Integer> stack=new MyArrayStack<>(20);
        for(int i=0;i<20;i++)
            stack.push(i);

        while(!stack.isEmpty()){
            System.out.print(stack.peek()+":");
            System.out.println(stack.pop());
        }

        while(!stack.isFull())
            stack.push(1);

        stack.push(10);
        //System.out.println();
    }
}
