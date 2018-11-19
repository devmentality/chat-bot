package test.TestStates;

import main.Bot;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import main.States.ChooseGameDifficultyState;
import main.States.GameIsOnState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestChooseGameDifficultyState
{
    private Bot bot;
    private ChooseGameDifficultyState state;
    private ConcurrentNewInMemoryRepo repository;
    private User user;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        bot = new Bot(repository, new ChallengeRepository());
        state = new ChooseGameDifficultyState(bot, repository);
        bot.changeState(state);
        user = new User();
        repository.addUser(user);
    }

    @Test
    public final void testSwitchesStateWithEasyCommand()
    {
        bot.processRequest(user, "easy");
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testSwitchesStateWithMediumCommand()
    {
        bot.processRequest(user, "medium");
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testSwitchesStateWithHardCommand()
    {
        bot.processRequest(user, "hard");
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testNotSwitchesState()
    {
        ArrayList<Response> response = bot.processRequest(user, "lololo");
        Assert.assertTrue(bot.getCurrentState() instanceof ChooseGameDifficultyState);
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.noSuchMode);
    }
}
