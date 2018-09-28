package main;

import main.Data.IAppRepository;

public abstract class ControllerStateBase implements IControllerState
{
    protected IController controller;
    protected IAppRepository repository;
    protected Bot bot;

    public ControllerStateBase(IController controller, Bot bot, IAppRepository repository)
    {
        this.controller = controller;
        this.bot = bot;
        this.repository = repository;
    }
}
