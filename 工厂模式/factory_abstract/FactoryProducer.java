package factory_abstract;
/**
 * 超级工厂类，建造工厂的工厂
 *https://blog.csdn.net/qq564425/article/details/81082242
 * 案例：某客户想要购买一辆车，他要联系4S店，首先得有4S店（抽象工厂）的电话。
 * 客户上网查询（建造工厂），发现了宝马4S店（具体工厂）的电话和奔驰4S店（具体工厂）的电话。
 * 客户拨通了宝马4S店的电话（获取具体工厂），发现目前店里可以提供（生产）多款车型（具体产品）供客户选择（BMW 320、BMW 530，BMW 740）。
 * 客户拨通了奔驰4S店的电话（获取具体工厂），发现目前店里可以提供（生产）多款车型（具体产品）供客户选择（BenzC200、BenzE300）。
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String type)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        Class cl = Class.forName(type);
        System.out.println("创建工厂"+type);
        return (AbstractFactory)cl.newInstance();
    }
}
