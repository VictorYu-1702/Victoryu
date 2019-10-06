package factor_mode;

public class milkFactory extends FoodFactory{
    @Override
    public  IFood creatFood(){
        return new Milk();
    }
}
