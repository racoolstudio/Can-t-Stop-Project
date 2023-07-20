import java.util.ArrayList;

import javax.swing.JButton;

public class Player {
    private String name, color, shape;
    private ArrayList<Integer> columnsCaptured;
    private ArrayList<pieces> piecesList = new ArrayList<pieces>();
    protected boolean AIPlayer;
    JButton rollButton;

    public Player(String shape, String color, String name) {
        this.shape = shape;
        this.name = name;
        this.color = color;
        this.AIPlayer = false;
        columnsCaptured = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            piecesList.add(null);
        }
    }

    public int getScore() {
        return columnsCaptured.size();
    }

    public void captureColumn(int column) {
        columnsCaptured.add(column);

    }

    public boolean checkWinner() {
        return (this.getScore() == 3) ? true : false;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public boolean getAI() {
        return AIPlayer;
    }

    public ArrayList<pieces> getPieces() {
        return piecesList;
    }

    public ArrayList<Integer> getColumns() {
        return columnsCaptured;
    }

    public pieces getPieceInColumn(int col) {
        if (this.piecesList.get(col - 2) == null) {
            return null;

        } else {
            return this.piecesList.get(col - 2);
        }
    }

    //Updates the players saved pieces once a turn ends
    public void updatePieces(ArrayList<pieces> runners) {
        runners.forEach(e -> {
            if (piecesList.get(e.getRow() - 1) == null) {
                pieces temp = new pieces(shape, color);
                temp.setLocation(e.getTile());
                piecesList.set(e.getRow() - 1, temp);
            } else {
                piecesList.get(e.getRow() - 1).setLocation(e.getTile());
            }
        });
    }

    public void rollButton(JButton rButton) {
        JButton rollButton = rButton;
    }

    public void clickRollButton() {
    }
}
