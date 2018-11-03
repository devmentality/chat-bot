package test.TestCommands;

import main.Commands.StopGameCommand;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

public class TestStopCommand
{
    private IStateMachine stateMachine;
    private ConcurrentNewInMemoryRepo repository;
    private StopGameCommand command;
    private User user;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new ConcurrentNewInMemoryRepo();
        command = new StopGameCommand(stateMachine, repository);
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);

        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }
}
