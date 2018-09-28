package main;

public interface IController
{
    boolean processRequest(String request);
    void changeState(IControllerState state);
}
