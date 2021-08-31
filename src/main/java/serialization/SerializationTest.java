package concept.serialization;

import java.io.*;

public class SerializationTest {


    private static class Parent {
        protected int field;
        protected Parent(){
            field = 5;
            System.out.println("Parent::Constructor");
        }
        public int getField() {
            return field;
        }
    }


    private static class Child extends Parent implements Serializable {
        private final static long serialVersionUID = 1L;
        private int i;
        private String name;
        public Child(int i){
            this.i = i;
            this.name = name;
            System.out.println("Child::Constructor");
        }
        public int getI() {
            return i;
        }
    }

    public static void main(String[] args){
            System.out.println("Creating...");
            Child child = new Child(1);
            //serialize(child);
            deSerialize();
    }

    private static void serialize(Child child){
        try{
        FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        System.out.println("Serializing...");
        oos.writeObject(child);
        oos.flush();
        oos.close();
    } catch (IOException ex){
        ex.printStackTrace();
    }
}

    private static void deSerialize(){
        try{
            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            System.out.println("Deserializing...");
            Child c1 = (Child) ois.readObject();
            System.out.println("c1.i="+c1.getI());
            System.out.println("c1.field="+c1.getField());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
