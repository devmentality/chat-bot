package main;

public class StringBufferReader implements IMessageReader
{
    private String[] messages;
    private int nextMessageIndex;

    public StringBufferReader(String[] messages)
    {
        this.messages = messages.clone();
        nextMessageIndex = 0;
    }

    @Override
    public String read()
    {
        if (nextMessageIndex >= messages.length)
            return "";

        String message = messages[nextMessageIndex];
        nextMessageIndex++;
        return message;
    }
}
