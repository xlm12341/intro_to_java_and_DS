import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input some text: ");
        String text = scanner.nextLine();

        int[] counts = getCharFrequency(text);

        System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII CODE", "Character", "Frequency", "Code");
        Tree tree = getHuffmanTree(counts);

        String[] codes = getCode(tree.root);

        for (int i = 0; i < codes.length; i++) {
            if (counts[i] != 0) {
                System.out.printf("%-15s%-15s%-15s%-15s\n", i, (char) i, counts[i], codes[i]);
            }
        }
    }

    private static String[] getCode(Tree.Node root) {
        if (root == null) return null;
        String[] codes = new String[2*128];
        assignCode(root, codes);
        return codes;
    }

    private static void assignCode(Tree.Node root, String[] codes) {
        //左边或者右边不重要，哈夫曼树的特点就是要么为叶节点，要么左子树和右子树都存在
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
        else { //叶节点才是合法编码，记录之
            codes[(int) root.element] = root.code;
        }

    }

    private static Tree getHuffmanTree(int[] counts) {
        PriorityQueue<Tree> heap = new PriorityQueue<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char) i));
        }

        while (heap.size() > 1) {
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();
            Tree tree = new Tree(t1, t2);
            heap.add(tree);
        }
        return heap.remove();
    }

    private static int[] getCharFrequency(String text) {
        int[] counts = new int[256];
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i)]++;

        }
        return counts;
    }

    public static class Tree implements Comparable<Tree> {
        Node root;

        public Tree(Tree tree1, Tree tree2) {
            root = new Node();
            root.left = tree1.root;
            root.right = tree2.root;
            root.weight = tree1.root.weight + tree2.root.weight;
        }

        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }

        @Override
        public int compareTo(Tree o) {
            if (root.weight < o.root.weight)
                return -1; //purposely reverse the order
            else if (root.weight == o.root.weight)
                return 0;
            else return 1;
        }


        public class Node {
            char element;
            Node left;
            Node right;
            int weight;
            String code = "";

            public Node() {
            }

            public Node(int weight, char element) {
                this.element = element;
                this.weight = weight;
            }
        }
    }
}
