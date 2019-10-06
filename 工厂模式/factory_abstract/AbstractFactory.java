package factory_abstract;

public abstract class AbstractFactory {
    public abstract Car getCar(String type) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException;
}

