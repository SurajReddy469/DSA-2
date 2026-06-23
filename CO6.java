public class LCSDemo {
    static String lcs(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] dp =
            new int[m + 1][n + 1];
        // Build DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) ==
                    B.charAt(j - 1)) {
                    dp[i][j] =
                        dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] =
                        Math.max(dp[i - 1][j],
                                 dp[i][j - 1]);
                }
            }
        }
        System.out.println("LCS DP Table:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder sb =
            new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (A.charAt(i - 1) ==
                B.charAt(j - 1)) {
                sb.append(A.charAt(i - 1));
                i--;
                j--;
            }
            else if (dp[i - 1][j] >=
                     dp[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String A = "NETWORK";
        String B = "NOTEBOOK";
        System.out.println("String A: " + A);
        System.out.println("String B: " + B);
        String result = lcs(A, B);
        System.out.println("\nLongest Common Subsequence: " + result);
        System.out.println("LCS Length: " + result.length());
    }
}
