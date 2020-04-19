public class CarDirector {
    //安排Car中各模块的顺序 相当于销售人员描述此车
    public CreateCar direct(CreateCar createCar){

        createCar.selectCarDoor();
        createCar.selectColor();
        createCar.selectTires();
        return createCar;
    }

}