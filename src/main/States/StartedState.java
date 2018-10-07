package main.States;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

public class StartedState extends StateBase
{
    public StartedState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
    }

    @Override
    public void activate()
    {
        writer.write(Strings.nameRequest);
    }

    @Override
    public void processRequest(String request)
    {
        String name = request;
        Session session = new Session(name);
        if (repository.hasUser(name))
            writer.write(String.format(Strings.greetingOldUser, name));
        else
        {
            writer.write(String.format(Strings.greetingNewUser, name));
            repository.addUser(name);
        }
        writer.write(Strings.introduction);
        stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
    }
}
