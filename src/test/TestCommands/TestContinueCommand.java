package test.TestCommands;

import main.Commands.ContinueGameCommand;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.Resources.Strings;
import main.States.GameIsOnState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;
import java.util.ArrayList;

public class TestContinueCommand
{
    private IStateMachine stateMachine;
    private ConcurrentNewInMemoryRepo repository;
    private ContinueGameCommand command;
    private User user;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new ConcurrentNewInMemoryRepo();
        user = new User();
        user.unfinishedGame = new Game(4);
        command = new ContinueGameCommand(stateMachine, repository);
    }

    @Test
    public final void testResponseWhenNoUnfinishedGame()
    {
        user.unfinishedGame = null;
        ArrayList<String> output = command.execute(user).get(0).getContent();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.noSavedGames, output.get(0));
    }

    @Test
    public final void testResponseWhenHasUnfinishedGame()
    {
        ArrayList<String> output = command.execute(user).get(0).getContent();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.continueGamePhrase, output.get(0));
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof GameIsOnState);
    }
}
