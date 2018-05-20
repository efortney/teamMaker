import java.util.ArrayList;

public class Team {

    ArrayList<String> players = new ArrayList<>();
    String name;
    int wins;
    int losses;

    public Team() {
        name = "Team";
        wins = 0;
        losses = 0;
    }

    protected void addPlayer(String p) {
        this.players.add(p);
    }

    protected void setName(String n ) {
        this.name = n;
    }


    @Override
    public String toString() {
        return  name + " members: " + this.players;
    }
}
