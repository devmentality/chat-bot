package main.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class ChallengeRepository
{
    private final HashMap<Long, Challenge> challenges;    //stores one challenge per creator

    public ChallengeRepository()
    {
        challenges = new HashMap<>();
    }

    public synchronized Boolean hasChallenge(Long id)
    {
        return challenges.containsKey(id);
    }

    public void addChallenge(Long id, Challenge challenge)
    {
        if (hasChallenge(id))
            throw new IllegalArgumentException("User already create other challenge");
        challenges.put(id, challenge);
    }

    public synchronized Challenge pickChallenge(Long id)
    {
        if (!hasChallenge(id))
            throw new IllegalArgumentException("User doesn't have challenge");
        Challenge challenge = challenges.get(id);
        challenges.remove(id);

        return challenge;
    }

    public synchronized ArrayList<ChallengeDescription> allChallengesDescriptions()
    {
        ArrayList<ChallengeDescription> descriptions = new ArrayList<>();
        for(Challenge challenge: challenges.values())
            descriptions.add(new ChallengeDescription(challenge.creatorId, challenge.points));

        return descriptions;
    }
}
