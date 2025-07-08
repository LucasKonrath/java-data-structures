package recursion;

public class BinaryStrings {
    int[] array;
    public BinaryStrings(int n) {
        this.array = new int[n];
    }

    public void bin(int n){
        if(n <= 0){
            System.out.println(java.util.Arrays.toString(array));
        } else {
            array[n - 1] = 0;
            bin(n - 1);
            array[n - 1] = 1;
            bin(n - 1);
        }
    }
}
