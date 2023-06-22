import java.util.Random;

/**
 Klasa abstrakcyjna Animal, po ktorej cechy takie jak: wpolrzedne, szybkosc poruszania, rodzaj spozywanego pokarmu,
 rodzaj walki czy symbol oznaczajacy dane zwierze, dziedzicza klasy konkretnych gatunkow zwierzat. Konstruktor Animal
 jest utworzony aby w klasach dziedziczacych nie nadpisywac kodu, poniewaz kazde ze zwierzat niezaleznie od gatunku posiada te atrybuty
 (w mysl zasady DRY) Istnieja rowniez funkcje zmieniajace oraz zwracajace dane atrybuty.
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
    okresla ile pol pokona zwierze w trakcie jednej tury
     */
    protected int movementSpeed;
    /**
    okresla rodzaj spozywanego pokarmu przez konkretne zwierzeta
    */
    protected String foodType;
    /**
    okresla rodzaj walki danego zwierzecia, ktory pozniej wplywa na szanse wygranej z innym zwierzeciem, walczacym w inny sposob
     */
    protected String fightType;
    /**
    pierwsza litera nazwy gatunku zwierzecia
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
     losuje liczbe z zadanego przedzialu <1, max></1,>, ktora pozniej okresli w ktora strone pojdzie zwierze
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
