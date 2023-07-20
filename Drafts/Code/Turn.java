import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;
    private Board board;
    // Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g
    // 1,2,3,4) indicating the order they move in. Values are the Player objects.
    private HashMap<Integer, Player> turnOrder;
    private ArrayList<pieces> runners = new ArrayList<pieces>();
    private ArrayList<Integer> capturedColumns = new ArrayList<Integer>();

    public Turn(ArrayList<Player> players, Dice dice, Board board) {
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.setTurnOrder(new HashMap<Integer, Player>());
        currentTurn = 1;
    }

    //Decides the play order for the players
    public void setTurnOrder(HashMap<Integer, Player> order) {
        HashMap<Integer, Player> turnOrderSetter = new HashMap<Integer, Player>();
        if (order.size() != 0) {
            turnOrder = order;
        } else {
            ArrayList<Integer> orderRolls = dice.getTurnOrderRolls(players.size());
            for (int i = 0; i < orderRolls.size(); i++) {
                int highest_remaining = Collections.max(orderRolls);
                int index = orderRolls.indexOf(highest_remaining);
                turnOrderSetter.put(i + 1, players.get(index));
                orderRolls.set(index, 0);
            }
            turnOrder = turnOrderSetter;
        }
    }

    public int getCurrentPlayerKey() {
        return currentTurn;
    }

    public void setCurrentPlayerkey(int key) {
        currentTurn = key;
    }

    public HashMap<Integer, Player> getTurnOrder() {
        return turnOrder;
    }

    // Called when player rolls dice on their turn
    public ArrayList<ArrayList<Integer>> getDiceCombinations() {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<ArrayList<Integer>> validCombos = new ArrayList<>();
        ArrayList<Integer> values = dice.makeTurnRoll();

        // Stores the dice pairs into combinations as ordered ArrayList. So [1,2,3,4]
        // means the pairs 1,2 + 3,4
        validCombos.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));

        for (ArrayList<Integer> combo : combinations) {
            int diceRoll1 = combo.get(0) + combo.get(1);
            int diceRoll2 = combo.get(2) + combo.get(3);
            if (isColValid(diceRoll1) || isColValid(diceRoll2)) {
                validCombos.add(combo);

            }
        }
        return validCombos;
    }

    private boolean isColValid(int diceRoll) {
        if (runners.size() == 0) {
            if (freeColumn(diceRoll)) {
                return true;
            }
        } else if (runners.size() == 1 || runners.size() == 2) {
            if (freeColumn(diceRoll) || hasRunner(diceRoll)) {
                return true;
            }
        } else if (runners.size() == 3) {
            if (hasRunner(diceRoll)) {
                return true;
            }
        }
        return false;
    }

    private boolean freeColumn(int diceRoll) {
        return !hasRunner(diceRoll) && checkNotCaptured(diceRoll);
    }

    public boolean checkNotCaptured(int diceRoll) {
        for (Player player : players) {
            ArrayList<Integer> columns = player.getColumns();
            for (int columnNum : columns) {
                if (columnNum == diceRoll) {
                    return false;
                }
            }
        }
        return true;
    }

    public int runnerCount() {
        return runners.size();
    }

    // Called when a player selects their dice combination and moves their pieces
    // appropriately
    public void movePiece(int diceRoll) {
        for (pieces runner : runners) {

            if (runner.getRow() + 1 == diceRoll
                    && !(board.getTile(runner.getColumn(), runner.getRow()).checkEndTile())) {

                runner.setLocation(board.getTile(runner.getRow(), runner.getColumn() - 1));
                if (board.getTile(runner.getColumn(), runner.getRow()).checkEndTile()) {
                    runner.cantMove();
                }
                board.updateGameBoard(runners);
                return;
            }
        }
        if (runners.size() != 3) {
            pieces runner1 = new pieces("Arrow", "White");
            if (turnOrder.get(currentTurn).getPieceInColumn(diceRoll) != null) {
                pieces temp = turnOrder.get(currentTurn).getPieceInColumn(diceRoll);
                runner1.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
            } else {
                runner1.setLocation(board.getTile(diceRoll - 1, 13 - Math.abs(7 - diceRoll) - 1));
            }

            if (!capturedColumns.contains(runner1.getRow() + 1)) {
                runners.add(runner1);
            }
        }
        board.updateGameBoard(runners);

    }

    public void addCapturedColumn(int col) {
        capturedColumns.add(col);
    }

    public ArrayList<Integer> getCapturedColumn() {
        return capturedColumns;
    }

    //Updates the pieces, captures any columns, etc.. once a player ends their turn
    public void endTurn() {
        int num = turnOrder.size();

        Player current_player = turnOrder.get(currentTurn);
        current_player.updatePieces(runners);
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                if (board.getTile(x, y).checkEndTile()) {

                }

            }
        }
        runners.forEach((e) -> {
            if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
                current_player.captureColumn(e.getRow() + 1);
                capturedColumns.add(e.getRow() + 1);
                board.capturedColumn(e.getRow());
                board.updateScores();
                if (current_player.checkWinner()) {
                    new winningDisplay(board, current_player);
                }
                ;
            }
        });

        board.removeRunners(runners);

        board.updateGameBoard(current_player.getPieces());
        runners = new ArrayList<pieces>();
        if (currentTurn == num) {
            currentTurn = 1;
        } else {
            currentTurn += 1;
        }
        board.setCurrentPlayer();

        if (getCurrentplayer().getAI()) {
            AIGamePlay();
        }

    }

    public Player getCurrentplayer() {
        Player current_player = turnOrder.get(currentTurn);
        return current_player;
    }

    public boolean hasRunner(int diceRoll) {
        ArrayList<Integer> valids = new ArrayList<>();
        runners.forEach(e -> valids.add(e.getRow() + 1));
        for (int valid : valids) {
            if (valid == diceRoll) {
                return true;
            }
        }
        return false;
    }

    //Logic for removing runners if a player busts
    public void endTurnBust() {
        int num = turnOrder.size();
        if (currentTurn == num) {
            currentTurn = 1;
        } else {
            currentTurn += 1;
        }
        board.movePiece(0, 0);
        board.removeRunners(runners);
        runners = new ArrayList<pieces>();
        if (getCurrentplayer().getAI()) {
            AIGamePlay();
        }
    }

    //Logic for AI player
    public void AIGamePlay() {
        for (int i = 0; i < 3; i++) {

            board.rollDiceAction();

            if (board.validChoice.size() == 0) {
                break;
            }

            board.validChoice.get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button clicked!");
                }
            });

            ActionListener[] actionListeners = board.validChoice.get(0).getActionListeners();
            for (ActionListener listener : actionListeners) {
                listener.actionPerformed(new ActionEvent(board.validChoice.get(0),
                        ActionEvent.ACTION_PERFORMED, ""));
            }

            System.out.println("lenght is" + board.validChoice.size());

        }

        endTurn();
        board.validChoice.clear();
    }
}
