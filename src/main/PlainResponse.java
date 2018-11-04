package main;

import java.util.ArrayList;
import java.util.Arrays;

public class PlainResponse extends Response
{
    private ArrayList<String> content;

    public PlainResponse(long receiverId, String... content)
    {
        super(receiverId);
        this.content = new ArrayList<>(Arrays.asList(content));
    }

    public void addMessageToContent(String message)
    {
        content.add(message);
    }

    public ArrayList<String> getContent()
    {
        return content;
    }
}
