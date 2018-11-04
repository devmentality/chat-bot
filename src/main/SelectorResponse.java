package main;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectorResponse extends Response
{
    private String preamble;
    private ArrayList<String> options;

    public SelectorResponse(long receiverId, String preamble, String... options)
    {
        super(receiverId);
        this.preamble = preamble;
        this.options = new ArrayList<>(Arrays.asList(options));
    }

    public void addOption(String option)
    {
        options.add(option);
    }

    public String getPreamble()
    {
        return preamble;
    }

    public ArrayList<String> getOptions()
    {
        return options;
    }
}
