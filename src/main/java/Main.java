import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);

        Animal wolf = new Wolf(2, 2, 2, "meat", "bite");
        Animal roe = new RoeDeer(0, 0, 3, "herbs", "kick", 3);
        Animal fox = new Fox(4, 4, 1, "meat", "scratch");
        Animal hare = new Hare(0, 4, 2, "herbs", "punch", 1);
        board.addAnimal(wolf, wolf.getX(), wolf.getY());
        board.addAnimal(roe, roe.getX(), roe.getY());
        board.addAnimal(fox, fox.getX(), fox.getY());
        board.addAnimal(hare, hare.getX(), hare.getY());

        for (int i = 0; i < 10; i++) {
            displayBoard(board);
            // Przesunięcie zwierząt
            if (board.getAnimal(wolf.getX(), wolf.getY()) != null && board.getAnimal(wolf.getX(), wolf.getY()).getActive()) {
                board.moveAnimal(wolf.getX(), wolf.getY());
            }
            displayBoard(board);
            if (board.getAnimal(roe.getX(), roe.getY()) != null && board.getAnimal(roe.getX(), roe.getY()).getActive()) {
                board.moveAnimal(roe.getX(), roe.getY());
            }
            displayBoard(board);
            if (board.getAnimal(fox.getX(), fox.getY()) != null && board.getAnimal(fox.getX(), fox.getY()).getActive()) {
                board.moveAnimal(fox.getX(), fox.getY());
            }
            displayBoard(board);
            if (board.getAnimal(hare.getX(), hare.getY()) != null && board.getAnimal(hare.getX(), hare.getY()).getActive()) {
                board.moveAnimal(hare.getX(), hare.getY());
            }

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