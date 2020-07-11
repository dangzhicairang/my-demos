package com.xsn.container;

public class AVLTree<T extends Comparable<T>> {
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

    public AVLTree() {
        mRoot = null;
    }

    public void insert(T key) {
        if (mRoot == null) {
            mRoot = new Node<>(key, null, null, null);
        } else {
            insert(mRoot, key);
        }
    }

    private void insert(Node<T> z, T key) {
        int cmp = key.compareTo(z.key);
        Node node = null;

        if (cmp < 0) {
            if (z.left == null) {
                node = new Node<>(key, null, null, z);
                z.left = node;
            } else {
                insert(z.left, key);
            }
        }

        if (cmp > 0) {
            if (z.right == null) {
                node = new Node<>(key, null, null, z);
                z.right = node;
            } else {
                insert(z.right, key);
            }
        }

        if (cmp == 0) {
            return;
        }

        /**
         * 插入一个节点后检查平衡性
         */
        if (node != null) {
            balance(node);
        }
    }

    /**
     * 从当前节点向上追溯（实际上如果是插入的话当前节点和当前节点的父节点是可以不检查的）
     * 当BF = 2，即左子树深度 - 右子树深度 = 2 时，整棵树需要右旋（或者先左旋后右旋）
     * 当BF = -2，即左子树深度 - 右子树深度 = -2 时，整棵树需要左旋（或者先右旋后左旋）
     * @param node
     */
    private void balance(Node node) {
        Node target = node;

        while (target != null) {
            Node l = target.left;
            Node r = target.right;
            int deep = getDeep(l) - getDeep(r);

            if (deep == 2) {
                rightAction(node, target);
                break;
            }

            if (deep == -2) {
                leftAction(node, target);
                break;
            }

            target = target.parent;
        }
    }

    public int getDeep(T key) {
        Node node = search(key);
        return getDeep(node);
    }

    /**
     * 获取节点的深度
     * 当前节点为空则为0
     * 当前节点为叶子节点则为1
     * 否则为1 + 左右子树中较深的值
     * @param node
     * @return
     */
    private int getDeep(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return 1 + Math.max(getDeep(node.left), getDeep(node.right));
    }

    /**
     * 右旋之前，若插入节点是右孩子节点（之前画的情况2图）
     * 则需要先左旋其父节点，再右旋BF不平衡节点
     * @param node
     * @param target
     */
    private void rightAction(Node node, Node target) {
        if (node == node.parent.right) {
            leftRound(node.parent);
        }

        rightRound(target);
    }

    /**
     * 右旋操作
     * @param node
     */
    private void rightRound(Node node) {
        // 目标节点的左孩子
        Node left = node.left;
        // 目标节点的父节点
        Node pre = node.parent;

        // 若左孩子存在右孩子，则将该右孩子指向目标节点的左孩子（情况1的衍生图）
        if (left.right != null) {
            left.right.parent = node;
        }
        // 目标节点的左孩子指向其左孩子的右孩子（即便它是null）
        node.left = left.right;

        if (pre == null) {
            // 若父节点为空，即目标节点原本是根节点，则目标节点左孩子晋升为根节点
            mRoot = left;
        } else {
            /**
             * 否则，若目标节点为其父节点的左孩子，则父节点的左孩子指向标节点左孩子
             * 反之亦然
             */
            if (node == pre.right) {
                pre.right = left;
            } else {
                pre.left = left;
            }
        }

        /**
         * 目标节点左孩子的父节点指向目标节点父节点
         * 目标节点父节点指向其左孩子节点
         * 左孩子节点的右孩子指向目标节点
         */
        left.parent = pre;
        node.parent = left;
        left.right = node;
    }

    private void leftAction(Node node, Node target) {
        if (node == node.parent.left) {
            rightRound(node.parent);
        }

        leftRound(target);
    }

    /**
     * 与右旋操作对称
     * @param node
     */
    private void leftRound(Node node) {
        Node right = node.right;
        Node pre = node.parent;

        if (right.left != null) {
            right.left.parent = node;
        }
        node.right = right.left;

        if (pre == null) {
            mRoot = right;
        } else {
            if (node == pre.left) {
                pre.left = right;
            } else {
                pre.right = right;
            }
        }

        right.parent = pre;
        node.parent = right;
        right.left = node;
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
     * 与二叉排序树实现不同的地方是
     * 再删除节点后需要检查平衡性
     * 而检查的起始点如下：
     * 当删除的是叶子节点时，从其父元素开始追溯检查
     * 当删除的节点只有左（右）子树时，从其父元素开始追溯检查
     * 当删除的节点既有左子树，也有右子树时，其节点值替换成后继节点的值
     *      并删除后继节点，递归检查平衡性
     * @param node
     */
    private void remove(Node node) {
        if (node != null) {
            Node target = null;

            if (node.left == null && node.right == null) {
                target = node.parent;

                if (node == target.left) {
                    target.left = null;
                } else {
                    target.right = null;
                }

                node = null;

            } else if (node.left == null) {
                target = node.parent;

                Node right = node.right;
                right.parent = target;
                target.right = right;

                node = null;

            } else if (node.right == null) {
                target = node.parent;

                Node left = node.left;
                left.parent = target;
                target.right = left;

                node = null;

            } else if (node.left != null && node.right != null) {
                Node next = next(node);
                node.key = next.key;
                remove(next);
            }

            if (target != null) {
                balance(target);
            }
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
        AVLTree avlTree = new AVLTree();

        Integer[] is = {23, 18, 46, 22, 32, 14, 29, 16, 17, 5, 44, 7, 4, 1};
        for (Integer i : is) {
            avlTree.insert(i);
        }

        // avlTree.print();

        avlTree.remove(7);
        avlTree.print();
    }
}
