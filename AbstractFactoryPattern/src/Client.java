public class Client {
    public static void main(String[] args) {
        //定义出两个工厂
        AbstractFactory creator1 = new Factory1();
        AbstractFactory creator2 = new Factory2();
        //产生A1对象
        AbstractProductA a1 = creator1.createProductA();
        //产生A2对象
        AbstractProductA a2 = creator2.createProductA();
        //产生B1对象
        AbstractProductB b1 = creator1.createProductB();
        //产生B2对象
        AbstractProductB b2 = creator2.createProductB();
        a1.doSomething();
        a2.doSomething();
        b1.doSomething();
        b2.doSomething();
    }
}