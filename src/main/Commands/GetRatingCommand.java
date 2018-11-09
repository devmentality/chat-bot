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
        ArrayList<User> users = repository.getAll();
        Collections.sort(users, Collections.reverseOrder());
        for(int i = 0; i < top; i++)
            ratingResponse.addMessageToContent(
                    String.format(
                            "%d place %s with %d points",
                            i + 1, users.get(i).username, users.get(i).points));
        return Response.compose(ratingResponse);
    }
}
