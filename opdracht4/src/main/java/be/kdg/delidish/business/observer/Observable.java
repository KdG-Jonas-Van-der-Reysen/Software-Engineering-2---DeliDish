package be.kdg.delidish.business.observer;

//Publisher
public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
