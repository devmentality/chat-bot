package main;

import main.Data.IAppRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.States.BeforeStartState;

public class Bot implements IStateMachine
{
    private IMessageWriter writer;
    private IAppRepository repository;
    private IState state;
    private boolean isTerminated;

    public Bot(IMessageWriter writer, IAppRepository repository)
    {
        this.writer = writer;
        this.repository = repository;
        state = new BeforeStartState(this, repository, writer);
        isTerminated = false;
    }

    public void changeState(IState nextState)
    {
        state = nextState;
        state.activate();
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

    public void processRequest(String message)
    {
        state.processRequest(message);
    }
}
