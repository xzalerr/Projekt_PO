import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for(int i = 0; i < numberOfTurns; i++) {
            System.out.println("TURA NR: " + (i+1));
            for (Animal animal : species) {
                displayBoard(board);
                int x = animal.getX();
                int y = animal.getY();
                if (board.getAnimal(x, y) != null && board.getAnimal(x, y).getActive()) {
                    board.moveAnimal(x, y);
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
    public void endSimulation() {
        //metoda nie zaimplementowana, bedzie sluzyla do zakonczenia symulacji jesli pozostanie jeden gatunek zwierzecia na mapie
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
