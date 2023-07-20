import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;

//Game Controller
public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;

    public Game(ArrayList<Player> players) {
        this.players = players;
        dice = new Dice();
        board = new Board(this, players);
        turn = new Turn(players, dice, board);
        board.setCurrentPlayer();
        if (turn.getCurrentplayer().getAI()) {
            turn.AIGamePlay();
        }
    }

    public void endTurn() {
        turn.endTurn();

    }

    public Turn getTurn() {
        return turn;
    }

    //Shows window with play order on game start
    public void displayTurnOrder() {
        HashMap<Integer, Player> turnOrder = turn.getTurnOrder();
        String turnOrderMessage = "Turn Order is" + System.getProperty("line.separator");
        for (int i = 0; i < players.size(); i++) {
            turnOrderMessage += (i + 1) + ": " + turnOrder.get(i + 1).getName() + System.getProperty("line.separator");
        }
        JOptionPane.showMessageDialog(null, turnOrderMessage, "Turn Order", JOptionPane.INFORMATION_MESSAGE);
    }

    //Save confirmation pop-up
    public void saveGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the game?", "Save Game",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Process further if user confirms
            FileManager fm = new FileManager();
            fm.writeSave(turn, players);
            ImageIcon originalIcon = new ImageIcon(Game.class.getResource("success.png"));
            ImageIcon resizedIcon = new ImageIcon(
                    originalIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            JOptionPane.showMessageDialog(null, "Game saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE,
                    resizedIcon);

        }
    }

    //Gets data from fileManager and passes it around to load a game
    public void loadGame(ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations, int currentPlayer,
            HashMap<Integer, Player> turnOrder, ArrayList<ArrayList<Integer>> capturedColumns) {
        turn.setCurrentPlayerkey(currentPlayer);
        turn.setTurnOrder(turnOrder);
        for (int i = 0; i < players.size(); i++) {
            ArrayList<pieces> pieces = players.get(i).getPieces();
            ArrayList<ArrayList<Integer>> playerPieceLocations = pieceLocations.get(i);
            for (int j = 0; j < playerPieceLocations.size(); j++) {
                if (playerPieceLocations.get(j) != null) {
                    int x = playerPieceLocations.get(j).get(0);
                    int y = playerPieceLocations.get(j).get(1);
                    pieces.set(j, new pieces(players.get(i).getShape(), players.get(i).getColor()));
                    pieces.get(j).setLocation(board.getTile(x, y));
                }
            }
            board.updateGameBoard(pieces);
        }

        for (int i = 0; i < players.size(); i++) {
            ArrayList<Integer> playerCaptured = capturedColumns.get(i);
            if (playerCaptured != null) {
                for (int j = 0; j < playerCaptured.size(); j++) {
                    players.get(i).captureColumn(playerCaptured.get(j));
                    turn.addCapturedColumn(playerCaptured.get(j));
                    board.capturedColumn(playerCaptured.get(j) - 1);
                    board.updateScores();
                }
            }
        }
        this.displayTurnOrder();
    }

    public Player getCurrentPlayer() {
        return turn.getTurnOrder().get(turn.getCurrentPlayerKey());
    }

    public int checkRunners() {
        return turn.runnerCount();
    }
}
