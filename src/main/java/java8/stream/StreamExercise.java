package java8.stream;

import java.util.stream.Stream;

public class StreamExercise {

    public void createStream(){
        Stream s1 = Stream.empty();
        Stream s2 = Stream.of(1,2,3);
        Stream s3 = Stream.of(1,2,3);

        Stream s6 = Stream.concat(s2,s3);
        //Stream s3 = Stream.of(null); -exception
        Stream s4 = Stream.generate(() -> "one");
        s6.forEach(System.out::println);
        s3.findFirst().orElse(2);

    }


    public static void main(String[] args) {
        new StreamExercise().createStream();
    }
}
