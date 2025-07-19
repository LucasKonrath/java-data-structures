package searching;

public class OrderedLinearSearch implements SearchAlgorithm {

    @Override
    public int search(int[] input, int target) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == target) {
                return i;
            }
            if (input[i] > target) {
                break;
            }
        }
        return -1;
    }
}
