class PSegNode {
    long sum;
    PSegNode left, right;

    PSegNode(long s, PSegNode l, PSegNode r) {
        sum = s;
        left = l;
        right = r;
    }
}

public class PersistentSegmentTree {

    static PSegNode build(int[] arr, int lo, int hi) {

        if (lo == hi)
            return new PSegNode(arr[lo], null, null);

        int mid = (lo + hi) / 2;

        PSegNode left = build(arr, lo, mid);
        PSegNode right = build(arr, mid + 1, hi);

        return new PSegNode(left.sum + right.sum, left, right);
    }

    static PSegNode pointUpdate(PSegNode node, int lo, int hi,
                                int idx, long newVal) {

        if (lo == hi)
            return new PSegNode(newVal, null, null);

        int mid = (lo + hi) / 2;

        PSegNode left = node.left;
        PSegNode right = node.right;

        if (idx <= mid)
            left = pointUpdate(node.left, lo, mid, idx, newVal);
        else
            right = pointUpdate(node.right, mid + 1, hi, idx, newVal);

        return new PSegNode(left.sum + right.sum, left, right);
    }

    static long rangeSum(PSegNode node, int lo, int hi,
                         int l, int r) {

        if (r < lo || hi < l)
            return 0;

        if (l <= lo && hi <= r)
            return node.sum;

        int mid = (lo + hi) / 2;

        return rangeSum(node.left, lo, mid, l, r)
             + rangeSum(node.right, mid + 1, hi, l, r);
    }

    public static void main(String[] args) {

        int[] stock = {12, 7, 25, 18, 9, 14, 6, 30};

        PSegNode v0 = build(stock, 0, 7);

        PSegNode v1 = pointUpdate(v0, 0, 7, 2, 75); // stock[3]+=50
        PSegNode v2 = pointUpdate(v1, 0, 7, 5, 10); // stock[6]-=4
        PSegNode v3 = pointUpdate(v2, 0, 7, 2, 63); // stock[3]-=12

        System.out.println("Version v0 Total Stock = " + v0.sum);
        System.out.println("Version v1 Total Stock = " + v1.sum);
        System.out.println("Version v2 Total Stock = " + v2.sum);
        System.out.println("Version v3 Total Stock = " + v3.sum);

        System.out.println("\nRange Sum [3..6] in v3 = "
                + rangeSum(v3, 0, 7, 2, 5));
    }
}