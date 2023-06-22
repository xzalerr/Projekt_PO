import java.util.Random;
/**
 Klasa sarny, dziedziczaca po klasie abstrakcyjnej Animal i rozszerzajaca ja o pole 'escapeRange' symbolizujace zasieg ucieczki i metode escape.
 Dodatkowo sa zaimplementowane metody move oraz fightLoser w sposob charakterystyczny dla zwierzecia
 */
public class RoeDeer  extends Animal {
    /**
     Zakres ucieczki sarny
     */
    private int escapeRange;
    public RoeDeer(int x, int y, int movementSpeed, String foodType, String fightType) {
        super(x, y, movementSpeed, foodType, fightType);
        this.escapeRange = 3;
        this.symbol = "R";
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
     metoda wykorzystuje metode genDirection, i na podstawie wylosowanej liczby decyduje, w jakim kierunku przemiesci sie zwierze oraz sprawdze czy zwierze
     nie wyszlo poza plansze, a gdy tak sie stanie, to przenosi je na drugi koniec planszy.
     */
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
    /**
     zwraca przegranego w sposob losowy ale na podstawie okreslonych szans w zaleznosci od rodzaju ataku
    */
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
    /**
     metoda zwraca tablice koordynatów, prezentującą gdzie zwierzę ma możliwe pola do ucieczki w zależności od jego zasięgu uczieczki
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
