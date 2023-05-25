public abstract class Animal implements Existence {
    private int x;
    private int y;
    private int movementSpeed;
    private String foodType;
    private String fightType;
    public Animal(int x, int y, int movementSpeed, String foodType, String fightType) {
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.foodType = foodType;
        this.fightType = fightType;
    }
    @Override
    public abstract void move(int x, int y);
    @Override
    public abstract void fight();
}
