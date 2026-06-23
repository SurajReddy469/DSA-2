import java.util.Arrays;

public class MergeSortDemo {

    // Merge Sort Function
    static int[] mergeSort(int[] arr) {

        // Base case
        if (arr.length <= 1)
            return arr;

        // Find middle index
        int mid = arr.length / 2;

        // Split array into left and right halves
        int[] left =
            Arrays.copyOfRange(arr, 0, mid);

        int[] right =
            Arrays.copyOfRange(arr, mid, arr.length);

        // Recursive sorting
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge sorted halves
        return merge(left, right);
    }

    // Merge Function
    static int[] merge(int[] left, int[] right) {

        int[] out =
            new int[left.length + right.length];

        int i = 0;
        int j = 0;
        int k = 0;

        // Merge while both arrays have elements
        while (i < left.length &&
               j < right.length) {

            if (left[i] <= right[j])
                out[k++] = left[i++];
            else
                out[k++] = right[j++];
        }

        // Copy remaining left elements
        while (i < left.length)
            out[k++] = left[i++];

        // Copy remaining right elements
        while (j < right.length)
            out[k++] = right[j++];

        return out;
    }

    // Main Method
    public static void main(String[] args) {

        int[] arr = {
            10, 4, 15, 2,
            13, 6, 11, 1,
            9, 5, 14, 8
        };

        System.out.println(
            "Original Array:");

        System.out.println(
            Arrays.toString(arr));

        // Perform Merge Sort
        int[] sorted =
            mergeSort(arr);

        System.out.println(
            "\nSorted Array:");

        System.out.println(
            Arrays.toString(sorted));
    }
}
