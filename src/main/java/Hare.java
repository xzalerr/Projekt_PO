import java.util.Random;

public class Hare extends Animal {
    private int escapeRange;
    public Hare(int x, int y, int movementSpeed, String foodType, String fightType, int escapeRange) {
        super(x, y, movementSpeed, foodType, fightType);
        this.escapeRange = escapeRange;
        this.symbol = "H";
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
    public String getFightType() {
        return super.getFightType();
    }

    @Override
    public void move(int x, int y) {
        int direction = genDirection(2);
        if(direction == 1) {
            this.x += movementSpeed;
            this.y -=movementSpeed;
        } else {
            this.x -= movementSpeed;
            this.y -=movementSpeed;
        }
        if(this.x>=Board.getWidth()) {
            this.x -= Board.getWidth();
        } else if(this.x < 0) {
            this.x += Board.getWidth();
        }
        if(this.y>=Board.getHeight()) {
            this.y -= Board.getHeight();
        }
    }

    @Override
    public Animal fightLoser(Animal other) {
        Random rd = new Random();
        int chances = rd.nextInt(100) + 1;
        if(other.getFightType() == "bite") {
            if(chances<=1) {
                return other;
            } else {
                return this;
            }
        }  else {
            if(chances<=30) {
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
