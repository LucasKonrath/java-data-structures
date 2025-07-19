package searching;


public class BinarySearch implements SearchAlgorithm {


    @Override
    public int search(int[] input, int target) {
        if (input == null) {
            return -1;
        }

        if (input.length == 0) {
            return -1;
        }

        int left = 0;
        int right = input.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (input[mid] == target) {
                return mid;
            } else if (input[mid] < target) {
                // Target is in the right half
                left = mid + 1;
            } else {
                // Target is in the left half
                right = mid - 1;
            }
        }

        // Target not found
        return -1;
    }


    public int searchRecursive(int[] input, int target, int left, int right) {
        if (input == null || left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (input[mid] == target) {
            return mid;
        } else if (input[mid] < target) {
            return searchRecursive(input, target, mid + 1, right);
        } else {
            return searchRecursive(input, target, left, mid - 1);
        }
    }

    public int searchRecursive(int[] input, int target) {
        if (input == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        return searchRecursive(input, target, 0, input.length - 1);
    }
}
