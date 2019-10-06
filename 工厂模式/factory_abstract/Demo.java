package factory_abstract;
/**
 * 验证
 */
public class Demo {
    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException, ClassNotFoundException
    {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("BMWFactory");
        Car bmwCar = abstractFactory.getCar("BMW320");
        bmwCar.drive();
        Car bmwCar1 = abstractFactory.getCar("BMW530");
        bmwCar1.drive();
        Car bmwCar2 = abstractFactory.getCar("BMW740");
        bmwCar2.drive();
        AbstractFactory abstractFactory1 = FactoryProducer.getFactory("BenzFactory");
        Car benzCar = abstractFactory1.getCar("BenzC200");
        benzCar.drive();
        Car benzCar1 = abstractFactory1.getCar("BenzE300");
        benzCar1.drive();
    }
}
