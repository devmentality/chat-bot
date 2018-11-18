package test.TestInteraction;

import main.Bot;
import main.Commands.CancelChallengeCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;

public class CancelChallengeRunnable implements Runnable
{
    private User user;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private CancelChallengeCommand command;

    public CancelChallengeRunnable(User user, Bot bot,
            ConcurrentNewInMemoryRepo repository, ChallengeRepository challengeRepository)
    {
        this.user = user;
        this.repository = repository;
        this.challengeRepository = challengeRepository;
        command = new CancelChallengeCommand(bot, repository, challengeRepository);
    }

    @Override
    public void run()
    {
        command.execute(user);
    }
}

