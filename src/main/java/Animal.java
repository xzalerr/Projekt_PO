import java.util.Random;

/**
 Klasa abstrakcyjna Animal, po ktorej dziedzicza klasy konkretnych gatunkow zwierzat
 */
public abstract class Animal implements Existence {
    /**
     Wspolrzedna X zwierzecia
     */
    protected int x;
    /**
     Wspolrzedna Y zwierzecia
     */
    protected int y;
    /**
    movementSpeed okresla ile pol pokona zwierze w trakcie jednej tury
     */
    protected int movementSpeed;
    /**
    foodType okresla rodzaj spozywanego pokarmu przez konkretne zwierzeta
    */
    protected String foodType;
    /**
    fightType okresla rodzaj walki danego zwierzecia, ktory pozniej wplywa na szanse wygranej z innym zwierzeciem, walczacym w inny sposob
     */
    protected String fightType;
    /**
    symbol to pierwsza litera nazwy gatunku zwierzecia
    */
    protected String symbol;
    /**
    okresla czy zwierze jest aktywne w trakcie trwania symulacji, czyli czy dalej zyje i ma sie poruszac po planszy
    */
    protected boolean active;
    public Animal(int x, int y, int movementSpeed, String foodType, String fightType) {
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.foodType = foodType;
        this.fightType = fightType;
        this.active = true;
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
    genDirection losuje liczbe z zadanego przedzialu <0, max), ktora pozniej okresli w ktora strone pojdzie zwierze
    */
    public int genDirection(int max) {
        Random rd = new Random();
        int direction = rd.nextInt(max) + 1;
        return direction;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getFightType() {
        return fightType;
    }
    public String getFoodType() {
        return foodType;
    }

    @Override
    public abstract void move(int x, int y);
    @Override
    public abstract Animal fightLoser(Animal other);
}
