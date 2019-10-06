package factor_mode;

public class breadFactory extends FoodFactory {
    @Override
    public  IFood creatFood(){
       return new Bread();
    }
}
