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
    public void fight() {
        //implementacja
    }

    public void hunt() {
        //implementacja
    }
}
