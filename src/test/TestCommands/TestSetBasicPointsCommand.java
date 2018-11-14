package test.TestCommands;

import main.Bot;
import main.Commands.AddChallengeCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSetBasicPointsCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private SetBasicPointsCommand command;
    private User user;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        user = new User(1,"Name");
        user.points = 5;
        repository.addUser(user);
        command = new SetBasicPointsCommand(bot, repository, challengeRepository);
    }

    @Test
    public final void testCorrectSetWhenLessPoints()
    {
        command.execute(user);
        Assert.assertEquals(user.points, 10);
    }

    @Test
    public final void testNotSetWhenMorePoints()
    {
        user.points = 15;
        command.execute(user);
        Assert.assertEquals(user.points, 15);
    }


}
