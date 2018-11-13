package main.Data;

import main.GameLogic.GameController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ChallengeRepository
{
    private HashMap<Long, Challenge> challenges;    //stores one challenge per creator
    private ReentrantLock repositoryLock;

    public ChallengeRepository()
    {
        challenges = new HashMap<>();
        repositoryLock = new ReentrantLock();
    }

    public Boolean hasChallenge(Long id)
    {
        repositoryLock.lock();

        Boolean result =  challenges.containsKey(id);

        repositoryLock.unlock();
        return result;
    }

    public void addChallenge(Long id, int number, int points)
    {
        repositoryLock.lock();

        if (hasChallenge(id))
            throw new IllegalArgumentException("User already create other challenge");
        int[] digits = GameController.parseGuess(String.valueOf(number));
        challenges.put(id, new Challenge(id, digits, points));

        repositoryLock.unlock();
    }

    public Challenge pickChallenge(Long id)
    {
        repositoryLock.lock();

        if (!hasChallenge(id))
            throw new IllegalArgumentException("User doesn't have challenge");
        Challenge challenge = challenges.get(id);
        challenges.remove(id);

        repositoryLock.unlock();
        return challenge;
    }

    public ArrayList<ChallengeDescription> allChallengesDescriptions()
    {
        repositoryLock.lock();

        ArrayList<ChallengeDescription> descriptions = new ArrayList<>();
        for(Challenge challenge: challenges.values())
            descriptions.add(new ChallengeDescription(challenge.creatorId, challenge.points));

        repositoryLock.unlock();
        return descriptions;
    }
}
