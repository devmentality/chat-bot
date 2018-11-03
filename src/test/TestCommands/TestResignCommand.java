package test.TestCommands;

import main.Commands.ResignCommand;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.GameLogic.GameResult;
import main.IStateMachine;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestResignCommand
{
    private IStateMachine stateMachine;
    private ConcurrentNewInMemoryRepo repository;
    private ResignCommand command;
    private User user;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new ConcurrentNewInMemoryRepo();
        command = new ResignCommand(stateMachine, repository);
        user = new User();
        user.unfinishedGame = new Game(4);
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsLoss()
    {
        command.execute(user);
        Assert.assertEquals(1, getAmountOfLosses(user.gameResults));
    }

    private int getAmountOfLosses(ArrayList<GameResult> results)
    {
        int counter = 0;
        for(GameResult result: results)
            if(!result.isVictory())
                counter++;
        return counter;
    }
}
