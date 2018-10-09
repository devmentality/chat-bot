package test;

import main.Commands.AttemptsCommand;
import main.Data.InMemoryRepository;
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
    private StringBufferWriter writer;
    private InMemoryRepository repository;
    private Game game;
    private AttemptsCommand command;
    private String username = "user";

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        repository.addUser(username);
        game = new Game();
        command = new AttemptsCommand(stateMachine, repository, writer, game);
    }

    @Test
    public final void testNoAttempts()
    {
        command.execute();

        ArrayList<String> output = writer.getBuffer();

        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.noAttempts, output.get(0));
    }

    @Test
    public final void testSeveralAttempts()
    {
        String[] attempts = new String[] {"1234", "2345"};
        ArrayList<GuessResult> results = new ArrayList<>();

        for(String attempt: attempts)
            results.add(game.respondOnGuess(GameController.parseGuess(attempt)));

        command.execute();

        ArrayList<String> output = writer.getBuffer();

        Assert.assertEquals(2 * attempts.length, output.size());
        for(int messageIndex = 0; messageIndex + 1 < output.size(); messageIndex += 2)
        {
            Assert.assertEquals(attempts[messageIndex / 2], output.get(messageIndex));
            String expectedAttemptResult =
                    String.format(Strings.guessResultTemplate,
                    results.get(messageIndex / 2).amountOfBulls,
                    results.get(messageIndex / 2).amountOfCows);

            Assert.assertEquals(expectedAttemptResult, output.get(messageIndex + 1));
        }
    }
}
