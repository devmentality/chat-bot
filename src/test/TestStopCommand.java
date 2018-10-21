package test;

import main.Commands.StopGameCommand;
import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.IO.StringBufferWriter;
import main.IStateMachine;
import main.Session;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

public class TestStopCommand
{
    private String username = "user";
    private IStateMachine stateMachine;
    private InMemoryRepository repository;
    private StopGameCommand command;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new InMemoryRepository();
        repository.addUser(username);
        command = new StopGameCommand(stateMachine, repository, new Session(username),
                new Game(4));
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute();

        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsUnfinishedGame()
    {
        command.execute();

        Assert.assertTrue(repository.hasUnfinishedGame(username));
    }
}
