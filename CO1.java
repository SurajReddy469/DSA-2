import java.util.*;

class IntervalNode {
    int start, end;
    int maxEnd;
    String streamId;
    IntervalNode left, right;

    IntervalNode(String id, int start, int end) {
        this.streamId = id;
        this.start = start;
        this.end = end;
        this.maxEnd = end;
    }
}

public class IntervalTreeCO1 {

    // INSERT (BST by start time)
    static IntervalNode insert(IntervalNode root, IntervalNode node) {
        if (root == null) return node;

        if (node.start < root.start) {
            root.left = insert(root.left, node);
        } else {
            root.right = insert(root.right, node);
        }

        // update maxEnd after insertion
        root.maxEnd = Math.max(root.end,
                Math.max(getMax(root.left), getMax(root.right)));

        return root;
    }

    static int getMax(IntervalNode node) {
        return (node == null) ? Integer.MIN_VALUE : node.maxEnd;
    }

    // OVERLAP CHECK
    static boolean isOverlap(int lo, int hi, IntervalNode node) {
        return node.start <= hi && node.end >= lo;
    }

    // INTERVAL TREE QUERY (with pruning)
    static List<String> findOverlapping(IntervalNode node,
                                        int lo, int hi,
                                        List<String> result) {

        if (node == null) return result;

        // PRUNE RULE
        if (lo > node.maxEnd) {
            return result;
        }

        // LEFT subtree
        findOverlapping(node.left, lo, hi, result);

        // CURRENT node check
        if (isOverlap(lo, hi, node)) {
            result.add(node.streamId);
        }

        // RIGHT subtree (only if needed)
        if (node.start <= hi) {
            findOverlapping(node.right, lo, hi, result);
        }

        return result;
    }

    // TEST CASE (given problem)
    public static void main(String[] args) {

        IntervalNode root = null;

        root = insert(root, new IntervalNode("S1", 1400, 1430));
        root = insert(root, new IntervalNode("S2", 1410, 1420));
        root = insert(root, new IntervalNode("S3", 1435, 1500));
        root = insert(root, new IntervalNode("S4", 1415, 1445));
        root = insert(root, new IntervalNode("S5", 1350, 1405));
        root = insert(root, new IntervalNode("S6", 1425, 1455));
        root = insert(root, new IntervalNode("S7", 1440, 1450));
        root = insert(root, new IntervalNode("S8", 1400, 1415));

        int lo = 1425;
        int hi = 1450;

        List<String> res = new ArrayList<>();

        findOverlapping(root, lo, hi, res);

        System.out.println("Overlapping Streams: " + res);
    }
}
