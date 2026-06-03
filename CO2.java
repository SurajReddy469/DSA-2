import java.util.*;

class SparseTableMin {

    int[][] table;
    int n, K;

    // Constructor
    SparseTableMin(int[] arr) {
        n = arr.length;
        K = (int) (Math.log(n) / Math.log(2)) + 1;

        table = new int[K][n];

        buildSparseTable(arr);
    }

    // BUILD SPARSE TABLE (O(n log n))
    void buildSparseTable(int[] arr) {

        // k = 0 (base row)
        for (int i = 0; i < n; i++) {
            table[0][i] = arr[i];
        }

        // build higher levels
        for (int k = 1; k < K; k++) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                table[k][i] = Math.min(
                        table[k - 1][i],
                        table[k - 1][i + (1 << (k - 1))]
                );
            }
        }
    }

    // O(1) RANGE MIN QUERY
    int queryMin(int lo, int hi) {

        int len = hi - lo + 1;
        int k = (int) (Math.log(len) / Math.log(2));

        return Math.min(
                table[k][lo],
                table[k][hi - (1 << k) + 1]
        );
    }

    // TEST
    public static void main(String[] args) {

        int[] arr = {42, 18, 55, 9, 31, 22, 6, 47};

        SparseTableMin st = new SparseTableMin(arr);

        System.out.println("Query [1..6] = " + st.queryMin(1, 6));
        System.out.println("Query [2..5] = " + st.queryMin(2, 5));
    }
}
