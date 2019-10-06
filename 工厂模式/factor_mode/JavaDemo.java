package factor_mode;

public class JavaDemo {
    public static void main(String args[]) {
       IFood food=new breadFactory().creatFood();
       food.eat();
    }
}