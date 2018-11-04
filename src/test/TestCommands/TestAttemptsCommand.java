package test.TestCommands;

import main.Commands.AttemptsCommand;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.GameLogic.Attempt;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GuessResult;
import main.IO.StringBufferWriter;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;
import java.util.ArrayList;

public class TestAttemptsCommand
{
    private StateMachineMock stateMachine;
    private ConcurrentNewInMemoryRepo repository;
    private Game game;
    private AttemptsCommand command;
    private User user;

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        repository = new ConcurrentNewInMemoryRepo();
        game = new Game(4);
        user = new User();
        user.unfinishedGame = game;
        command = new AttemptsCommand(stateMachine, repository);
    }

    @Test
    public final void testNoAttempts()
    {
        PlainResponse output = (PlainResponse) command.execute(user).get(0);
        Assert.assertEquals(1, output.getContent().size());
        Assert.assertEquals(Strings.noAttempts, output.getContent().get(0));
    }

    @Test
    public final void testAttemptFormat()
    {
        int []guess = new int[] {1, 2, 3, 4};
        String guessStr = "1234";
        GuessResult result = new GuessResult(0, 1);
        game.attempts.add(new Attempt(guess, result));

        PlainResponse output = (PlainResponse)command.execute(user).get(0);

        Assert.assertEquals(2, output.getContent().size());
        ArrayList<String> content = output.getContent();
        Assert.assertEquals(guessStr, content.get(0));

        String expectedAttemptResult =
                String.format(Strings.guessResultTemplate,
                result.amountOfBulls, result.amountOfCows);
        Assert.assertEquals(expectedAttemptResult, content.get(1));
    }
}
