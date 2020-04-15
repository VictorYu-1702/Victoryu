public class SimpleFactory {
    public static void main(String[] args) {
        Fruit fruit = FruitFactory.creatFruit("apple");
        fruit.grow();
    }
}
