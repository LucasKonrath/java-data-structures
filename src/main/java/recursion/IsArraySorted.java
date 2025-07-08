package recursion;

public class IsArraySorted {
    public static boolean isArraySorted(int[] array, int index){
        if(array.length == 1 || index == 1){
            return true;
        }

        return array[index -1] >= array[index - 2] && isArraySorted(array, index - 1);
    }
}
