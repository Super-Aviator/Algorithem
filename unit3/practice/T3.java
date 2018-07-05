package unit3.practice;

import unit3.code.MyLinkedList;

public class T3<AnyType> extends MyLinkedList<AnyType> {
    public boolean contains(AnyType item) {
        for (AnyType i : this)
            if (i.equals(item))
                return true;
        return false;
    }
}
