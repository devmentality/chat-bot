package main.States;

import main.Data.IAppRepository;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;
import java.util.ArrayList;

public class StartedState extends StateBase
{
    public StartedState(IStateMachine stateMachine, IAppRepository repository)
    {
        super(stateMachine, repository);
    }

    @Override
    public ArrayList<String> processRequest(String name)
    {
        Session session = new Session(name);
        ArrayList<String> output = new ArrayList<>();
        if (repository.hasUser(name))
            output.add(String.format(Strings.greetingOldUser, name));
        else
        {
            output.add(String.format(Strings.greetingNewUser, name));
            repository.addUser(name);
        }
        output.add(Strings.introduction);
        stateMachine.changeState(new InitializedState(stateMachine, repository, session));
        return output;
    }
}
