package factory_abstract;

/**
 * 奔驰工厂，覆盖所有奔驰车型的构造方法
 */
public class BenzFactory extends AbstractFactory {
    public Car getCar(String type) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException
    {
        Class cl = Class.forName(type);
        return (BenzCar)cl.newInstance();
    }

}

