package test.TestCommands;

import main.Bot;
import main.Commands.ResignCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Game;
import main.GameLogic.GameResult;
import main.States.InitializedState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class TestResignCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private ResignCommand command;
    private User user;

    @Before
    public final void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        bot = new Bot(repository, challengeRepository);
        command = new ResignCommand(bot, repository);
        user = new User();
        user.unfinishedGame = new Game(4);

        repository.addUser(user);
    }

    @Test
    public final void testSwitchesState()
    {
        command.execute(user);
        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsLoss()
    {
        command.execute(user);
        Assert.assertEquals(1, getAmountOfLosses(user.gameResults));
    }

    private int getAmountOfLosses(ArrayList<GameResult> results)
    {
        int counter = 0;
        for(GameResult result: results)
            if(!result.isVictory())
                counter++;
        return counter;
    }
}
