package main;

import main.Data.IAppRepository;
import main.States.IState;
import main.States.InitializedState;
import java.util.ArrayList;

public class Bot implements IStateMachine
{
    private IAppRepository repository;
    private IState state;
    private Session session;

    public Bot(IAppRepository repository, Session session)
    {
        this.repository = repository;
        this.session = session;
        state = new InitializedState(this, repository, session);
    }

    public void changeState(IState nextState)
    {
        state = nextState;
    }

    public IState getCurrentState()
    {
        return state;
    }

    public ArrayList<String> processRequest(String message)
    {
        return state.processRequest(message);
    }

    public Session getSession()
    {
        return session;
    }
}
