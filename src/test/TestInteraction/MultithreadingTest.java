package test.TestInteraction;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import org.junit.Assert;
import org.junit.Test;

public class MultithreadingTest
{
    class ExceptionsHandler implements Thread.UncaughtExceptionHandler
    {
        public Boolean thrown = false;

        @Override
        public void uncaughtException(Thread t, Throwable e)
        {
            thrown = true;
            e.printStackTrace();
        }
    }

    private final int attempts = 100;

    @Test
    public final void testCancelChallengeManyParallel()
    {
        for(int i = 0; i < attempts; i++)
            testCancelChallengeSingleParallel();
    }

    private void testCancelChallengeSingleParallel()
    {
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        ChallengeRepository challengeRepository = new ChallengeRepository();
        Bot bot = new Bot(repository, challengeRepository);

        User user = new User(1, "u");
        User sameUser = new User(1, "u");
        repository.addUser(user);
        challengeRepository.addChallenge(user.id, new Challenge(user.id, new int[]{1, 2, 3, 4}, 0));

        Runnable firstRunnable = new CancelChallengeRunnable(user, bot, repository, challengeRepository);
        Runnable secondRunnable = new CancelChallengeRunnable(sameUser, bot, repository, challengeRepository);

        performMultithreading(firstRunnable, secondRunnable);
        Assert.assertFalse(challengeRepository.hasChallenge(user.id));
    }

    private void performMultithreading(Runnable firstRunnable, Runnable secondRunnable)
    {
        Thread firstThread = new Thread(firstRunnable);
        Thread secondThread = new Thread(secondRunnable);
        ExceptionsHandler firstExceptionsHandler = new ExceptionsHandler();
        ExceptionsHandler secondExceptionsHandler = new ExceptionsHandler();

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
    }

    @Test
    public final void testAddChallengeManyParallel()
    {
        for(int i = 0; i < attempts; i++)
            testAddChallengeSingleParallel();
    }

    private void testAddChallengeSingleParallel()
    {
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        ChallengeRepository challengeRepository = new ChallengeRepository();
        Bot bot = new Bot(repository, challengeRepository);

        User user = new User(1, "u");
        User sameUser = new User(1, "u");
        repository.addUser(user);

        Runnable firstRunnable = new AddChallengeRunnable(user, bot, repository, challengeRepository);
        Runnable secondRunnable = new AddChallengeRunnable(sameUser, bot, repository, challengeRepository);
        performMultithreading(firstRunnable, secondRunnable);

        Assert.assertTrue(challengeRepository.hasChallenge(1L));
    }
}
