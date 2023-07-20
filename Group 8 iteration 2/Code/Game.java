import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;

    //Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g 1,2,3,4) indicating the order they move in. Values are the Player objects. 
    private HashMap<Integer,Player> turnOrder;

    public Game(ArrayList<Player> players){
        this.players = players;
        dice = new Dice();
        turn = new Turn(players, dice);
        board = new Board(players);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void printInfo(){
        System.out.println(players);
    }
}
