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
    public void removeLoserAnimal(Animal loser) {
        //metoda usuwa zwierze z miejsca na planszy po przegranej walce
        animals[loser.getX()][loser.getY()] = null;
    }
    public void moveAnimal(int fromX, int fromY) {
        //metoda porusza zwierze z punktu a do b, w zaleznosci od metody move() danego gatunku, jesli zwierzeta sie spotkaja to walcza, badz roslinozercy odchodza na inne pole niz inny roslinozerca
        Animal relocated = getAnimal(fromX, fromY);
        relocated.move(fromX, fromY);
        int toX = relocated.getX();
        int toY = relocated.getY();
        if(toX>=Board.getWidth()) {
            toX = toX % Board.getWidth();
        } else if(toX < 0) {
            toX += Board.getWidth();
        }
        if(toY>=Board.getHeight()) {
            toY = toY % Board.getHeight();
        } else if(toY < 0) {
            toY += Board.getHeight();
        }
        if(isOccupied(toX, toY)) {;
            System.out.println("KOLIZJA");
            Animal opponent = getAnimal(toX, toY);
            if(relocated.getFoodType().equals("herbs") && opponent.getFoodType().equals("herbs")) {
                System.out.println("ROSLINO");
                System.out.println("find blad");
                int[] moveNearby = findEmptyPosition(relocated);
                System.out.println("find dziala");
                if(moveNearby != null) {
                    toX = moveNearby[0];
                    toY = moveNearby[1];
                    relocated.setX(moveNearby[0]);
                    relocated.setY(moveNearby[1]);
                    animals[toX][toY] = relocated;
                    removeAnimal(fromX, fromY);
                } else {
                    return;
                }
            } else if(relocated.getFoodType().equals("meat") && opponent.getFoodType().equals("herbs")) {
                System.out.println("MIESO/ROSLINO");
                Animal loser = relocated.fightLoser(opponent);
                if(loser == opponent) {
                    removeLoserAnimal(opponent);
                    animals[toX][toY] = relocated;
                    removeAnimal(fromX, fromY);
                } else if(loser == relocated) {
                    removeLoserAnimal(relocated);
                }
            } else if(relocated.getFoodType().equals("meat") && opponent.getFoodType().equals("meat")) {
                System.out.println("MIESO");
                Animal loser = relocated.fightLoser(opponent);
                if(loser == opponent) {
                    removeLoserAnimal(opponent);
                    animals[toX][toY] = relocated;
                    removeAnimal(fromX, fromY);
                } else if(loser == relocated) {
                    removeLoserAnimal(relocated);
                }
            }
        } else {
            animals[toX][toY] = relocated;
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
    //MUSI BRAC ZWIERZE JAKO PARAMETR ZAMIAST WSPOLRZEDNYCH I ZMIENIACH MU WARTOSCI X I Y
    public int[] findEmptyPosition(Animal animal) {
        //metoda sprawdzajaca wszystkie wolne pola w okol pola o zadancyh wspolrzednych, jesli uda sie znalezc to zwraca jego wspolrzedne
        int newX, newY;
        int[] emptyPosition = new int[2];
        emptyPosition[0] = animal.getX();
        emptyPosition[1] = animal.getY();

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <=1; j++) {
                if( i == 0 && j ==0) {
                    continue;
                }
                newX = emptyPosition[0] + i;
                newY =  emptyPosition[1] + j;
                if(newX>=Board.getWidth()) {
                    newX -= Board.getWidth();
                } else if(newX < 0) {
                    newX += Board.getWidth();
                }
                if(newY>=Board.getHeight()) {
                    newY -= Board.getHeight();
                } else if(newY < 0) {
                    newY += Board.getHeight();
                }
                if(!isOccupied(newX, newY)) {
                    emptyPosition[0] = newX;
                    emptyPosition[1] = newY;
                    animal.setX(newX);
                    animal.setY(newY);
                    return emptyPosition;
                }
            }
        }
       return null;
    }
}
