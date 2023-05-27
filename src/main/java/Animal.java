import java.util.Random;

public abstract class Animal implements Existence {
    protected int x;
    protected int y;
    protected int movementSpeed;
    protected String foodType;
    protected String fightType;
    protected String symbol;
    public Animal(int x, int y, int movementSpeed, String foodType, String fightType) {
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.foodType = foodType;
        this.fightType = fightType;
    }
    public int genDirection(int max) {
        Random rd = new Random();
        int direction = rd.nextInt(max) + 1;
        return direction;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getFightType() {
        return fightType;
    }
    public String getFoodType() {
        return foodType;
    }
    @Override
    public abstract void move(int x, int y);
    @Override
    public abstract Animal fightLoser(Animal other);
}
