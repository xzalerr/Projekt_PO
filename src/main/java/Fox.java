public class Fox extends Animal {
    private int huntingRange;
    public Fox(int x, int y, int movementSpeed, String foodType, String fightType, int huntingRange) {
        super(x, y, movementSpeed, foodType, fightType);
        this.huntingRange = huntingRange;
    }
    @Override
    public void move(int x, int y) {

    }

    @Override
    public void fight() {

    }

    public void hunt() {

    }
}
