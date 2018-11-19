package main.Commands;

import javafx.print.Collation;
import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.PlainResponse;
import main.Response;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
        return users.stream()
                .sorted(Collections.reverseOrder())
                .limit(Math.min(top, users.size()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
