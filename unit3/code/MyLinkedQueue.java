package unit3.code;

import java.util.NoSuchElementException;

@SuppressWarnings("unused")
public class MyLinkedQueue<AnyType> {
    private int currentSize;
    private Node<AnyType> front,rear;

    private class Node<AnyType>{
        public Node<AnyType> next;
        public AnyType item;

        public Node(AnyType item, Node<AnyType> next){
            this.next=next;
            this.item=item;
        }
    }

    public MyLinkedQueue(){
        front=rear=new Node<>(null,null);
    }

    public void enqueue(AnyType item){
        rear.next=new Node<>(item,null);
        rear=rear.next;
        currentSize++;
    }

    public AnyType dequeue(){
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty");
        front=front.next;
        return front.item;
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public static void main(String[] args) {
        MyLinkedQueue<Integer> que=new MyLinkedQueue<>();
        for(int i=0;i<20;i++)
            que.enqueue(i);

        while(!que.isEmpty())
            System.out.print(que.dequeue()+" ");

        que.enqueue(1000);
        System.out.println(que.dequeue());

        System.out.println(que.dequeue());
    }
}
