package test.TestCommands;

import main.Bot;
import main.Commands.AddChallengeCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestAddChallengeCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private AddChallengeCommand command;
    private User user;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        user = new User(1,"Name");
        user.points = 10;
        repository.addUser(user);
        command = new AddChallengeCommand(bot, repository, challengeRepository);
    }

    @Test
    public final void testCorrectAddChallenge()
    {
        command.execute(user, "1234", "1");
        Assert.assertTrue(challengeRepository.hasChallenge(user.id));
    }

    @Test
    public final void testNotEnoughPointsToChallenge()
    {
        command.execute(user, "1234", "11");
        Assert.assertFalse(challengeRepository.hasChallenge(user.id));
    }

    @Test
    public final void testCorrectChangePoints()
    {
        command.execute(user, "1234", "4");
        Assert.assertEquals(user.points, 6);
    }

    @Test
    public final void testMoreThanOneChallengeByUser()
    {
        command.execute(user, "1234", "1");
        ArrayList<Response> response = command.execute(user, "1234", "5");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0), Strings.alreadyHaveChallenge);
    }

    @Test
    public final void testNotANumber()
    {
        ArrayList<Response> response = command.execute(user, "123a", "1");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.yourChallengeHasIncorrectNumber);
    }

    @Test
    public final void testPointsNotANumber()
    {
        ArrayList<Response> response = command.execute(user, "1234", "a");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.yourChallengeHasIncorrectPoints);
    }

    @Test
    public final void testNotPositivePoints()
    {
        ArrayList<Response> response = command.execute(user, "1234", "-3");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.yourChallengeHasIncorrectPoints);
    }

    @Test
    public final void testIncorrectLengthOfNumber()
    {
        ArrayList<Response> response = command.execute(user, "12345", "1");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.yourChallengeHasIncorrectNumber);
    }

    @Test
    public final void testNotUniqueDigitsInNumber()
    {
        ArrayList<Response> response = command.execute(user, "1233", "1");
        Assert.assertEquals(((PlainResponse)response.get(0)).getContent().get(0),
                Strings.yourChallengeHasIncorrectNumber);
    }
}
