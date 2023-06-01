import java.util.Random;

public class Wolf extends Animal {
    private int huntingRange;
    public Wolf(int x, int y, int movementSpeed, String foodType, String fightType) {
        super(x, y, movementSpeed, foodType, fightType);
        this.huntingRange = 2;
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
        if(other.getFightType().equals("kick")) {
            if(chances<=1) {
                return other;
            } else {
                return this;
            }
        } else if(other.getFightType().equals("scratch")){
            if(chances<=1) {
                return other;
            } else {
                return this;
            }
        } else {
            if(chances<=1) {
                return other;
            } else {
                return this;
            }
        }
    }

    public int[] hunt(int x, int y) {
        int[] coords = new int[16];
        coords[0] = x-1;
        coords[1] = y;
        coords[2] = x-2;
        coords[3] = y;
        coords[4] = x+1;
        coords[5] = y;
        coords[6] = x+2;
        coords[7] = y;
        coords[8] = x;
        coords[9] = y-1;
        coords[10] = x;
        coords[11] = y-2;
        coords[12] = x;
        coords[13] = y+1;
        coords[14] = x;
        coords[15] = y+2;
        return coords;
    }
    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }

    @Override
    public boolean getActive() {
        return super.getActive();
    }
}
