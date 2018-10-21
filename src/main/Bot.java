package main;

import main.Data.IAppRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.States.BeforeStartState;

import java.util.ArrayList;

public class Bot implements IStateMachine
{
    private IAppRepository repository;
    private IState state;
    private boolean isTerminated;

    public Bot(IAppRepository repository)
    {
        this.repository = repository;
        state = new BeforeStartState(this, repository);
        isTerminated = false;
    }

    public void changeState(IState nextState)
    {
        state = nextState;
    }

    public IState getCurrentState()
    {
        return state;
    }

    public void signalToTerminate()
    {
        isTerminated = true;
    }

    @Override
    public boolean isTerminated() {
        return isTerminated;
    }

    public ArrayList<String> processRequest(String message)
    {
        return state.processRequest(message);
    }
}
