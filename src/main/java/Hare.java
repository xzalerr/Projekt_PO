import java.util.Random;

public class Hare extends Animal {
    private int escapeRange;
    public Hare(int x, int y, int movementSpeed, String foodType, String fightType) {
        super(x, y, movementSpeed, foodType, fightType);
        this.escapeRange = 2;
        this.symbol = "H";
        this.active = true;
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
        int direction = genDirection(2);
        if(direction == 1) {
            this.x += movementSpeed;
            this.y -=movementSpeed;
        } else {
            this.x -= movementSpeed;
            this.y -=movementSpeed;
        }
        //sprawdzenie czy zwierze wyszlo za plansze i jesli tak to przeniesienie go na druga strone
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
        //zwraca przegranego w sposob losowy ale na podstawie okreslonych szans w zaleznosci od rodzaju ataku
        Random rd = new Random();
        int chances = rd.nextInt(100) + 1;
        if(other.getFightType().equals("bite")) {
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

    public int[] escape(int x, int y) {
        //tablica koordynatow, gdzie pozniej sprawdzana jest wolne pole do ucieczki
        //tutaj implementacja jest niepelna, bo beda sprawdzane wszystkie pola w zakresie escapeRange
        //implememtacja metody escape jest niekompletna, nalezy uwzglednic przypadek w ktorym zwierze nie znajdzie zadnego wolnego pola w swqoim zasiegu ucieczki
        //jednak przy malym natezeniu zwierzat, nie sprawia problemu
        int[] coords = new int[8];
        coords[0] = x-2;
        coords[1] = y;
        coords[2] = x+2;
        coords[3] = y;
        coords[4] = x;
        coords[5] = y+2;
        coords[6] = x;
        coords[7] = y-2;
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
