package unit3.code;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList的简单实现
 *
 * @param <AnyType>
 */

@SuppressWarnings("unused")
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private Node<AnyType> beginNode;
    private Node<AnyType> endNode;
    private int thisSize;
    private int modCount;

    public MyLinkedList() {
        doClear();
    }

    private class Node<AnyType> {//私有内部类结点
        public AnyType item;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        /**
         * 这里我错误的将this.next = next;写成了this.next = prev;
         * 导致程序出现了堆栈溢出的错误。自己调试了几个小时才找到原因
         * 细心一点，坤哥。拜托了
         * @param item
         * @param prev
         * @param next
         */

        public Node(AnyType item, Node<AnyType> prev, Node<AnyType> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public void clear() {
        doClear();
    }

    public void doClear() {//双向链表初始化
        beginNode = new Node<>(null, null, null);
        endNode = new Node<>(null, beginNode, null);
        beginNode.next = endNode;

        thisSize = 0;
        modCount = 0;
    }

    public int size() {
        return thisSize;
    }

    public boolean isEmpty() {
        return thisSize == 0;
    }

    public AnyType get(int index) {
        return getNode(index).item;
    }

    public AnyType set(int index, AnyType item) {
        Node<AnyType> temp = getNode(index);
        AnyType result = temp.item;
        temp.item = item;
        return result;
    }

    public boolean add(AnyType item) {//默认在末尾添加元素
        add(size(), item);
        return true;
    }

    public void add(int index, AnyType item) {
        Node<AnyType> temp = getNode(index);
        temp.prev.next = temp.prev=new Node<>(item, temp.prev, temp);
        /**
         * 这里等同于:
         * Node<AnyType> newNode=new Node<>(item, temp.prev, temp);
         * temp.prev.next = newNode;
         * temp.prev= newNode;
         * 奇淫技巧之一
         */
        modCount++;
        thisSize++;
    }

    public void remove(int index) {
        remove(getNode(index));
    }

    private void remove(Node<AnyType> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        modCount++;
        thisSize--;
    }

    private Node<AnyType> getNode(int index) {//根据下标得到结点
        checkIndex(index);
        Node<AnyType> temp;
        if (index < size() / 2) {//判断index是位于链表的前半部分还是后半部分
            temp = beginNode.next;
            for (int i = 0; i < index; i++)
                temp = temp.next;
            return temp;
        } else {
            temp = endNode;//从后向前遍历需要多执行一次temp=temp.prev,因为从前遍历是从0开始的
            for (int i = size(); i > index; i--)
                temp = temp.prev;
            return temp;
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new Iterator<AnyType>() {
            private int expectedModCount = modCount;
            private Node<AnyType> current = beginNode.next;
            private boolean canRemove = false;//用于判断remove是否在next之后调用

            @Override
            public boolean hasNext() {
                return current != endNode;
            }

            @Override
            public AnyType next() {
                if (modCount != expectedModCount)//首先检测是否修改过。
                    throw new ConcurrentModificationException();

                if (!hasNext())//在检查是否还有元素。
                    throw new NoSuchElementException();

                AnyType temp = current.item;
                current = current.next;
                canRemove = true;
                return temp;
            }

            @Override
            public void remove() {

                if (modCount != expectedModCount)//首先检测是否修改过。
                    throw new ConcurrentModificationException();

                if (!canRemove) {
                    throw new IllegalStateException("Please use next() before");
                }

                MyLinkedList.this.remove(current.prev);
                expectedModCount++;
                canRemove = false;
            }
        };
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException(index);
    }

    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        Iterator<AnyType> ite = iterator();
        sb.append("[ ");
        int i=0;
        while (++i<13) {
            sb.append(ite.next());
            if (!ite.hasNext())
                return sb.append(" ]").toString();
            sb.append(" ");
        }
        return sb.toString();
}

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < 10; i++)
            list.add(i);

        System.out.println("list.size() " + list.size() + ":" + list.isEmpty());
        System.out.println("list.get(0) " + list.get(0));
        System.out.println("list.get(1) " + list.get(1));
        System.out.println("list.set(2,10000) " + list.set(2,10000));
        System.out.println("list.get(3) " + list.get(3));

        System.out.println(list + " size() " + list.size());
    }
}
