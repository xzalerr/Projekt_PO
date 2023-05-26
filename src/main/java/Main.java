import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Utworzenie obiektu planszy
        Board board = new Board(10, 10); // przykładowe wymiary planszy

        // Dodanie zwierząt do planszy
        Animal wolf = new Wolf(3, 4, 2, "meat", "bite", 2); // przykładowe współrzędne wilka
        Animal lion = new RoeDeer(7, 2, 2, "meat", "bite", 2); // przykładowe współrzędne lwa
        board.addAnimal(wolf, wolf.getX(), wolf.getY());
        board.addAnimal(lion, lion.getX(), lion.getY());

        // Wyświetlenie stanu planszy przez 5 iteracji
        for (int i = 0; i < 5; i++) {
            displayBoard(board);
            // Przesunięcie zwierząt
            board.moveAnimal(wolf.getX(), wolf.getY()); // przykładowe przesunięcie wilka
            board.moveAnimal(lion.getX(), lion.getY()); // przykładowe przesunięcie lwa
        }
    }

    private static void displayBoard(Board board) {
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
}
