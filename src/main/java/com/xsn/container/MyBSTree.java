package com.xsn.container;

public class MyBSTree<T extends Comparable<T>> {

    private Node<T> mRoot;		//根结点

    public class Node<T extends Comparable<T>> {	//内部类

        T key;				//关键字
        Node<T> left;	//左孩子
        Node<T> right;	//右孩子
        Node<T> parent;	//父结点

        // 通过构造方法初始化结点
        public Node(T key, Node<T> left, Node<T> right, Node<T> parent) {
            super();
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey(){
            return key;
        }

        @Override
        public String toString() {

            return "key:" + key;
        }
    }

    public MyBSTree() {
        mRoot = null;
    }

    /**
     * 当mRoot为空，则插入的为根元素
     * 否则为子树
     * @param key
     */
    public void insert(T key) {
        if (mRoot == null) {
            mRoot = new Node<>(key, null, null, null);

        } else {
            insert(mRoot, key);
        }
    }

    /**
     * 与当前节点key作比较，小于当前节点是，先判断是否叶子节点，
     * 实则插入为左子树，否则递归插入左子树。右边同理
     * @param z
     * @param key
     */
    private void insert(Node<T> z, T key) {
        Node node = null;
        int cmp = key.compareTo(z.key);

        if (cmp < 0) {
            node = z.left;

            if (node == null) {

                z.left = new Node<>(key, null, null, z);
            } else {

                insert(node, key);
            }

            return;
        }

        if (cmp > 0) {
            node = z.right;

            if (node == null) {

                z.right = new Node<>(key, null, null, z);
            } else {

                insert(node, key);
            }

            return;
        }

        throw new RuntimeException("can not insert existed key");
    }

    /**
     * 先序遍历
     */
    public void preOrder() {
        preOrder(mRoot);
    }

    private void preOrder(Node node) {
        if (node != null) {
            preOrder(node.left);
            System.out.println(node.key);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(Node node) {
        if (node != null) {
            preOrder(node.left);
            preOrder(node.right);
            System.out.println(node.key);
        }
    }

    public Node<T> search(T key) {

        return search(mRoot, key);
    }

    /**
     * 若查找key小于当前节点，则递归查找左子树
     * 反之亦然
     * @param node
     * @param key
     * @return
     */
    private Node<T> search(Node<T> node, T key) {

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return search(node.left, key);
        }

        if (cmp > 0) {
            return search(node.right, key);
        }

        return node;
    }

    public T min() {

        return min(mRoot).key;
    }

    /**
     * 节点为空则返回null
     * 否则，左子树为空返回当前节点
     * 否则，递归在左子树查询
     * @param node
     * @return
     */
    private Node<T> min(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> temp = node.left;
        if (temp == null) {
            return node;
        }

        return min(temp);
    }

    public T max() {

        return max(mRoot).key;
    }

    /**
     * 节点为空则返回null
     * 否则，右子树为空返回当前节点
     * 否则，递归在右子树查询
     * @param node
     * @return
     */
    private Node<T> max(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> temp = node.right;
        if (temp == null) {
            return node;
        }

        return max(temp);
    }

    public T pre(T key) {
        Node<T> node = search(key);
        node = pre(node);
        return node == null ? null : node.key;
    }

    /**
     * 查询该节点的前置节点（比当前节点key值小的节点中的最大值）
     * 当节点有左子树时，即求左子树的最大值max（node.left）
     * 否则，当节点无父，则返回null
     * 否则，当节点为右子树时，它的父节点即是前置节点（二叉排序树的定义）
     * 否则，当节点为左子树，则向上追溯，其第一个拥有右子树的父节点即为前置节点（可简单画图证明）
     * 否则，返回null
     * @param node
     * @return
     */
    private Node<T> pre(Node<T> node) {

        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return max(node.left);
        }

        Node<T> pre = node.parent;

        if (pre == null) {
            return null;
        }

        if (pre.right == node) {
            return pre;
        }

        Node temp = pre.parent;
        while (temp != null && pre != temp.right) {
            pre = temp;
            temp = pre.parent;
        }

        return temp;
    }

    public T next(T key) {
        Node<T> node = search(key);
        node = next(node);
        return node == null ? null : node.key;
    }

    /**
     * 查询该节点的后置节点（比当前节点key值大的节点中的最小值）
     * 当节点有右子树时，即求右子树的最小值min（node.right）
     * 否则，当节点无父，则返回null
     * 否则，当节点为左子树时，它的父节点即是后置节点（二叉排序树的定义）
     * 否则，当节点为右子树，则向上追溯，其第一个拥有左子树的父节点即为后置节点（可简单画图证明）
     * 否则，返回null
     * @param node
     * @return
     */
    private Node<T> next(Node<T> node) {

        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return min(node.right);
        }

        Node<T> pre = node.parent;

        if (pre == null) {
            return null;
        }

        if (node == pre.left) {
            return pre;
        }

        Node<T> temp = pre.parent;
        while (temp != null && pre != temp.left) {
            pre = temp;
            temp = pre.parent;
        }

        return temp;
    }

    public void remove(T key) {
        Node<T> node = search(key);
        remove(node);
    }

    /**
     * 当前节点为叶子节点（无左右子树），则直接删除
     * （当前为父节点的左子时，父节点左子指向null，反之亦然）
     * 当前节点只有左子时，把左子直接指向父节点，父节点左子指向当前节点左子
     * 反之亦然
     * 当前节点既有左子树又有右子树时，取前置或后置节点代替当前节点
     * 并移除该前置或后置节点
     * @param node
     */
    private void remove(Node node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            Node pre = node.parent;

            if (node == pre.left) {
                pre.left = null;
            } else {
                pre.right = null;
            }

            return;
        }

        if (node.left == null) {
            node.parent.right = node.right;
            node.right.parent = node.parent;
            return;
        }

        if (node.right == null) {
            node.parent.left = node.left;
            node.left.parent = node.parent;
            return;
        }


        if (node.left != null && node.right != null) {
            Node<T> temp = next(node);
            node.key = temp.key;
            remove(temp);
            return;
        }
    }

    public void clear(){
        clear(mRoot);
    }

    private void clear(Node node) {
        if (node != null) {
            clear(node.left);
            clear(node.right);
            node = null;
        }
    }

    /**
     * 打印二叉查找树
     * key:  结点的值
     * i  :  0,表示该结点是根结点
     * 		 -1,表示该结点是它的父结点的左孩子
     * 		  1,表示该结点是它的父结点的右孩子
     */
    public void print() {
        if (mRoot != null) {
            print(mRoot, mRoot.key, 0);
        }
    }

    private void print(Node<T> tree, T key, int i) {
        if (tree != null) {
            if (i == 0) {//根结点
                System.out.printf("%2d is root\n", tree.key);
            } else {
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key,
                        i == 1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public static void main(String[] args) {
        MyBSTree myBSTree = new MyBSTree();

        Integer[] is = {23, 18, 46, 22, 32, 14, 29, 16, 17, 5, 44, 7, 1};
        for (Integer i : is) {
            myBSTree.insert(i);
        }

        // myBSTree.clear();

        System.gc();

        // myBSTree.print();

        // myBSTree.preOrder();

        /*System.out.println(myBSTree.max());
        System.out.println(myBSTree.min());

        System.out.println(myBSTree.pre(22));
        System.out.println(myBSTree.next(22));*/

        /*myBSTree.remove(14);
        myBSTree.print();*/
    }
}
