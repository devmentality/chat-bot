package main;

import main.Data.IAppRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.Resources.Strings;
import main.States.BeforeStartState;
import main.States.InitializedState;

import java.util.ArrayList;
import java.util.Arrays;

public class Bot implements IStateMachine
{
    private IAppRepository repository;
    private IState state;
    private boolean isTerminated;
    private Session session;

    public Bot(IAppRepository repository, Session session)
    {
        this.repository = repository;
        this.session = session;
        state = new InitializedState(this, repository, session);
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

    public ArrayList<String> introduce()
    {
        return new ArrayList<>(Arrays.asList(Strings.introduction));
    }

    public Session getSession()
    {
        return session;
    }
}
