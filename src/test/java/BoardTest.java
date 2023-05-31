import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void moveAnimalTest() {
        Board board = new Board(10, 10);
        Animal wolf = new Wolf(0, 0, 2, "meat", "bite");
        Animal roeDeer = new RoeDeer(5, 5, 3, "herbs", "kick", 3);
        board.addAnimal(wolf, wolf.getX(), wolf.getY());
        board.addAnimal(roeDeer, roeDeer.getX(), roeDeer.getY());
        board.moveAnimal(0, 0);
        assertEquals(null, board.getAnimal(0, 0));
        assertEquals(wolf, board.getAnimal(wolf.getX(), wolf.getY()));
        board.removeAnimal(wolf.getX(), wolf.getY());
        board.moveAnimal(5, 5);
        assertEquals(null, board.getAnimal(5, 5));
        assertEquals(roeDeer, board.getAnimal(roeDeer.getX(), roeDeer.getY()));
    }
}