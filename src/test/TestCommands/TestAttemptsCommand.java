package test.TestCommands;

import main.Commands.AttemptsCommand;
import main.Data.InMemoryRepository;
import main.GameLogic.Attempt;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GuessResult;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;
import java.util.ArrayList;

public class TestAttemptsCommand
{
    private StateMachineMock stateMachine;
    private InMemoryRepository repository;
    private Game game;
    private AttemptsCommand command;
    private String username = "user";

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        repository = new InMemoryRepository();
        repository.addUser(username);
        game = new Game(4);
        command = new AttemptsCommand(stateMachine, repository, game);
    }

    @Test
    public final void testNoAttempts()
    {
        ArrayList<String> output = command.execute();

        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.noAttempts, output.get(0));
    }

    @Test
    public final void testAttemptFormat()
    {
        int []guess = new int[] {1, 2, 3, 4};
        String guessStr = "1234";
        GuessResult result = new GuessResult(0, 1);
        game.attempts.add(new Attempt(guess, result));

        ArrayList<String> output = command.execute();

        Assert.assertEquals(2, output.size());
        Assert.assertEquals(guessStr, output.get(0));

        String expectedAttemptResult =
                String.format(Strings.guessResultTemplate,
                result.amountOfBulls, result.amountOfCows);
        Assert.assertEquals(expectedAttemptResult, output.get(1));
    }
}
