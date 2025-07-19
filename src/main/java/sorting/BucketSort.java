package sorting;

public class BucketSort {
    public void sort(int[] a) {
        int i, j, k;

        int[] buckets = new int[10]; // Assuming numbers are in the range 0-9

        for(i = 0; i < 10; i++) {
            buckets[i] = 0;
        }
        for(i = 0; i < a.length; i++) {
            buckets[a[i]]++;
        }
        for(j = 0, k = 0; j < 10; j++) {
            while(buckets[j] > 0) {
                a[k++] = j;
                buckets[j]--;
            }
        }
    }
}
