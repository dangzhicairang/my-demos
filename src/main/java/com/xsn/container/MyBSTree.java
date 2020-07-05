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

    public void insert(T key) {}

    private void insert(MyBSTree<T> bst, Node<T> z) {

    }

    public void preOrder() {

    }

    public void inOrder() {

    }

    public void postOrder() {

    }

    public Node<T> search(T key) {

        return null;
    }

    public Node<T> iterSearch(T key) {

        return null;
    }

    public T min() {

        return null;
    }

    public T max() {

        return null;
    }

    /**
     * 找结点x的前驱结点：即，查找“二叉树中数据值小于该结点”的“最大结点”
     */
    public Node<T> predecessor(Node<T> x) {

        return null;
    }

    /**
     * 找结点x的后继结点：即，查找“二叉树中数据值大于该结点”的“最小结点”
     */
    public Node<T> successor(Node<T> x) {

        return null;
    }

    public void remove(T key) {

    }

    public void print() {

    }

    public void clear(){

    }
}
