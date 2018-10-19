package test;

import main.Commands.ContinueGameCommand;
import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.IO.StringBufferWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;
import main.States.GameIsOnState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;
import java.util.ArrayList;

public class TestContinueCommand
{
    private String username = "user";
    private IStateMachine stateMachine;
    private InMemoryRepository repository;
    private ContinueGameCommand command;
    private StringBufferWriter writer;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        repository = new InMemoryRepository();
        repository.addUser(username);
        repository.addUnfinishedGame(username, new Game(4));
        writer = new StringBufferWriter();
        command = new ContinueGameCommand(stateMachine, repository, writer, new Session(username));
    }

    @Test
    public final void testResponseWhenNoUnfinishedGame()
    {
        repository.deleteUnfinishedGame(username);
        command.execute();
        ArrayList<String> output = writer.getBuffer();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.noSavedGames, output.get(0));
    }

    @Test
    public final void testResponseWhenHasUnfinishedGame()
    {
        command.execute();
        ArrayList<String> output = writer.getBuffer();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.continueGamePhrase, output.get(0));
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute();
        Assert.assertTrue(stateMachine.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testRemovesUnfinishedGame()
    {
        command.execute();
        Assert.assertFalse(repository.hasUnfinishedGame(username));
    }
}
