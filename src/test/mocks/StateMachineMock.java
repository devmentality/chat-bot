package test.mocks;

import main.IState;
import main.IStateMachine;

public class StateMachineMock implements IStateMachine
{
    private IState currentState;
    private boolean isTerminated = false;

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

    @Override
    public void signalToTerminate()
    {
        isTerminated = true;
    }

    public boolean isTerminated()
    {
        return isTerminated;
    }
}
