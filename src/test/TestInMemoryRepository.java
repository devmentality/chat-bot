package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.GameLogic.GameResult;

public class TestInMemoryRepository 
{	
	private InMemoryRepository repository;
	
	@Before
	public final void assignment()
	{
		repository = new InMemoryRepository();
	}
	
	@Test
	public final void testAddUser()
	{
		repository.addUser("user");
		assertTrue(repository.hasUser("user"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testIncorrectAddUser()
	{
		repository.addUser("user");
		repository.addUser("user");
	}
	
	@Test
	public final void testHasUser()
	{
		assertFalse(repository.hasUser("user"));
		repository.addUser("user");
		assertTrue(repository.hasUser("user"));
	}
	
	@Test
	public final void testAddGameResult()
	{
		repository.addUser("user");
		GameResult gameResult1 = new GameResult(true);
		GameResult gameResult2 = new GameResult(false);
		repository.addGameResult("user", gameResult1);
		repository.addGameResult("user", gameResult2);
		ArrayList<GameResult> results = repository.getGameResults("user");
		assertEquals(results.get(0), gameResult1);
		assertEquals(results.get(1), gameResult2);
	}
	
	@Test
	public final void testHasUnfinishedGameResult()
	{
		Game game = new Game();
		repository.addUnfinishedGame("user", game);
		assertTrue(repository.hasUnfinishedGame("user"));
		repository.getUnfinishedGame("user");
		assertFalse(repository.hasUnfinishedGame("user"));
	}
	
	@Test
	public final void testCorrectDeleteUnfinishedGame()
	{
		Game game = new Game();
		repository.addUnfinishedGame("user", game);
		repository.deleteUnfinishedGame("user");
		assertFalse(repository.hasUnfinishedGame("user"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testIncorrectDeleteUnfinishedGame()
	{
		repository.deleteUnfinishedGame("user");
	}
	
	@Test
	public final void testGetUnfinishedGame()
	{
		Game game1 = new Game();
		Game game2 = new Game();
		repository.addUnfinishedGame("user", game1);
		repository.addUnfinishedGame("user", game2);
		Game game3 = repository.getUnfinishedGame("user");
		assertEquals(game2, game3);
		assertNotEquals(game1, game3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNotHasUnfinishedGame()
	{
		assertFalse(repository.hasUnfinishedGame("user"));
		repository.getUnfinishedGame("user");
	}
}
