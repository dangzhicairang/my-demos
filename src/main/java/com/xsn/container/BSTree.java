package com.xsn.container;

import java.util.Comparator;

public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> mRoot;		//根结点

    public class BSTNode<T extends Comparable<T>> {	//内部类

        T key;				//关键字
        BSTNode<T> left;	//左孩子
        BSTNode<T> right;	//右孩子
        BSTNode<T> parent;	//父结点

        // 通过构造方法初始化结点
        public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
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

    public BSTree() {
        mRoot = null;
    }

    /**
     * 新建结点key，并将其插入到二叉树中
     * @param bst 二叉树
     * @param key 插入结点的键值
     * @param z 插入的结点
     */
    public void insert(T key) {
        BSTNode<T> z = new BSTNode<T>(key, null, null, null);
        // 如果新建结点失败，则返回
        if (z != null) {
            insert(this, z);
        }
    }

    private void insert(BSTree<T> bst, BSTNode<T> z) {
        BSTNode<T> y = null;
        BSTNode<T> x = bst.mRoot;//x指向该树的根结点

        /*
         * 查找z的插入位置
         * 比较根结点x与新节点z之间的关系
         * 		这时，y结点指向根结点x,若z小于根结点，则x指向x的左子树；否则指向右子树
         * 			直到左/右子树为空  【y结点是x结点的父结点】
         * 此时，z.parent指向y
         * if y=null
         *		新节点z就是父结点；
         * else
         * 		比较z和y的大小，
         * 		if z<y
         * 			z插入到y的左孩子的位置
         * 		else
         * 			z插入到y的右孩子的位置
         */
        int cmp;
        while (x != null) {
            y = x;
            cmp = z.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            bst.mRoot = z;
        } else {
            cmp = z.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = z;
            } else {
                y.right = z;
            }
        }
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrder() {
        preOrder(mRoot);
    }

    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            System.out.println(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历二叉树
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历二叉树
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.key + " ");
        }
    }

    /**
     * 查找
     * 递归实现 查找二叉树tree中键值为key的结点
     */
    public BSTNode<T> search(T key) {

        return search(mRoot, key);
    }

    private BSTNode<T> search(BSTNode<T> tree, T key) {
        if (tree == null) {
            return tree;
        }
        int cmp = key.compareTo(tree.key);
        if (cmp < 0) {
            return search(tree.left, key);
        } else if (cmp > 0) {
            return search(tree.right, key);
        } else {
            return tree;
        }
    }

    /**
     * 查找
     * 非递归实现 查找二叉树tree中键值为key的结点
     */
    public BSTNode<T> iterSearch(T key){
        return iterSearch(mRoot, key);
    }

    private BSTNode<T> iterSearch(BSTNode<T> tree, T key) {
        while (tree != null) {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                tree = tree.left;
            } else if (cmp>0) {
                tree = tree.right;
            } else {
                return tree;
            }
        }
        return tree;
    }

    /**
     * 查找最小结点：返回tree为根结点的二叉树的最小结点
     */
    public T min() {
        BSTNode<T> p = min(mRoot);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private BSTNode<T> min(BSTNode<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 查找最大结点：返回tree为根结点的二叉树的最大结点
     */
    public T max() {
        BSTNode<T> p = max(mRoot);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private BSTNode<T> max(BSTNode<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 找结点x的前驱结点：即，查找“二叉树中数据值小于该结点”的“最大结点”
     */
    public BSTNode<T> predecessor(BSTNode<T> x) {
        // 如果x存在左孩子，则“x的前驱结点”为“以其左孩子为根的子树的最大结点”
        if (x.left != null) {
            return max(x.left);
        }

        if (mRoot == x) {
            return null;
        }

        /**
         * 如果x没有左孩子，则x有以下两种可能
         * 1、x是“一个右孩子”，则“x的前驱结点”为“它的父结点”
         * 2、x是“一个左孩子”，则查找“x的最低的父结点，并且该父结点要具有右孩子”，
         * 					找到的这个“最低的父结点”就是“x的前驱结点”
         */
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 找结点x的后继结点：即，查找“二叉树中数据值大于该结点”的“最小结点”
     */
    public BSTNode<T> successor(BSTNode<T> x) {
        // 如果x存在右孩子，则“x的后继结点”为“以其右孩子为根的子树的最小结点”
        if (x.right != null) {
            return min(x.right);
        }

        /**
         * 如果x没有右孩子，则x有以下两种可能
         * 1、x是“一个左孩子”，则“x的后继结点”为“它的父结点”
         * 2、x是“一个右孩子”，则查找“x的最低的父结点，并且该父结点要具有左孩子”，
         * 					找到的这个“最低的父结点”就是“x的后继结点”
         */
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 删除结点z，并返回被删除的结点
     * @param tree 二叉树的根结点
     * @param z 删除的结点
     */
    public void remove(T key) {
        BSTNode<T> z, node;
        if ((z = search(mRoot, key)) != null) {
            if ((node = remove(this, z)) != null) {
                node = null;
            }
        }
    }

    /**
     * 删除结点z，并返回被删除的结点
     * @param bst 二叉树
     * @param z 删除的结点
     */
    private BSTNode<T> remove(BSTree<T> bst, BSTNode<T> z) {
        BSTNode<T> x = null;//真正删除结点的子树；左右子树合体的抽象
        BSTNode<T> y = null;//真正删除的结点

        //获取真正删除的结点
        if ((z.left == null) || (z.right == null)) {
            y = z;
        } else {
            y = successor(z);
        }

        //真正删除结点的子树；左右子树合体的抽象
        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        //删除  真正删除结点
        if (x != null) {
            x.parent = y.parent;
        }

        //删除之后把子树这段了，准备焊接
        if (y.parent == null) {
            bst.mRoot = x;
        } else if (y == y.parent.left.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        // 对删除转移 做值替换
        if (y != z) {
            z.key = y.key;
        }

        return y;
    }

    public void delete(T key) {
        // 获取要删除的结点
        BSTNode<T> node = search(mRoot, key);

        // 如果存在就删除
        if (node != null) {
            delete(node);
        }
    }
    private BSTNode<T> delete(BSTNode<T> node) {
        // 第3种情况，如果同时存在左右子结点
        if (node.left != null && node.right != null) {

            //获取后继结点
            BSTNode<T> successor = successor(node);

            // 转移后继结点值到当前结点
            node.key = successor.key;

            // 把要删除的当前结点设置为后继结点
            node = successor;
        }

        /**
         * 经过前一步处理，下面只有两种情况，只能是一个结点或者没有结点
         * 不管是否有子结点，都获取子结点
         */
        BSTNode<T> child;
        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        /**
         * 如果child!=null,就说明有一个结点的情况
         * 将父结点与子结点关联上
         */
        if (child != null) {
            child.parent = node.parent;
        }

        /**
         * 如果当前结点没有父结点(后继情况到这儿时一定有父结点)
         * 说明要删除的就是根结点
         */
        if (node.parent == null) {
            /**
             * 根结点设置为子结点
             * 按照前面的逻辑，根只有一个或者没有结点，所以直接赋child
             */
            mRoot = child;

        } else if (node == node.parent.left) {
            /**
             * 存在父结点，并且当前结点是左结点
             * 将父结点的左结点设置为child
             */
            node.parent.left = child;

        } else {
            /**
             * 存在父结点，并且当前结点是右结点
             * 将父结点的右结点设置为child
             */
            node.parent.right = child;
        }

        //返回被删除的结点
        return node;
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
            print(mRoot,mRoot.key, 0);
        }
    }

    private void print(BSTNode<T> tree, T key, int i) {
        if (tree != null) {
            if (i == 0) {//根结点
                System.out.printf("%2d is root\n",tree.key);
            } else {
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key,
                        i==1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    /**
     * 销毁二叉树
     */
    public void clear(){
        destory(mRoot);
        mRoot = null;
    }

    private void destory(BSTNode<T> tree) {
        if (tree == null) {
            return ;
        }
        if (tree.left != null) {
            destory(tree.left);
        }
        if (tree.right != null) {
            destory(tree.right);
        }
        tree = null;
    }

    private static final Integer arr[] = {5, 6, 1, 9, 4, 3};

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();

        System.out.println("==依次添加：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }

        /*System.out.println("==前序遍历：");
        tree.preOrder();
        System.out.println("==中序遍历：");
        tree.inOrder();
        System.out.println("==后序遍历：");
        tree.postOrder();*/

        /*System.out.println("++++输出树++++");
        tree.print();*/

        /*System.out.println("==最小值："+tree.min());
        System.out.println("==最大值："+tree.max());*/

        /*System.out.println("==删除根结点：" + arr[3]);
        tree.remove(arr[3]);

        System.out.println("==中序遍历：");
        tree.inOrder();*/

        //销毁二叉树
        tree.clear();
        System.out.println(tree.search(6));

    }
}
