public class Board {
    private static int height;
    private static int width;
    private Animal animals[][];
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.animals = new Animal[height][width];
    }
    public void addAnimal(Animal animal, int x, int y) {
        //metoda dodaje zwierze w konkretne miejsce na planszy
        animals[x][y] = animal;
    }
    public void removeAnimal(int x, int y) {
        //metoda usuwa zwierze z miejsca na planszy po przegranej walce
        animals[x][y] = null;
    }
    public void moveAnimal(int fromX, int fromY) {
        //metoda porusza zwierze z punktu a do b, w zaleznosci od metody move() danego gatunku
        Animal relocated = animals[fromX][fromY];
        relocated.move(fromX, fromY);
        int toX = relocated.getX();
        int toY = relocated.getY();
        if(toX < width && toY < height) {
            animals[toX][toY] = animals[fromX][fromY];
            removeAnimal(fromX, fromY);
        } else if(toX >= width && toY < height) {
            animals[toX-width][toY] = animals[fromX][fromY];
            removeAnimal(fromX, fromY);
        } else if(toX < width && toY >= height) {
            animals[toX][toY-height] = animals[fromX][fromY];
            removeAnimal(fromX, fromY);
        } else {
            animals[toX-width][toY-height] = animals[fromX][fromY];
            removeAnimal(fromX, fromY);
        }
    }
    public boolean isOccupied(int x, int y) {
        //metoda sprawdza czy na danym polu planszy znajduje sie inne zwierze niz to ktore sie tam przemiescilo
        //jesli znajduje sie dwoch roslinozercow to zwierze idzie w inne miejsce
        //jesli znajduje sie dwoch miesozercow albo miesozerca i roslinozerca to walcza
        return animals[x][y] != null;
    }
    public Animal getAnimal(int x, int y) {
        //metoda sprawdza jakie zwierze znajduje sie na danym polu
        return animals[x][y];
    }
    public static int getHeight() {
        return height;
    }
    public static int getWidth() {
        return width;
    }
}
