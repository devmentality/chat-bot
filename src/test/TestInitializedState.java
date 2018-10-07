package test;

import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.Session;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestInitializedState
{
    private StateMachineMock stateMachine;
    private StringBufferWriter writer;
    private InitializedState state;
    private InMemoryRepository repository;
    private String username = "user";

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        repository.addUser(username);
        state = new InitializedState(
                stateMachine, repository, writer, new Session(username));
    }

    @Test
    public final void testTerminateOnExit()
    {
        state.processRequest("exit");
        assertTrue(stateMachine.isTerminated());
    }

    @Test
    public final void testShowsHelp()
    {
        state.processRequest("help");
        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals(Strings.help, output.get(0));
    }

    @Test
    public final void testSwitchesToNewGame()
    {
        state.processRequest("newgame");

        assertThat(stateMachine.getCurrentState(), instanceOf(GameIsOnState.class));
    }

    @Test
    public final void testInvalidContinue()
    {
        state.processRequest("continue");
        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals(Strings.noSavedGames, output.get(0));
    }

    @Test
    public final void testValidContinue()
    {
        repository.addUnfinishedGameResult(username, new Game());

        state.processRequest("continue");

        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals(Strings.continueGamePhrase, output.get(0));
        assertThat(stateMachine.getCurrentState(), instanceOf(GameIsOnState.class));
    }

    @Test
    public final void testGetStatistics()
    {
        state.processRequest("stat");
        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals(String.format(Strings.statisticsTemplate, 0, 0, 0), output.get(0));
    }
}
