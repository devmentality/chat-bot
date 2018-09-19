package main;

public interface IState
{
    void setContext(Bot bot);
    String initialize();
    String respond(String request);
}
