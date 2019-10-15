package serialize;

import java.io.*;

public class Test {
    public static void serialize() {
        Son son = new Son();
        son.setAge(12);
        son.setName("Tom");
        System.out.println(son);
        ObjectOutput objectOutput = null;
        try {
            objectOutput = new ObjectOutputStream(new FileOutputStream("E:\\JAVA\\JAVA程序\\Programs\\src\\serialize\\son.ser"));
            objectOutput.writeObject(son);
            objectOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutput != null) {
                try {
                    objectOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deserialize() {
        ObjectInput objectInput = null;
        try {
            objectInput = new ObjectInputStream(new FileInputStream("E:\\JAVA\\JAVA程序\\Programs\\src\\serialize\\son.ser"));
            Son son = (Son) objectInput.readObject();
            System.out.println(son.getAge());//输出12 age属性是Son类从Father类继承过来的，由于父类实现Serializable接口，所以序列化子类时age属性也会被序列化。
            System.out.println(son.getName());//输出Tom 尽管Son类没有实现Serializable接口，但由于其父类实现了该接口，所以Son类中name属性依然被序列化。

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInput != null) {
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        serialize();// 序列化
        deserialize();// 反序列化
    }
}


