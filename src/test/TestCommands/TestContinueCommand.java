package test.TestCommands;

import main.Bot;
import main.Commands.ContinueGameCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Game;
import main.PlainResponse;
import main.Resources.Strings;
import main.States.GameIsOnState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class TestContinueCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private ContinueGameCommand command;
    private User user;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        user = new User();
        user.unfinishedGame = new Game(4);
        command = new ContinueGameCommand(bot, repository);
    }

    @Test
    public final void testResponseWhenNoUnfinishedGame()
    {
        user.unfinishedGame = null;
        ArrayList<String> output = ((PlainResponse)command.execute(user).get(0)).getContent();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.noSavedGames, output.get(0));
    }

    @Test
    public final void testResponseWhenHasUnfinishedGame()
    {
        ArrayList<String> output = ((PlainResponse)command.execute(user).get(0)).getContent();
        Assert.assertEquals(1, output.size());
        Assert.assertEquals(Strings.continueGamePhrase, output.get(0));
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }
}
