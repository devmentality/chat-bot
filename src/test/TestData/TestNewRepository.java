package test.TestData;

import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNewRepository
{
    private ConcurrentNewInMemoryRepo repository;

    @Before
    public final void assignment()
    {
        repository = new ConcurrentNewInMemoryRepo();
    }

    @Test
    public final void getAll_shouldReturnEmptyList_whenCreated()
    {
        Assert.assertTrue(repository.getAll().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void addUser_shouldThrow_whenAddedTwoUsersWithSameId()
    {
        User first = new User();
        first.id = 1;
        User second = new User();
        second.id = 1;

        repository.addUser(first);
        repository.addUser(second);
    }

    @Test
    public final void getAll_shouldReturnSingle_afterAddOne()
    {
        User user = new User();
        user.id = 10;

        repository.addUser(user);
        Assert.assertEquals(1, repository.getAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void addUser_shouldThrow_whenNullAdded()
    {
        repository.addUser(null);
    }

    @Test
    public final void getUserById_shouldReturnUserWithSameId_whenUserWasAdded()
    {
        int id = 10;
        User user = new User();
        user.id = id;

        repository.addUser(user);
        Assert.assertEquals(id, repository.getUser(id).id);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void getUserById_shouldThrow_whenNoUserWithThisId()
    {
        repository.getUser(10);
    }

    @Test
    public final void updateUser_shouldUpdateUserInfo_whenUserAlreadyExists()
    {
        User user = new User();
        user.id = 10;
        user.username = "user";

        repository.addUser(user);

        User updatedUser = new User();
        updatedUser.id = 10;
        updatedUser.username = "updatedname";

        repository.updateUser(updatedUser);

        Assert.assertEquals("updatedname", repository.getUser(10).username);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void updateUser_shouldThrow_whenNoUserWithThisId()
    {
        User user = new User();
        user.id = 10;

        repository.updateUser(user);
    }
}
