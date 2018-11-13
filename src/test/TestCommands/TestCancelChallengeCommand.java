package test.TestCommands;

import main.Bot;
import main.Commands.CancelChallengeCommand;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCancelChallengeCommand
{
    private CancelChallengeCommand command;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        challengeRepository = new ChallengeRepository();
        command = new CancelChallengeCommand(new Bot(repository, challengeRepository), repository, challengeRepository);
    }

    @Test
    public final void shouldRemoveChallengeAndReturnPoints_whenChallengeExists()
    {
        User user = new User(1, "user");
        repository.addUser(user);
        Challenge challenge = new Challenge(user.id, new Game(4), 10);
        challengeRepository.addChallenge(user.id, challenge);

        command.execute(user);

        Assert.assertFalse(challengeRepository.hasChallenge(user.id));
        Assert.assertEquals(10, repository.getUser(user.id).points);
    }
}
