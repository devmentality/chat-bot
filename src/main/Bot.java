package main;

import main.Data.IAppRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.States.BeforeStartState;

public class Bot implements IStateMachine
{
    private IMessageReader reader;
    private IMessageWriter writer;
    private IAppRepository repository;
    private String userName;
    private IState state;
    private boolean isTerminated;

    public Bot(IMessageReader reader, IMessageWriter writer, IAppRepository repository)
    {
        this.reader = reader;
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

    public void execute()
    {
        state.activate();
        while(!isTerminated)
        {
            String request = reader.read();
            state.processRequest(request);
        }
    }
}
