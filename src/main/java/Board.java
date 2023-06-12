public class Board {
    private static int height;
    private static int width;
    private Animal animals[][];
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.animals = new Animal[width][height];
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
        //rowniez metody polowania i ucieczki sa zaimplementowane w tej metodzie
        //przemieszczanie zwierzecia
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
        //jesli pole na ktore przemieszcza sie zwierze jest zajete
        if(isOccupied(toX, toY)) {;
            System.out.println("SPOTKANIE ZWIERZAT:");
            Animal opponent = getAnimal(toX, toY);
            //jesli roslinozerca spotka inne zwierze to zawsze pojdzie na pole obok, unikajac ataku
            //jesli spotkaja sie 2 zwierzeta tego samego gatunku, nawet miesozercy, to tez ida na pole obok unikajac ataku
            if(relocated.getFoodType().equals("herbs") || (relocated.symbol == opponent.symbol)){
                System.out.println(relocated.symbol + " spotkal: " + opponent.symbol + ", wiec nie zawalcza.");
                int[] moveNearby = findEmptyPosition(relocated);
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
                //jesli miesozerca spotka roslinozerce to atakuje go
                //jesli miesozerca wygra to przechodzi na miejsce w ktorym pokonal przeciwnika i roslinozerca jest usuwany z planszy
                //jesli roslinozerca wygra to miesozerca przechodzi na jego miejsce, a roslinozerca ucieka z uzyciem metody escape()
            } else if(relocated.getFoodType().equals("meat") && opponent.getFoodType().equals("herbs")) {
                System.out.println(relocated.symbol + " spotkal: " + opponent.symbol + ", wiec zawalcza.");
                Animal loser = relocated.fightLoser(opponent);
                if(loser == opponent) {
                    removeLoserAnimal(opponent);
                    animals[toX][toY] = relocated;
                    removeAnimal(fromX, fromY);
                    opponent.setActive(false);
                    System.out.println(relocated.symbol + " zwyciezyl!");
                } else if(loser == relocated) {
                    System.out.println(relocated.symbol + "(atakujacy) przegral z: " + opponent.symbol);
                    animals[toX][toY] = relocated;
                    //implememtacja metody escape jest niekompletna, nalezy uwzglednic przypadek w ktorym zwierze nie znajdzie zadnego wolnego pola w swqoim zasiegu ucieczki
                    //jednak przy malym natezeniu zwierzat, nie sprawia problemu
                    int[] esc;
                    if (opponent.symbol.equals("H")) {
                        esc = ((Hare) opponent).escape(opponent.getX(), opponent.getY());
                    } else {
                        esc = ((RoeDeer) opponent).escape(opponent.getX(), opponent.getY());
                    }
                    for (int j = 0; j < 4; j++) {
                        int escX = esc[j * 2];
                        int escY = esc[j * 2 + 1];
                        if ((escX < 0 || escX > Board.getWidth() - 1) || (escY < 0 || escY > Board.getWidth() - 1)) {
                            continue;
                        }
                        else {
                            if (!isOccupied(escX,escY)){
                                int newX = opponent.getX();
                                int newY = opponent.getY();
                                opponent.setX(escX);
                                opponent.setY(escY);
                                animals[escX][escY] = opponent;
                                removeAnimal(fromX,fromY);
                                System.out.println(opponent.symbol + " uciekł!");
                                break;
                            }
                        }
                    }
                }
                //jesli miesozerca spotka innego miesozerce to atakuje go
                //jesli atakujacy wygra to przechodzi na miejsce w ktorym pokonal przeciwnika i przeciwnik jest usuwany z planszy
                //jesli atakujacy przegra to jest usuwany z planszy i zwyciezca pozostaje na swoim miejscu
            } else if(relocated.getFoodType().equals("meat") && opponent.getFoodType().equals("meat")) {
                System.out.println(relocated.symbol + " spotkal: " + opponent.symbol + ", wiec zawalcza.");
                Animal loser = relocated.fightLoser(opponent);
                if(loser == opponent) {
                    removeLoserAnimal(opponent);
                    opponent.setActive(false);
                    animals[toX][toY] = relocated;
                    removeAnimal(fromX, fromY);
                    System.out.println(relocated.symbol + " zwyciezyl!");
                } else if(loser == relocated) {
                    System.out.println(relocated.symbol + " zaatakował i przegrał z: " + opponent.symbol);
                    removeAnimal(fromX, fromY);
                    relocated.setActive(false);
                }
            }
            //jesli zwierze niespotka nikogo
        } else {
            animals[toX][toY] = relocated;
            removeAnimal(fromX, fromY);
            boolean attack = false;
            //lis implementuje metode hunt i stara sie znalezc przeciwnika do ataku, jesli sie nie uda to konczy sie jego tura
            if (relocated.symbol.equals("F")){
                int[] arr = ((Fox)relocated).hunt(relocated.getX(), relocated.getY());
                for (int i = 0; i < 4; i++) {
                    int huntX = arr[i*2];
                    int huntY = arr[i*2+1];
                    if ((huntX < 0 || huntX > Board.getWidth() - 1) || (huntY < 0 || huntY > Board.getHeight() - 1)) {
                        continue;
                    }
                    else {
                        if (isOccupied(huntX,huntY) && !attack) {
                            attack = true;
                            System.out.println("F zapolowal:");
                            Animal opponent = getAnimal(huntX, huntY);
                            System.out.println(relocated.symbol + " zaatakowal: " + opponent.symbol);
                            if(opponent.getFoodType().equals("herbs")) {
                                Animal loser = relocated.fightLoser(opponent);
                                if(loser == opponent) {
                                    removeLoserAnimal(opponent);
                                    animals[huntX][huntY] = relocated;
                                    relocated.setX(huntX);
                                    relocated.setY(huntY);
                                    removeAnimal(toX, toY);
                                    System.out.println("Przegral: " + opponent.symbol);
                                    opponent.setActive(false);
                                } else if(loser == relocated) {
                                    System.out.println("Ucieczka. Przegral: " + relocated.symbol);
                                    //implememtacja metody escape jest niekompletna, nalezy uwzglednic przypadek w ktorym zwierze nie znajdzie zadnego wolnego pola w swqoim zasiegu ucieczki
                                    //jednak przy malym natezeniu zwierzat, nie sprawia problemu
                                    int[] esc;
                                    if (opponent.symbol.equals("H")) {
                                        esc = ((Hare) opponent).escape(opponent.getX(), opponent.getY());
                                    } else {
                                        esc = ((RoeDeer) opponent).escape(opponent.getX(), opponent.getY());
                                    }
                                    for (int j = 0; j < 4; j++) {
                                        int escX = esc[j * 2];
                                        int escY = esc[j * 2 + 1];
                                        if ((escX < 0 || escX > Board.getWidth() - 1) || (escY < 0 || escY > Board.getHeight() - 1)) {
                                            continue;
                                        }
                                        else {
                                            if (!isOccupied(escX,escY)){
                                                int newX = opponent.getX();
                                                int newY = opponent.getY();
                                                opponent.setX(escX);
                                                opponent.setY(escY);
                                                animals[escX][escY] = opponent;
                                                removeAnimal(newX,newY);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (opponent.getFoodType().equals("meat") && (relocated.symbol != opponent.symbol)) {
                                Animal loser = relocated.fightLoser(opponent);
                                if (loser == opponent) {
                                    removeLoserAnimal(opponent);
                                    animals[huntX][huntY] = relocated;
                                    relocated.setX(huntX);
                                    relocated.setY(huntY);
                                    removeAnimal(toX, toY);
                                    opponent.setActive(false);
                                    System.out.println("Przegral: " + opponent.symbol);
                                } else if (loser == relocated) {
                                    removeLoserAnimal(relocated);
                                    relocated.setActive(false);
                                    System.out.println("Przegral: " + relocated.symbol);
                                }
                            }
                        }
                    }
                }
            }
            //wilk implementuje metode hunt i stara sie znalezc przeciwnika do ataku, jesli sie nie uda to konczy sie jego tura
            if (relocated.symbol.equals("W")) {
                int[] arr = ((Wolf) relocated).hunt(relocated.getX(), relocated.getY());
                for (int i = 0; i < 8; i++) {
                    int huntX = arr[i * 2];
                    int huntY = arr[i * 2 + 1];
                    if ((huntX < 0 || huntX > Board.getWidth() - 1) || (huntY < 0 || huntY > Board.getHeight() - 1)) {
                        continue;
                    } else {
                        if (isOccupied(huntX, huntY) && !attack) {
                            attack = true;
                            System.out.println("W zapolowal: ");
                            Animal opponent = getAnimal(huntX, huntY);
                            System.out.println(relocated.symbol+" zaatakowal: "+opponent.symbol);
                            if (opponent.getFoodType().equals("herbs")) {
                                Animal loser = relocated.fightLoser(opponent);
                                if (loser == opponent) {
                                    removeLoserAnimal(opponent);
                                    animals[huntX][huntY] = relocated;
                                    relocated.setX(huntX);
                                    relocated.setY(huntY);
                                    removeAnimal(toX, toY);
                                    opponent.setActive(false);
                                    System.out.println("Przegral: " + opponent.symbol);
                                } else if (loser == relocated) {
                                    System.out.println("Ucieczka. Przegral: " + relocated.symbol);
                                    //implememtacja metody escape jest niekompletna, nalezy uwzglednic przypadek w ktorym zwierze nie znajdzie zadnego wolnego pola w swqoim zasiegu ucieczki
                                    //jednak przy malym natezeniu zwierzat, nie sprawia problemu
                                    int[] esc;
                                    if (opponent.symbol.equals("H")) {
                                        esc = ((Hare) opponent).escape(opponent.getX(), opponent.getY());
                                    } else {
                                        esc = ((RoeDeer) opponent).escape(opponent.getX(), opponent.getY());
                                    }
                                    for (int j = 0; j < 4; j++) {
                                        int escX = esc[j * 2];
                                        int escY = esc[j * 2 + 1];
                                        if ((escX < 0 || escX > Board.getWidth() - 1) || (escY < 0 || escY > Board.getHeight() - 1)) {
                                            continue;
                                        }
                                        else {
                                            if (!isOccupied(escX,escY)){
                                                int newX = opponent.getX();
                                                int newY = opponent.getY();
                                                opponent.setX(escX);
                                                opponent.setY(escY);
                                                animals[escX][escY] = opponent;
                                                removeAnimal(newX,newY);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (opponent.getFoodType().equals("meat") && (relocated.symbol != opponent.symbol)) {
                                Animal loser = relocated.fightLoser(opponent);
                                if (loser == opponent) {
                                    removeLoserAnimal(opponent);
                                    animals[huntX][huntY] = relocated;
                                    relocated.setX(huntX);
                                    relocated.setY(huntY);
                                    removeAnimal(toX, toY);
                                    opponent.setActive(false);
                                    System.out.println("Przegral: " + opponent.symbol);
                                } else if (loser == relocated) {
                                    removeLoserAnimal(relocated);
                                    relocated.setActive(false);
                                    System.out.println("Przegral: " + relocated.symbol);
                                }
                            } else {
                                System.out.println("Zrezygnował z polowania bo zobaczyl ze to ten sam gatunek.");
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean isOccupied(int x, int y) {
        //metoda sprawdza czy na danym polu planszy znajduje sie inne zwierze niz to ktore sie tam przemiescilo
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
