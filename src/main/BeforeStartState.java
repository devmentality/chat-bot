package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public class BeforeStartState extends StateBase
{
    public BeforeStartState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
    }

    @Override
    public void processRequest(String request) {
        if (request.equals("start"))
        {
            writer.write("Hello");
            writer.write("What is your name?");
            stateMachine.changeState(new StartedState(stateMachine, repository, writer));
        }
        else
            writer.write("Send 'start' to start dialog");
    }
}
