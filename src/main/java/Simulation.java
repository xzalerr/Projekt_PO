import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private Board board;
    private int numberOfTurns;
    public List<Animal> species = new ArrayList<Animal>();
    public void startSimulation(int numberOfTurns, List species) {
        //rozpoczyna symulacje z danymi w liscie species zwierzetami, tworzy Board i dodaje zwierzeta do tablicy dwuwymiarowej animals z klasy Board
        //symulacja trwa i w mumencie gdy liczba tur symulacji == numberOfTurns symulacja sie konczy za pomoca metody endSimulation()
    }
    public void endSimulation() {
        //implementacja
    }
    public void addAnimal(Animal animal) {
        //dodaje zwierzeta do Listy species, aby braly udzial w symulacji
    }
}
