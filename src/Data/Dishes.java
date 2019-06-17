package Data;

public class Dishes {

    private String dishName;
    private int preparationTime;
    private int price;

    public Dishes(String dishName, int preparationTime, int price) {
        this.dishName = dishName;
        this.preparationTime = preparationTime;
        this.price = price;
    }

    public String toString() {
        return dishName + "\nPreparation Time: " + preparationTime + "\nPrice : RM " + price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getDishName() {
        return dishName;
    }

    public int getPrice() {
        return price;
    }

}
