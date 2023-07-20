import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;

    public Game(ArrayList<Player> players){
        this.players = players;
        dice = new Dice();
        board = new Board(this,players);
        turn = new Turn(players, dice,board);
    }

    public Turn getTurn(){
        return turn;
    }

    public void saveGame(){
        FileManager fm = new FileManager();
        fm.writeSave(turn, players);
        ImageIcon originalIcon = new ImageIcon(Game.class.getResource("success.png"));
        ImageIcon resizedIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, "Game saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
    }

    public void loadGame(ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations, int currentPlayer, HashMap<Integer, Player> turnOrder){
        turn.setCurrentPlayerkey(currentPlayer);
        turn.setTurnOrder(turnOrder);
        for (int i = 0; i < players.size(); i++){
            ArrayList<pieces> pieces = players.get(i).getPieces();
            ArrayList<ArrayList<Integer>> playerPieceLocations = pieceLocations.get(i);
            for (int j = 0; j< playerPieceLocations.size(); j++){
                int x = playerPieceLocations.get(j).get(0);
                int y = playerPieceLocations.get(j).get(1);
                if (x >=0){
                    pieces.get(j).setLocation(board.getTile(x, y));
                }
            }
        }
    }

    public static void main(String[] argv){
        Player p1 = new  Player("Square","Green","chaman1");
        Player p2 = new  Player("Triangle","Orange","chaman2");
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(p1);
        list.add(p2);
        Game game = new Game(list);
    }
}
