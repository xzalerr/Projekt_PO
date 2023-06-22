import java.util.Random;
/**
 Klasa lisa, dziedziczaca po klasie abstrakcyjnej Animal i rozszerzajaca ja o pole 'huntingRange' symbolizujace zasieg polowania i metode hunt.
 Dodatkowo sa zaimplementowane metody move oraz fightLoser w sposob charakterystyczny dla zwierzecia
 */
public class Fox extends Animal {
    /**
    Zakres ataku lisa
    */
    private int huntingRange;
    public Fox(int x, int y, int movementSpeed, String foodType, String fightType) {
        super(x, y, movementSpeed, foodType, fightType);
        this.huntingRange = 1;
        this.symbol = "F";
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
        int direction = genDirection(2);
        if(direction == 1) {
            this.x += movementSpeed;
            this.y +=movementSpeed;
        } else {
            this.x += movementSpeed;
            this.y -=movementSpeed;
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
        if(other.getFightType().equals("kick")) {
            if(chances<=35) {
                return other;
            } else {
                return this;
            }
        } else if(other.getFightType().equals("bite")){
            if(chances<=10) {
                return other;
            } else {
                return this;
            }
        } else {
            if(chances<=70) {
                return other;
            } else {
                return this;
            }
        }
    }
    /**
     zwraca tablice koordynatow, gdzie pozniej, w trakcie poruszania na planszy, sprawdzana jest obecnosc potencjalnych ofiar
    */
    public int[] hunt(int x, int y) {
        int[] coords = new int[8*this.huntingRange];
        int licznik = 0;
        for (int i=0;i<huntingRange;i++){
            coords[licznik] = x-i-1;
            coords[licznik+1] = y;
            licznik+=2;
        }
        for (int i=0;i<huntingRange;i++){
            coords[licznik] = x+i+1;
            coords[licznik+1] = y;
            licznik+=2;
        }
        for (int i=0;i<huntingRange;i++){
            coords[licznik] = x;
            coords[licznik+1] = y-i-1;
            licznik+=2;
        }
        for (int i=0;i<huntingRange;i++){
            coords[licznik] = x;
            coords[licznik+1] = y+i+1;
            licznik+=2;
        }
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
