import java.util.Random;

public class Wolf extends Animal {
    private int huntingRange;
    public Wolf(int x, int y, int movementSpeed, String foodType, String fightType, int huntingRange) {
        super(x, y, movementSpeed, foodType, fightType);
        this.huntingRange = huntingRange;
        this.symbol = "W";
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
       int direction = genDirection(4);
       if(direction == 1) {
           this.x += movementSpeed;
           this.y +=movementSpeed;
       } else if(direction==2) {
           this.x += movementSpeed;
           this.y -=movementSpeed;
       } else if(direction==3) {
           this.x -= movementSpeed;
           this.y -=movementSpeed;
       } else {
           this.x -= movementSpeed;
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
        if(other.getFightType() == "kick") {
            if(chances<=75) {
                return other;
            } else {
                return this;
            }
        } else if(other.getFightType() == "scratch"){
            if(chances<=90) {
                return other;
            } else {
                return this;
            }
        } else {
            if(chances<=99) {
                return other;
            } else {
                return this;
            }
        }
    }

    public void hunt() {
        //implementacja
    }
}
