package main;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Response
{
    private long receiverId;

    public Response(long receiverId)
    {
        this.receiverId = receiverId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public static ArrayList<Response> compose(Response... responses)
    {
        return new ArrayList<>(Arrays.asList(responses));
    }
}
