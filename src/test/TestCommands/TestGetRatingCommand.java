package test.TestCommands;

import main.Bot;
import main.Commands.GetRatingCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestGetRatingCommand
{
    private Bot bot;
    private ConcurrentNewInMemoryRepo repository;
    private GetRatingCommand command;

    @Before
    public final void arrange()
    {
        repository = new ConcurrentNewInMemoryRepo();
        bot = new Bot(repository, new ChallengeRepository());
        command = new GetRatingCommand(bot, repository);
    }

    private User[] addUsers()
    {
        User user1 = new User(0, "u1");
        User user2 = new User(1, "u2");
        User user3 = new User(2, "u3");
        user1.points = 2;
        user2.points = 3;
        user3.points = 1;

        repository.addUser(user1);
        repository.addUser(user2);
        repository.addUser(user3);

        return new User[] { user1, user2, user3 };
    }

    @Test
    public final void getTopShouldReturnUsersWithNonAscendingPoints()
    {
        User[] users = addUsers();

        ArrayList<User> top = command.getTop(3);
        Assert.assertArrayEquals(new User[]{ users[1], users[0], users[2] }, top.toArray());
    }

    @Test
    public final void getTopShouldReturnLessThanTop()
    {
        User[] users = addUsers();
        Assert.assertEquals(users.length, command.getTop(5).size());
    }

    @Test
    public final void getTopShouldReturnExactlyTop()
    {
        addUsers();
        Assert.assertEquals(2, command.getTop(2).size());
    }


}
