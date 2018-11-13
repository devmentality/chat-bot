package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.PlainResponse;
import main.Response;

import java.util.ArrayList;
import java.util.Collections;

public class GetRatingCommand extends CommandBase
{
    public GetRatingCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository, "rating");
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        final int top = 10;
        PlainResponse ratingResponse = new PlainResponse(user.id);
        ArrayList<User> rating = getTop(top);
        for(int i = 0; i < rating.size(); i++)
            ratingResponse.addMessageToContent(
                    String.format(
                            "%d place %s with %d points",
                            i + 1, rating.get(i).username, rating.get(i).points));
        return Response.compose(ratingResponse);
    }

    public ArrayList<User> getTop(int top)
    {
        ArrayList<User> users = repository.getAll();
        ArrayList<User> rating = new ArrayList<>();
        Collections.sort(users, Collections.reverseOrder());
        for(int i = 0; i < Math.min(top, users.size()); i++)
            rating.add(users.get(i));
        return rating;
    }
}
