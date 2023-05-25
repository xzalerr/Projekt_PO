public class RoeDeer  extends Animal {
    private int escapeRange;
    public RoeDeer(int x, int y, int movementSpeed, String foodType, String fightType, int escapeRange) {
        super(x, y, movementSpeed, foodType, fightType);
        this.escapeRange = escapeRange;
        this.symbol = "R";
    }
    @Override
    public int genDirection(int max) {
        return super.genDirection(max);
    }
    @Override
    public int getX() {
        return super.getX();
    }
    @Override
    public int getY() {
        return super.getY();
    }
    @Override
    public void move(int x, int y) {
        int direction = genDirection(3);
        if(direction == 1) {
            this.x += movementSpeed;
        } else if(direction==2) {
            this.y -=movementSpeed;
        } else {
            this.y +=movementSpeed;
        }
        if(this.x>=Board.getWidth()) {
            this.x -= Board.getWidth();
        }
        if(this.y>=Board.getHeight()) {
            this.y -= Board.getHeight();
        } else if(this.y < 0) {
            this.y += Board.getHeight();
        }
    }

    @Override
    public void fight() {
        //implementacja
    }

    public void escape() {
        //implementacja
    }
}
