import java.util.Random;
/**
Klasa zajaca, dziedziczaca po klasie abstrakcyjnej Animal
*/
public class Hare extends Animal {
    /**
     Zakres ucieczki zajaca
     */
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

    /**
     metoda move wykorzystuje metode genDirection, i na podstawie wylosowanej liczby decyduje, w jakim kierunku przemiesci sie zwierze
     */
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
    /**
     fightLoser zwraca przegranego w sposob losowy ale na podstawie okreslonych szans w zaleznosci od rodzaju ataku
     */
    @Override
    public Animal fightLoser(Animal other) {
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
    /**
    metoda escape zwraca tablice koordynatów, prezentującą gdzie zwierzę ma możliwe pola do ucieczki w zależności od jego zasięgu uczieczki
    */
    public int[] escape(int x, int y) {
        int[] coords = new int[8];
        coords[0] = x-this.escapeRange;
        coords[1] = y;
        coords[2] = x+this.escapeRange;
        coords[3] = y;
        coords[4] = x;
        coords[5] = y+this.escapeRange;
        coords[6] = x;
        coords[7] = y-this.escapeRange;
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
