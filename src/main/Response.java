package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Response
{
    private long receiverId;
    private ArrayList<String> content;
    private ResponseType type;

    public Response(long receiverId, String... messages)
    {
        this.receiverId = receiverId;
        this.content = new ArrayList<>(Arrays.asList(messages));
        this.type = ResponseType.PLAIN_TEXT;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public ArrayList<String> getContent()
    {
        return content;
    }

    public void addMessageToContent(String line)
    {
        content.add(line);
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public static ArrayList<Response> compose(Response... responses)
    {
        return new ArrayList<>(Arrays.asList(responses));
    }
}
