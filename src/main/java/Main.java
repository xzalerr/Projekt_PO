public class Main {
    public static void main(String[] args) {
        Board board = new Board(5, 5);

        Animal wolf = new Wolf(3, 4, 2, "meat", "bite", 2);
        Animal roe = new RoeDeer(4, 2, 3, "herbs", "kick", 3);
        Animal fox = new Fox(1, 1, 1, "meat", "scratch", 2);
        Animal hare = new Hare(1, 3, 2, "herbs", "punch", 1);
        board.addAnimal(wolf, wolf.getX(), wolf.getY());
        board.addAnimal(roe, roe.getX(), roe.getY());
        board.addAnimal(fox, fox.getX(), fox.getY());
        board.addAnimal(hare, hare.getX(), hare.getY());

        for (int i = 0; i < 30; i++) {
            displayBoard(board);
            // Przesunięcie zwierząt
            if (board.getAnimal(wolf.getX(), wolf.getY()) != null) {
                board.moveAnimal(wolf.getX(), wolf.getY());
            }
            if (board.getAnimal(roe.getX(), roe.getY()) != null) {
                board.moveAnimal(roe.getX(), roe.getY());
            }
            if (board.getAnimal(fox.getX(), fox.getY()) != null) {
                board.moveAnimal(fox.getX(), fox.getY());
            }
            if (board.getAnimal(hare.getX(), hare.getY()) != null) {
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