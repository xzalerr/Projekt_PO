public class RoeDeer  extends Animal {
    private int escapeRange;
    public RoeDeer(int x, int y, int movementSpeed, String foodType, String fightType, int escapeRange) {
        super(x, y, movementSpeed, foodType, fightType);
        this.escapeRange = escapeRange;
    }
    @Override
    public void move(int x, int y) {

    }

    @Override
    public void fight() {

    }

    public void escape() {

    }
}
