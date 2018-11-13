package test.TestInteraction;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestChallengeInteraction
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private User user1;
    private User user2;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);

        user1 = new User(1, "u1");
        user2 = new User(2, "u2");
        repository.addUser(user1);
        repository.addUser(user2);

        challengeRepository.addChallenge(user2.id, new Challenge(user2.id, new int[]{1, 2, 3, 4}, 10));
    }

    private void executeCommands(String[] commands)
    {
        for(String command: commands)
            bot.processRequest(user1, command);
    }

    @Test
    public final void acceptingOfChallenge_ShouldRemoveOpponentsChallenge()
    {
        executeCommands(new String[] {
                "startgame", "challenge", ((Long)user2.id).toString()
        });

        Assert.assertFalse(challengeRepository.hasChallenge(user2.id));
    }

    @Test
    public final void victoryOfUser_ShouldAddPoints()
    {
        executeCommands(new String[]
                {
                        "startgame", "challenge", ((Long)user2.id).toString(), "1234"
                });

        Assert.assertEquals(10, repository.getUser(user1.id).points);
    }

    @Test
    public final void lossOfUser_ShouldDecreaseHisAndIncreaseOpponentsPoints()
    {
        executeCommands(new String[]
                {
                        "startgame", "challenge", ((Long)user2.id).toString()
                });
        for(int i = 0; i < Game.attemptsToLose; i++)
            bot.processRequest(user1, "1235");

        Assert.assertEquals(10, repository.getUser(user2.id).points);
        Assert.assertEquals(-10, repository.getUser(user1.id).points);
    }
}
