import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 klasa ktora na podstawie zadanej liczby tur wykonuje symulacje
*/
public class Simulation {
    /**
    Obiekt klasy board, przy pomocy ktorego jest pozniej wykonywana symulacja
    */
    private Board board;
    /**
    liczba tur symulacji
    */
    private int numberOfTurns;
    public List<Animal> species = new ArrayList<Animal>();
    public Simulation(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }
    /**
    metoda startSimulation sluzaca do uruchomienia symulacji, w ktora uzytkownik moze podac parametry wejsciowe symulacji, takie jak ilosc zwierzat, czy rozmiar planszy
    */
    public void startSimulation(int width, int height, int wolf, int fox, int roedeer, int hare) {
        int area = height * width;
        int sum = wolf + fox + roedeer + hare;
        int min;
        if(width<height) {
            min = width;
        } else {
            min = height;
        }
        if(sum>area) {
            System.out.println("Więcej zwierząt niż, miejsc na planszy, symulacja się nie odbędzie.");
        }else if(min <= 3) {
            System.out.println("Za mała plansza, gdyż zwierzę przeszłoby całą plansze o takim rozmiarze w jednym ruchu.");
        }else {
            int[] position;
            int[][] occupied = new int[width][height];
            int x, y;
            for(int j = 0; j < height; j++) {
                for(int i = 0; i < width; i++) {
                    occupied[i][j] = 0;
                }
            }
            for(int i = 0; i < wolf; i++) {
                position = generateRandomFreeLocation(occupied, width, height);
                x = position[0];
                y = position[1];
                addAnimal(new Wolf(position[0], position[1], 2, "meat", "bite"));
                occupied[x][y] = 1;
            }
            for(int i = 0; i < fox; i++) {
                position = generateRandomFreeLocation(occupied, width, height);
                x = position[0];
                y = position[1];
                addAnimal(new Fox(position[0], position[1], 2, "meat", "scratch"));
                occupied[x][y] = 1;
            }
            for(int i = 0; i < roedeer; i++) {
                position = generateRandomFreeLocation(occupied, width, height);
                x = position[0];
                y = position[1];
                addAnimal(new RoeDeer(position[0], position[1], 3, "herbs", "kick"));
                occupied[x][y] = 1;
            }
            for(int i = 0; i < hare; i++) {
                position = generateRandomFreeLocation(occupied, width, height);
                x = position[0];
                y = position[1];
                addAnimal(new Hare(position[0], position[1], 1, "herbs", "punch"));
                occupied[x][y] = 1;
            }
            generateMap(width, height);
        }

    }
    /**
    metoda generateMap tworzy plansze, po ktorej przemieszczaja sie zwierzeta z wykorzystaniem obiektu klasy Board
    */
    public void generateMap(int width, int height) {
        Board board = new Board(width, height);
        for (Animal animal : species) {
            board.addAnimal(animal, animal.getX(), animal.getY());
        }
        System.out.println("Pozycje zwierząt przed rozpoczęciem:");
        displayBoard(board);
        for(int i = 0; i < numberOfTurns; i++) {
            System.out.println("TURA NR: " + (i+1));
            for (Animal animal : species) {
                int x = animal.getX();
                int y = animal.getY();
                if (board.getAnimal(x, y) != null && animal.getActive()) {
                    board.moveAnimal(x, y);
                    displayBoard(board);
                }
            }
            if(endSimulation(board)) {
                System.out.println("Symulacja zakończona, pozostał tylko jeden gatunek zwierząt.");
                break;
            }
        }
    }
    /**
    metoda displayBoard wyswietlajaca plansze
    */
    private void displayBoard(Board board) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Animal animal = board.getAnimal(x, y);
                if (animal != null) {
                    System.out.print(animal.symbol + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
    metoda sprawdzajaca czy symulacja powinna zostac zakonczona, czyli gdy pozostal jeden gatunek zwierzat na planszy to zwraca prawde - symulacja powinna sie zakonczyc
    */
    public boolean endSimulation(Board board) {
        int[] alive = {0, 0, 0, 0};
        int species = 0;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Animal animal = board.getAnimal(x, y);
                if(animal != null) {
                    if (animal.symbol == "W") {
                        alive[0]++;
                    } else if(animal.symbol == "F") {
                        alive[1]++;
                    } else if(animal.symbol == "R") {
                        alive[2]++;
                    } else if (animal.symbol == "H"){
                        alive[3]++;
                    }
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            if(alive[i] > 0) {
                species++;
            }
        }
        if(species == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
    metoda addAnimal dodaje zwierzeta do Listy species, która przechowuje zwierzęta biorącyce udzial w symulacji
    */
    public void addAnimal(Animal animal) {
        species.add(animal);
    }
    /**
    public int genLocation generuje losowe wspolrzedne, ktore potem sa wykorzysywane w metodzie generateRandomFreeLocation
    */
    public int[] genLocation(int width, int height) {
        int[] location = new int[2];
        Random rd = new Random();
        location[0] = rd.nextInt(width);
        location[1] = rd.nextInt(height);
        return location;
    }
    public boolean isFree(int[][] arr, int x, int y) {
        return arr[x][y] == 0;
    }
    /**
    generateRandomFreeLocation, wykorzystuje metode genLocation, i losuje wspolrzedne do skutku, do momentu az nie znajdzie wolnych wspolrzednych, gdzie mozna losowo umiescic zwierze
    */
    public int[] generateRandomFreeLocation(int[][] arr, int width, int height) {
        int[] location = genLocation(width, height);
        while (!isFree(arr, location[0], location[1])) {
            location = genLocation(width, height);
        }
        return location;
    }
}
