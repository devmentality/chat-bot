package test.TestStates;

import main.Bot;
import main.Data.Challenge;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.Data.ChallengeRepository;
import main.States.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestChooseGameModeState
{
    private Bot bot;
    private ChooseGameModeState state;
    private ConcurrentNewInMemoryRepo repository;
    private User user;
    private ChallengeRepository challengeRepository;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        state = new ChooseGameModeState(bot, repository, challengeRepository);
        bot.changeState(state);
        user = new User();
        repository.addUser(user);
    }

    @Test
    public final void testSwitchesStateOnChooseDifficultyState()
    {
        bot.processRequest(user, "classic");
        Assert.assertTrue(bot.getCurrentState() instanceof ChooseGameDifficultyState);
    }

    @Test
    public final void testSwitchesOnInitializedState()
    {
        bot.processRequest(user, "challenge");
        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testSwitchesOnChooseChallengeState()
    {
        challengeRepository.addChallenge((long)2, new Challenge(2, new int[] {1, 2, 3, 4}, 0));
        bot.processRequest(user, "challenge");
        Assert.assertTrue(bot.getCurrentState() instanceof ChooseChallengeState);
    }


}
