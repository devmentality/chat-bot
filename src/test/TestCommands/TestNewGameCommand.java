package test.TestCommands;

import main.Bot;
import main.Commands.NewGameCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.IStateMachine;
import main.States.GameIsOnState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

public class TestNewGameCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private NewGameCommand command;
    private User user;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        user = new User();
        command = new NewGameCommand(bot, repository);
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user, "4");
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }

    @Test(expected = NumberFormatException.class)
    public final void testIncorrectArgument()
    {
        command.execute(user, "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testArgumentGraterThanMax()
    {
        command.execute(user, "11");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testArgumentLessThanMin()
    {
        command.execute(user, "-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testEmptyArgument()
    {
        command.execute(user, " ");
    }
}
