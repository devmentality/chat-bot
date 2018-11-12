package test.TestCommands;

import main.Bot;
import main.Commands.StopGameCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestStopCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private StopGameCommand command;
    private User user;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        user = new User();
        command = new StopGameCommand(bot, repository);
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);

        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }
}
