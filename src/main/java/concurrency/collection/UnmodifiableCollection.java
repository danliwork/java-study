package concurrency.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class UnmodifiableCollection {

    private class Data<T>{
        int a;
        int b;
        T[] array;
        public Data(int a, int b){
            this.a = a;
            this.b = b;
        }
        public void setA(int a){
            this.a = a;
        }
    }

    public void testModityDatainCollectionForUnmodifiableListwillwork(){
        Data data1= new Data(1,1);
        List<Data> dl = Arrays.asList(data1);
        //dl.add(d1);//UnsupportedOperationException
        List<Data> umdl = Collections.unmodifiableList(dl);
       // umdl.get(0).setA(5);
        System.out.println("A =" + dl.get(0).a);
        //umdl.remove(0);
    }

    public void testConcurrentmodificationException(){
        List<String> names = new CopyOnWriteArrayList<>();
        names.addAll(Arrays.asList("1","2","3"));
        List<String> unmodiNames = Collections.unmodifiableList(names);

        for(String s : unmodiNames){
            System.out.println("Name =" + s);
            names.remove(0);
        }
    }

    public void swap(Integer a, Integer b){
        Integer temp = a;
        a= b;
        b = temp;
    }

    public void testConcurrentHashMap(){
        ConcurrentMap<String, String> cm = new ConcurrentHashMap<>();
        Map<String, String> hm = new HashMap<>();
        hm.values().stream().
                collect(Collectors.toList());

    }


    //test method local inner class
    public static class Outerclass {
        // instance method of the outer class
        private String name = "dan";
        void my_Method() {
            int num = 23;
            name = "weiyan";
            // method-local inner class
            class MethodInner_Demo {
                public void print() {
                    System.out.println("This is method inner class "+ name);// no error
                }
            } // end of inner class

            // Accessing the inner class
            MethodInner_Demo inner = new MethodInner_Demo();
            inner.print();
        }
    }

    public void testBreakWithNestedForLoop(){
        for(int i =0; i<10; i++){
            System.out.println("----------Outer"+ i);
            for(int j = 0; j<10; j++){
                System.out.println("inner"+ j);
                if(j==5){
                    break;
                }
            }
        }
    }

    public void testCompletableFuture(){
        //CompletableFuture.supplyAsync(() -> "AAA").thenRunAsync(() -> "BBB").
    }

    public static void main(String[] args) {
        new UnmodifiableCollection().testBreakWithNestedForLoop();
        //BigDecimal bd = new BigDecimal("1000");
        //String a = bd.stripTrailingZeros().toString();

        //new UnmodifiableCollection().swap(5,6);
        //System.out.println("A =" + "");
        //String[] a = {"a", "c"};
        //Arrays.sort(a, String::compareToIgnoreCase);
        //new Outerclass().my_Method();

    }
}
