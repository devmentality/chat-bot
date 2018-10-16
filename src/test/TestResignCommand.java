package test;

import main.Commands.ResignCommand;
import main.Data.InMemoryRepository;
import main.GameLogic.GameResult;
import main.IO.StringBufferWriter;
import main.IStateMachine;
import main.Session;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestResignCommand
{
    private String username = "user";
    private IStateMachine stateMachine;
    private InMemoryRepository repository;
    private ResignCommand command;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new InMemoryRepository();
        repository.addUser(username);
        command = new ResignCommand(stateMachine, repository, new StringBufferWriter(), new Session(username));
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute();

        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsLoss()
    {
        command.execute();

        Assert.assertEquals(1, getAmountOfLosses(repository.getGameResults(username)));
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
