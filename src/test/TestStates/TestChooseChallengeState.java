package test.TestStates;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Attempt;
import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import main.States.ChallengeGameState;
import main.States.ChooseChallengeState;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestChooseChallengeState
{
    private Bot bot;
    private ChooseChallengeState state;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private User player;
    private User creator;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, new ChallengeRepository());
        state = new ChooseChallengeState(bot, repository, challengeRepository);
        bot.changeState(state);

        player = new User(1, "u1");
        creator = new User(2, "u2");
        repository.addUser(player);
        repository.addUser(creator);
        challengeRepository.addChallenge(creator.id,
                new Challenge(creator.id, new Game(4), 0));
    }

    @Test
    public final void shouldSwitchToInitStateOnDecline()
    {
        state.processRequest(player, "decline");

        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void shouldSwitchToInitStateOnWrongChoice()
    {
        state.processRequest(player, "kek");

        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void shouldSwitchToChallengeGameState()
    {
        state.processRequest(player, "2");

        Assert.assertTrue(bot.getCurrentState() instanceof ChallengeGameState);
    }

    @Test
    public final void shouldAttachChallengeToPlayer()
    {
        state.processRequest(player, "2");

        Assert.assertTrue(repository.getUser(player.id).unfinishedGame != null);
        Assert.assertTrue(repository.getUser(player.id).challengeDescription != null);
        Assert.assertEquals(repository.getUser(player.id).challengeDescription.creatorId, creator.id);
    }
}
