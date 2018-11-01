package main;

import java.util.ArrayList;

public class Response
{
    private int receiverId;
    private ArrayList<String> content;
    private ResponseType type;

    public Response(int receiverId)
    {
        this.receiverId = receiverId;
        this.content = new ArrayList<>();
        this.type = ResponseType.PLAIN_TEXT;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public ArrayList<String> getContent()
    {
        return content;
    }

    public void addLineToContent(String line)
    {
        content.add(line);
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
