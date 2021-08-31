package concurrency.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static class UserService{

        public final static ThreadLocal<SimpleDateFormat> threadLocal =
                ThreadLocal.withInitial(() -> new SimpleDateFormat("YYYY-MM-DD"));

        //executed 10000 times with 3(n of thread) copy of SimpleDateFormat
        // comparing with typical static field: all thread shared one SimpleDateFormat - not thread safe
        //comparing with typical local field: 10000 SimpleDateFormat is created
        public void birthDate(){
            Date bd = new Date(2020, 05, 05);
            System.out.println(threadLocal.get().format(bd)) ;
        }
    }

    public static void shutDownExecutor(ExecutorService pool){
        pool.shutdown();
        try{
            if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                pool.shutdownNow();
                if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                    //log pool can not be shut doen
                }
            }
        } catch (InterruptedException e){
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(3);
        UserService us = new UserService();
        for(int i=0; i<10; i++) {
            pool.submit(() -> us.birthDate());
        }
        Thread.sleep(100000000);
    }

}
