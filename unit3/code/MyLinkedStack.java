package unit3.code;

import java.util.NoSuchElementException;

public class MyLinkedStack<E> {
    private class Node<E> {
        public Node<E> next;
        public E item;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node() {
        }

        public boolean isEnd() {
            return next == null && item == null;
        }
    }

    private Node<E> top = new Node<>();//末端哨兵

    public void push(E e) {
        top = new Node<>(e, top);
    }

    public boolean isEmpty() {
        return top.isEnd();
    }

    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        E result = top.item;
        top = top.next;
        return result;
    }

    public E peek(){
        return top.item;
    }

    public static void main(String[] args) {
        MyLinkedStack<Integer> stack=new MyLinkedStack<>();
        for(int i=0;i<20;i++)
            stack.push(i);
        while(!stack.isEmpty()){
            System.out.print(stack.peek()+":");
            System.out.println(stack.pop());
        }
    }
}
