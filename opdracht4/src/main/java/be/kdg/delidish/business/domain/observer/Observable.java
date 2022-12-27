package be.kdg.delidish.business.domain.observer;

//Publisher
public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
