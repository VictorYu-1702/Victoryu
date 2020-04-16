public class FactoryMethod {
    public static void main(String[] args) {
        IFactory factory=new AppleFactory();
        Fruit apple=factory.createFruit();
        apple.grow();
    }
}