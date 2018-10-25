package test.mocks;

import main.IStateMachine;
import main.States.IState;

public class StateMachineMock implements IStateMachine
{
    private IState currentState;

    @Override
    public IState getCurrentState()
    {
        return currentState;
    }

    @Override
    public void changeState(IState nextState)
    {
        currentState = nextState;
    }
}
