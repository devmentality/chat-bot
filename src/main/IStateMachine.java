package main;

import main.States.IState;

public interface IStateMachine
{
    IState getCurrentState();
    void changeState(IState nextState);
}
