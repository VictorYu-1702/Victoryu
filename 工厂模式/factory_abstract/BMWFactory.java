package factory_abstract;
/**
 * 宝马工厂，覆盖所有宝马车型的构造方法
 */
public class BMWFactory extends AbstractFactory
{
    public Car getCar(String type) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException
    {
        Class cl = Class.forName(type);
        return (BMWCar)cl.newInstance();
    }
}
