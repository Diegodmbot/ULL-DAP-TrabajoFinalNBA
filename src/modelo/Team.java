package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    private int id;
    private String name;
    private String shortName;
    private ArrayList<Player> players;
    private String conference;
    private String crestUrl;
    HashMap<Result, Integer> results;
    private int ranks;
}
