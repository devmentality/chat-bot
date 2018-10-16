package main;

public interface IStateMachine
{
    IState getCurrentState();
    void changeState(IState nextState);
    void signalToTerminate();
    boolean isTerminated();
}
