package java8.stream;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers) {
        return numbers.stream().map(i -> (int) Math.sqrt((double) i)).collect(toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user) {
        return user.stream().collect(Collectors.mapping(User::getAge, toList()));
    }

    public static List<Integer> getDistinctAges(List<User> users) {
        Map<String, User> userWithAge = users.stream().collect(Collectors.toMap(User::getName, identity()));
        return users.stream().map(User::getAge).distinct().collect(toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit) {
        return users.stream().limit(limit).collect(Collectors.toList());
    }

    public static Integer countUsersOlderThen25(List<User> users) {
        return (int) users.stream().filter(user -> user.getAge() > 25).count();
    }

    // map(s -> s.toUpperCase())
    public static List<String> mapToUpperCase(List<String> strings) {
        return strings.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip) {
        return integers.stream().skip(toSkip).collect(Collectors.toList());
    }

    public static List<String> getFirstNames(List<String> names) {
        return names.stream().map(n -> n.split(" ")[0]).collect(Collectors.toList());
    }

    /// Need to to research
    //flatMap: map To stream, the flattening
    public static List<String> getDistinctLetters(List<String> names) {
        return names.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }


    public static String separateNamesByComma(List<User> users) {
        return users.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    public static double getAverageAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).average().getAsDouble();
    }

    public static Integer getMaxAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).max().getAsInt();
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).min().getAsInt();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users) {
        return users.stream().collect(Collectors.partitioningBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(User::isMale, Collectors.groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users) {
        return users.stream().collect(Collectors.groupingBy(User::isMale, Collectors.counting()));
    }

    public static boolean anyMatch(List<User> users, int age) {
        return users.stream().anyMatch(u -> u.getAge() == age);
    }

    public static boolean noneMatch(List<User> users, int age) {
        return users.stream().noneMatch(u -> u.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name) {
        return users.stream().filter(u ->u.getName().equals(name)).findAny();
    }

    public static List<User> sortByAge(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getAge)).collect(toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream) {
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers() {
        return IntStream.iterate(1, i -> i+1).filter(Stream8::isPrime).limit(10).boxed().collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> generate10RandomNumbers() {
        return new Random().ints(10).boxed().collect(Collectors.toList());
    }

    public static User findOldest(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getAge).reversed()).findFirst().get();
    }

    public static int sumAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).sum();
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users) {
        return users.stream().collect(Collectors.summarizingInt(User::getAge));
    }

}
