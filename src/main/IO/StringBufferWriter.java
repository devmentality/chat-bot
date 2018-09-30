package main.IO;

import main.IO.IMessageWriter;

import java.util.ArrayList;

public class StringBufferWriter implements IMessageWriter
{
    private ArrayList<String> buffer;

    public StringBufferWriter()
    {
        buffer = new ArrayList<String>();
    }

    public ArrayList<String> getBuffer()
    {
        return buffer;
    }

    @Override
    public void write(String message)
    {
        buffer.add(message);
    }
}
