package test.TestInteraction;

import main.Bot;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import org.junit.Assert;
import org.junit.Test;

public class MultithreadingTest
{
    class Handler implements Thread.UncaughtExceptionHandler
    {
        public Boolean thrown = false;

        @Override
        public void uncaughtException(Thread t, Throwable e)
        {
            thrown = true;
        }
    }

    @Test
    public final void TestAddChallengeParallel()
    {
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        ChallengeRepository challengeRepository = new ChallengeRepository();
        Bot bot = new Bot(repository, challengeRepository);

        User user = new User(1, "u");
        User sameUser = new User(1, "u");
        repository.addUser(user);

        AddChallengeRunner firstRunnable = new AddChallengeRunner(user, bot, repository, challengeRepository);
        AddChallengeRunner secondRunnable = new AddChallengeRunner(sameUser, bot, repository, challengeRepository);
        Thread firstThread = new Thread(firstRunnable);
        Thread secondThread = new Thread(secondRunnable);
        Handler firstExceptionsHandler = new Handler();
        Handler secondExceptionsHandler = new Handler();

        firstThread.setUncaughtExceptionHandler(firstExceptionsHandler);
        secondThread.setUncaughtExceptionHandler(secondExceptionsHandler);
        firstThread.start();
        secondThread.start();

        try
        {
            firstThread.join();
            secondThread.join();
        }
        catch (InterruptedException ex)
        {
            Assert.fail();
        }

        Assert.assertFalse(firstExceptionsHandler.thrown);
        Assert.assertFalse(secondExceptionsHandler.thrown);
        Assert.assertTrue(challengeRepository.hasChallenge(1L));
    }
}
