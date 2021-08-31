package basic.initorders;


public class ObjectInitialization {
    public static void main(String[] args) {
        new Sub().hello();
        System.out.printf("%s\n", "Second time invokation ------------");
        new Sub().hello();
    }
}

class Base {
    {
        System.out.printf("%s - %s - %s\n", "base", "instance", "block");
    }
    private String fieldBase = fieldBase();
    private String staticFieldBase = fieldstaticBase();


    static {
        System.out.printf("%s - %s - %s\n", "base", "static", "block");
    }


    public Base() {
        System.out.printf("%s - %s\n", "base", "constructor");
    }

    //@PostConstruct
    public void init() {
        System.out.printf("%s - %s\n", "base", "PostConstruct");
    }

    public void hello() {
        System.out.printf("%s - %s\n", "base", "method");
    }

    public String fieldBase() {
        System.out.printf("%s - %s\n", "base", "field");
        return "";
    }

    public static String fieldstaticBase() {
        System.out.printf("%s - %s\n", "base", "static field");
        return "";
    }
}

class Sub extends Base {
    private String field = field();
    private String staticField = fieldstatic();

    static {
        System.out.printf("%s - %s - %s\n", "sub", "static", "block");
    }
    {
        System.out.printf("%s - %s - %s\n", "sub", "instance", "block");
    }

    public Sub() {
        System.out.printf("%s - %s\n", "sub", "constructor");
    }
    public String field() {
         System.out.printf("%s - %s\n", "sub", "field");
         return "";
    }
    public static String fieldstatic() {
        System.out.printf("%s - %s\n", "sub", "static field");
        return "";
    }

    //@PostConstruct
    public void init() {
        System.out.printf("%s - %s\n", "sub", "PostConstruct");
    }

    @Override
    public void hello() {
        // super.hello();
        System.out.printf("%s - %s\n", "sub", "method");
    }
}