public class CreateCarImpl implements CreateCar{

    Car car;

    public CreateCarImpl() {
        // TODO Auto-generated constructor stub
        car = new Car();
    }


    @Override
    public void selectColor() {
        // TODO Auto-generated method stub
        car.setColor("颜色");
    }

    @Override
    public void selectCarDoor() {
        // TODO Auto-generated method stub
        car.setCarDoor(2);
    }

    @Override
    public void selectTires() {
        // TODO Auto-generated method stub
        car.setTires(4);
    }

    @Override
    public Car createCar() {
        // TODO Auto-generated method stub
        return car;//返回一个组件好的Car实例
    }

}