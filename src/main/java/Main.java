import java.util.Random;

public class Main {
    /**
    w metodzie jest tworzony nowy obiekt Simalation i pozniej wykonywana jest symulacja na podstawie zadanych argumentow
    */
    public static void main(String[] args) {
        Simulation simulation = new Simulation(100);
        simulation.startSimulation(4, 4, 0, 1, 1, 0);
    }
}
