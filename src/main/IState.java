package main;

public interface IState
{
    void activate();
    void processRequest(String request);
}
