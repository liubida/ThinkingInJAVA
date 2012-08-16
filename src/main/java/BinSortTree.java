import java.util.Random;
import java.util.Stack;

/**
 * @author liubida 2011-11-8 下午8:45:19
 */
public class BinSortTree {

    public static void main(String[] args) {
        BinSortTree tree = new BinSortTree(new Node(100));
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 30; i++) {
            tree.insert(tree.root, new Node(rand.nextInt(200)));
        }
        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.inOrderByStack(tree.root);
        System.out.println();
        tree.afterOrder(tree.root);
        System.out.println();
        tree.afterOrderByStack(tree.root);
        //
        // System.out.println(a.b);
        // System.out.println("1" + 2 + 4);
    }

    static class Node {

        Node left;
        Node right;
        int  data;

        Node(int data){
            this.left = null;
            this.right = null;
            this.data = data;
        }
    }

    Node        root  = null;
    Stack<Node> stack = new Stack<Node>();

    public BinSortTree(Node p){
        this.root = p;
    }

    void preOrder(Node r) {
        if (null != r) {
            System.out.print(r.data + ",");
            preOrder(r.left);
            preOrder(r.right);
        }
    }

    void preOrderByStack(Node r) {
        if (null == r) return;
        Node p = r;
        stack.clear();
        while (true) {
            while (null != p) {
                System.out.println(p.data + ",");
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) break;
            p = stack.pop();
            p = p.right;
        }
    }

    void inOrder(Node r) {
        if (null != r) {
            inOrder(r.left);
            System.out.print(r.data + ",");
            inOrder(r.right);
        }
    }

    void inOrderByStack(Node r) {
        if (null == r) return;
        Node p = r;
        stack.clear();
        while (true) {
            while (null != p) {
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) break;
            p = stack.pop();
            System.out.print(p.data + ",");
            p = p.right;
        }
    }

    void afterOrder(Node r) {
        if (null != r) {
            afterOrder(r.left);
            afterOrder(r.right);
            System.out.print(r.data+",");
        }
    }

    void afterOrderByStack(Node r) {
        if (null == r) return;
        Node p = r;
        Stack<Node> out = new Stack<Node>();
        stack.clear();
        stack.push(p);
        while (!stack.isEmpty()) {
            p = stack.pop();
            out.push(p);
            if (null != p.left) stack.push(p.left);
            if (null != p.right) stack.push(p.right);
        }
        while (!out.isEmpty()) {
            System.out.print(out.pop().data + ",");
        }
    }

    void insert(Node r, Node p) {
        if (null == p) return;
        else {
            if (p.data < r.data) {
                if (null == r.left) r.left = p;
                else insert(r.left, p);
            } else {
                if (null == r.right) r.right = p;
                else insert(r.right, p);
            }
        }
    }
}
