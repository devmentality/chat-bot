package main.Data;

import main.GameLogic.Game;
import main.GameLogic.GameResult;

import java.util.ArrayList;

public class User implements Cloneable
{
    public int id;
    public String username;
    public Game unfinishedGame;
    public ArrayList<GameResult> gameResults;


}
