package main;

import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.States.*;

import java.util.ArrayList;

public class Bot implements IStateMachine
{
    private INewRepository repository;
    private IState currentState;

    public final InitializedState initializedState;
    public final GameIsOnState gameIsOnState;
    public final ChooseGameModeState chooseGameModeState;
    public final ChooseGameDifficultyState chooseGameDifficultyState;
    public final ChooseChallengeState chooseChallengeState;
    public final ChallengeGameState challengeGameState;

    public Bot(INewRepository repository, ChallengeRepository challengeRepository)
    {
        this.repository = repository;

        initializedState = new InitializedState(this, repository, challengeRepository);
        gameIsOnState = new GameIsOnState(this, repository);
        chooseGameModeState = new ChooseGameModeState(this, repository, challengeRepository);
        chooseGameDifficultyState = new ChooseGameDifficultyState(this, repository);
        chooseChallengeState = new ChooseChallengeState(this, repository, challengeRepository);
        challengeGameState = new ChallengeGameState(this, repository);

        currentState = initializedState;
    }

    public void changeState(IState nextState)
    {
        currentState = nextState;
    }

    public IState getCurrentState()
    {
        return currentState;
    }

    public ArrayList<Response> processRequest(User user, String message)
    {
        return currentState.processRequest(user, message);
    }
}
