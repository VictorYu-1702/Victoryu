//具体工厂 AppleFactory
public class AppleFactory implements IFactory {
    @Override
    public Fruit createFruit() {
        return new Apple();
    }
}
