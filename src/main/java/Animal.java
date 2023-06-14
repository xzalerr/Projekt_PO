import java.util.Random;

public abstract class Animal implements Existence {
    //x i y to współrzędne zwierząt
    protected int x;
    protected int y;
    //movementSpeed określa ile pól pokona zwierzę w trakcie jednej tury
    protected int movementSpeed;
    protected String foodType;
    //fightType określa rodzaj walki danego zwierzęcia, który później wpływa na szansę wygranej z innym zwierzęciem, walczącym w inny sposób
    protected String fightType;
    //symbol to pierwsza litera nazwy gatunku zwierzęcia
    protected String symbol;
    //określa czy zwierzę jest aktywne w trakcie trwania symulacji, czyli czy dalej zyje i ma sie poruszac po planszy
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
    //genDirection losuje liczbe z zadanego przedzialu <0, max), ktora pozniej okresli w ktora strone pojdzie zwierze
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
