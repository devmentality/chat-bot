package main.Data;

import java.util.HashMap;

public class ChallengeRepository
{
    private HashMap<Long, Challenge> challenges;    //stores one challenge per creator

    public ChallengeRepository()
    {
        challenges = new HashMap<>();
    }

    public Boolean hasChallenge(Long id)
    {
        return challenges.containsKey(id);
    }

    public void addChallenge(Long id, Challenge challenge)
    {
        if (hasChallenge(id))
            throw new IllegalArgumentException("User already create other challenge");
        challenges.put(id, challenge);
    }

    public void removeChallenge(Long id)
    {
        if (!hasChallenge(id))
            throw new IllegalArgumentException("User doesn't have challenge");
        challenges.remove(id);
    }
}
