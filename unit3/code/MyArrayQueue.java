package unit3.code;

import java.util.NoSuchElementException;

public class MyArrayQueue<AnyType> {
    private AnyType[] queue;
    private int currentSize;
    private int front, rear;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyArrayQueue() {
        queue = (AnyType[]) new Object[DEFAULT_CAPACITY];
        front = rear = queue.length / 2;
    }

    @SuppressWarnings("unchecked")
    public MyArrayQueue(int initCapacity) {
        queue = (AnyType[]) new Object[initCapacity];
        front = rear = queue.length / 2;
    }

    public void enqueue(AnyType item) {
        if(isFull())
            throw new IllegalStateException("Queue is full");
        queue[(rear + 1) % queue.length] = item;
        rear = (rear + 1) % queue.length;
        currentSize++;
    }

    public AnyType dequeue() {
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty");
        AnyType result = queue[(front+1)%queue.length];
        front = (front + 1) % queue.length;
        currentSize--;
        return result;
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull(){
        return currentSize==queue.length;
    }

    public static void main(String[] args) {
        MyArrayQueue<Integer> que = new MyArrayQueue<>(20);
        for (int i = 0; i < 10; i++)
            que.enqueue(i);

        System.out.println(que.size());

        while (!que.isEmpty())
            System.out.print(que.dequeue() + " ");

        que.enqueue(1000);
        System.out.println("***"+que.dequeue() + " ");

    }
}
