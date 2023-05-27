import java.util.Random;

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
    public void setX(int x) {
        super.setX(x);
    }
    @Override
    public void setY(int y) {
        super.setY(y);
    }
    @Override
    public String getFoodType() {
        return super.getFoodType();
    }
    @Override
    public String getFightType() {
        return super.getFightType();
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
        } else if(this.x < 0) {
            this.x += Board.getWidth();
        }
        if(this.y>=Board.getHeight()) {
            this.y -= Board.getHeight();
        } else if(this.y < 0) {
            this.y += Board.getHeight();
        }
    }

    @Override
    public Animal fightLoser(Animal other) {
        Random rd = new Random();
        int chances = rd.nextInt(100) + 1;
        if(other.getFightType().equals("bite")) {
            if(chances<=25) {
                return other;
            } else {
                return this;
            }
        }  else {
            if(chances<=65) {
                return other;
            } else {
                return this;
            }
        }
    }
    public void escape() {
        //implementacja
    }
}
