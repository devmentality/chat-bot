package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public abstract class StateBase implements IState
{
    protected IAppRepository repository;
    protected IStateMachine stateMachine;
    protected IMessageWriter writer;

    public StateBase(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.writer = writer;
    }
}
