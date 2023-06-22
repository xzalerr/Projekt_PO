import java.util.Random;

public class Main {
    /**
    w metodzie main jest tworzony nowy obiekt typu Simalation i pozniej wykonywana jest symulacja na podstawie zadanych argumentow
    */
    public static void main(String[] args) {
        Simulation simulation = new Simulation(100);
        simulation.startSimulation(4, 4, 0, 1, 1, 0);
    }
}
