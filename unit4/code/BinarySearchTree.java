package unit4.code;

@SuppressWarnings("unused")
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;

    private class BinaryNode<AnyType> {
        AnyType item;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType item, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        BinaryNode(AnyType item) {
            this.item = item;
        }
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType item) {
        return contains(item, root);
    }

    private boolean contains(AnyType item, BinaryNode<AnyType> node) {
        if (node == null) return false;

        int compare = item.compareTo(node.item);

        if (compare < 0)
            return contains(item, node.left);

        if (compare > 0)
            return contains(item, node.right);

        return true;    //match
    }

    public AnyType findMin() throws UnderFlowException {
        if (isEmpty()) throw new UnderFlowException();
        return findMin(root).item;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
        /**
         * 查找最小值的递归实现
         */

        if (node.left == null) return node;
        return findMin(node.left);

        /**
         * 查找最小值的非递归实现
         */

        /*while (node.left != null) {
            node = node.left;
        }
        return node;*/
    }

    public AnyType findMax() throws UnderFlowException {
        if (isEmpty()) throw new UnderFlowException();
        return findMax(root).item;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
        /**
         * 查找最大值的递归实现
         */

        if (node.right == null) return node;
        return findMax(node.right);

        /**
         * 查找最大值的非递归实现
         */

        /*while (node.right != null) {
            node = node.right;
        }
        return node;*/
    }

    public void insert(AnyType item) {
        root = insert(item, root);
    }

    private BinaryNode<AnyType> insert(AnyType item, BinaryNode<AnyType> node) {
        /**
         * 插入的实现，自己凭着记忆写出来滴，和书上差不多。哟西。
         */
        if (node == null)
            return new BinaryNode<>(item);

        int compare = item.compareTo(node.item);
        if (compare < 0) node.left = insert(item, node.left);
        else if (compare > 0) node.right = insert(item, node.right);
        //如果item存在，就什么也不做。
        return node;
    }

    public void remove(AnyType item) {
        root = remove(item, root);
    }

    private BinaryNode<AnyType> remove(AnyType item, BinaryNode<AnyType> node) {
        /**
         * 删除结点的递归实现
         */
        if (node == null)   return null;

        int compare=item.compareTo(node.item);

        if(compare<0)
            node.left=remove(item,node.left);
        else    if(compare>0)
            node.right=remove(item,node.right);

        else    if(node.left!=null&&node.right!=null){//被删除的结点存在两个子结点
            node.item=findMin(node.right).item;//首先找到最小的结点，并保存其值
            node.right=remove(node.item,node.right);//然后将最小结点删除
        }else   node=node.left!=null?node.left:node.right;//将node结点的非空子结点返回

        return node;
    }

    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree");
        printTree(root);
    }

    private void printTree(BinaryNode<AnyType> node){
        if(node==null)  return;
        printTree(node.left);
        System.out.println(node.item+" ");
        printTree(node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++)
            bst.insert(i);
        for (int i = 0; i < 10; i++)
            System.out.println(bst.contains(i));

        try {
            System.out.println(bst.findMin());
            System.out.println(bst.findMax());
        } catch (UnderFlowException e) {
            System.out.println("树为空");
        }

        bst.printTree();

        for(int i=0;i<15;i++)
            bst.remove(i);
        bst.printTree();
    }
}
