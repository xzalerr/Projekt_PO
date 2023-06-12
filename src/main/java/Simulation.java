import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private Board board;
    private int numberOfTurns;
    public List<Animal> species = new ArrayList<Animal>();
    public Simulation(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }
    public void startSimulation(int width, int height, int wolf, int fox, int roedeer, int hare) {
        //metoda sluzaca do uruchomienia symulacji, w ktora uzytkownik moze podac parametry wejsciowe symulacji
        int[] position;
        int[][] occupied = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                occupied[x][y] = 0;
            }
        }
        for(int i = 0; i < wolf; i++) {
            position = generateRandomFreeLocation(occupied, width, height);
            addAnimal(new Wolf(position[0], position[1], 2, "meat", "bite"));
        }
        for(int i = 0; i < fox; i++) {
            position = generateRandomFreeLocation(occupied, width, height);
            addAnimal(new Fox(position[0], position[1], 2, "meat", "scratch"));
        }
        for(int i = 0; i < roedeer; i++) {
            position = generateRandomFreeLocation(occupied, width, height);
            addAnimal(new RoeDeer(position[0], position[1], 3, "herbs", "kick"));
        }
        for(int i = 0; i < hare; i++) {
            position = generateRandomFreeLocation(occupied, width, height);
            addAnimal(new Hare(position[0], position[1], 1, "herbs", "punch"));
        }
        generateMap(width, height);
    }
    public void generateMap(int width, int height) {
        //metoda generujaca mape po ktorej przemieszczaja sie zwierzeta
        Board board = new Board(width, height);
        for (Animal animal : species) {
            board.addAnimal(animal, animal.getX(), animal.getY());
        }
        System.out.println("Pozycje zwierząt przed rozpoczęciem:");
        displayBoard(board);
        for(int i = 0; i < numberOfTurns; i++) {
            if(endSimulation(board)) {
                System.out.println("Symulacja zakończona, pozostał tylko jeden gatunek zwierząt.");
                break;
            }
            System.out.println("TURA NR: " + (i+1));
            for (Animal animal : species) {
                int x = animal.getX();
                int y = animal.getY();
                if (board.getAnimal(x, y) != null && animal.getActive()) {
                    board.moveAnimal(x, y);
                    displayBoard(board);
                }
            }
        }
    }
    private void displayBoard(Board board) {
        //metoda wyswietlajaca mape
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
    public void addAnimal(Animal animal) {
        species.add(animal);
    }
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
    public int[] generateRandomFreeLocation(int[][] arr, int width, int height) {
        int[] location = genLocation(width, height);
        while (!isFree(arr, location[0], location[1])) {
            location = genLocation(width, height);
        }
        return location;
    }
}
