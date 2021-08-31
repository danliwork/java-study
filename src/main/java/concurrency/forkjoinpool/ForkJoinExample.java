package concept.concurrency.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

        long mergedResult = forkJoinPool.invoke(myRecursiveTask);

        System.out.println("mergedResult = " + mergedResult);
    }
}
