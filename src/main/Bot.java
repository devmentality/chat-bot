package main;

import main.Data.INewRepository;
import main.Data.User;
import main.States.IState;
import main.States.InitializedState;
import java.util.ArrayList;

public class Bot implements IStateMachine
{
    private INewRepository repository;
    private IState state;

    public Bot(INewRepository repository)
    {
        this.repository = repository;
        state = new InitializedState(this, repository);
    }

    public void changeState(IState nextState)
    {
        state = nextState;
    }

    public IState getCurrentState()
    {
        return state;
    }

    public ArrayList<String> processRequest(User user, String message)
    {
        return state.processRequest(user, message);
    }
}
