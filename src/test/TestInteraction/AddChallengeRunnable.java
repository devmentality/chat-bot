package test.TestInteraction;

import main.Bot;
import main.Commands.AddChallengeCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;

public class AddChallengeRunnable implements Runnable
{
    private User user;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private AddChallengeCommand command;

    public AddChallengeRunnable(User user, Bot bot,
                                ConcurrentNewInMemoryRepo repository, ChallengeRepository challengeRepository)
    {
        this.user = user;
        this.repository = repository;
        this.challengeRepository = challengeRepository;
        command = new AddChallengeCommand(bot, repository, challengeRepository);
    }

    @Override
    public void run()
    {
        command.execute(user, "1234", "0");
    }
}
