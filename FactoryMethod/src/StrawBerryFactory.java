//具体工厂 StrawBerryFactory
public class StrawBerryFactory implements IFactory {
    @Override
    public Fruit createFruit() {
        return new Strawberry();
    }
}
