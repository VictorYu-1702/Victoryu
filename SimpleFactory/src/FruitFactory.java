class FruitFactory {
    public static Fruit creatFruit(String fruitName) {
        Fruit fruit = null;
        switch (fruitName) {
            case "apple":
                fruit = new Apple();
                break;
            case "strawberry":
                fruit = new Strawberry();
                break;
            default:
                break;
        }
        return fruit;
    }
}
