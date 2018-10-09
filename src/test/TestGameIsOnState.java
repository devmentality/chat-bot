package test;

import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.IO.StringBufferWriter;
import main.Session;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.instanceOf;

public class TestGameIsOnState
{
    private StateMachineMock stateMachine;
    private StringBufferWriter writer;
    private GameIsOnState state;
    private InMemoryRepository repository;
    private Game game;
    private String username = "user";

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        repository.addUser(username);
        game = new Game();
        state = new GameIsOnState(stateMachine,repository, writer, game, new Session(username));
    }

    @Test
    public final void testResignSwitchesState()
    {
        state.processRequest("resign");

        Assert.assertThat(stateMachine.getCurrentState(), instanceOf(InitializedState.class));
    }

    @Test
    public final void testStopSwitchesState()
    {
        state.processRequest("stop");

        Assert.assertThat(stateMachine.getCurrentState(), instanceOf(InitializedState.class));
    }

    @Test
    public final void testAttempts()
    {
        String attempt = "1290";
        game.respondOnGuess(GameController.parseGuess(attempt));

        state.processRequest("attempts");

        ArrayList<String> output = writer.getBuffer();

        Assert.assertEquals(2, output.size());
        Assert.assertEquals(attempt, output.get(0));
        Pattern p = Pattern.compile("^Bulls: \\d+, cows: \\d+$");
        Assert.assertTrue(p.matcher(output.get(1)).matches());

    }
}
