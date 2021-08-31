package concept.concurrency.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

/**
 * Increment all elements in a array parallelly
 */
public class ForkJoinArrayIncrement extends RecursiveAction{
    private static final int THRESHOLD = 10;
    private final long[] array;
    private int lo;
    private int hi;

    public ForkJoinArrayIncrement(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }


    @Override
    protected void compute() {
        if(hi-lo < THRESHOLD){
            for(int i=lo; i<hi; i++){
                array[i]++;
            }
        } else {
            int mid = lo + (hi-lo)/2;//(lo + hi) >>> 1
            ForkJoinArrayIncrement firstTask = new ForkJoinArrayIncrement(array, lo, mid);
            //firstTask.fork();
            ForkJoinArrayIncrement secondTask = new ForkJoinArrayIncrement(array, mid, hi);
            //secondTask.compute();
            //firstTask.join();
            invokeAll(firstTask, secondTask);
        }
    }

    public static void main(String[] args) {

        long[] nums = new long[100];
        IntStream.range(0, 100).forEach(i -> nums[i] = i);
        ForkJoinPool pool = new ForkJoinPool(10);
        pool.invoke(new ForkJoinArrayIncrement(nums, 0, nums.length));
        System.out.println(Arrays.toString(nums));
    }
}
