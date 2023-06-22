/**
Interfejs zawierajacy cechy niezbedne do zycia dla kazdego zwierzecia, takie jak poruszanie czy wylonienie przegranego z walki
*/
public interface Existence {
    void move(int x, int y);
    Animal fightLoser(Animal other);
}
