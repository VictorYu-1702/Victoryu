//抽象建造者  规范车的各个组成部分并进行抽象
public interface CreateCar {
    //定义了产品的创建方法和返回方法
    public void selectColor();

    public void selectCarDoor();

    public void selectTires();

    public Car createCar();

}