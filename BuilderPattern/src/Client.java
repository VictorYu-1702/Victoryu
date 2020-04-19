public class Client {

    public static void main(String[] args) {

        CarDirector director = new CarDirector();

        director.direct(new CreateCarImpl());

    }
}