import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Board extends JFrame{
    private Tile[][] board;
    private ArrayList<Player> players;
    public Board(ArrayList<Player> players){
        this.players = players;
        this.setSize(new Dimension(1000,900));
        getContentPane().setLayout(new BorderLayout());
        buildBoard();
        buildSide();
        setResizable(true);
        setVisible(true);

    }
    private void buildBoard(){
        JPanel boardPanel = new JPanel(new GridLayout(13,13));
        boardPanel.setBackground(new Color(149, 240, 252));
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        board = new Tile[13][13];
        for (int x=0; x<13; x++) {
            for (int y = 0; y < 13; y++) {
                if (checkTile(x,y)){
                    board[x][y] = new Tile(x, y, true);
                    board[x][y].setFont(new Font("Calibre", Font.BOLD,20));
                    board[x][y].setForeground(Color.red);
                }
                else {board[x][y] = new Tile(x, y, false);}
                setLabels(x,y);
                boardPanel.add(board[x][y]);
            }
        }


    }
    private void setLabels(int x, int y){
        if ((x==5&&y==1)){
            board[x][y].setText("2");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==4&&y==2)){
            board[x][y].setText("3");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==3&&y==3)){
            board[x][y].setText("4");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==2&&y==4)){
            board[x][y].setText("5");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==1&&y==5)){
            board[x][y].setText("6");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==0&&y==6)){
            board[x][y].setText("7");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==1&&y==7)){
            board[x][y].setText("8");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==2&&y==8)){
            board[x][y].setText("9");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==3&&y==9)){
            board[x][y].setText("10");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==4&&y==10)){
            board[x][y].setText("11");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==5&&y==11)){
            board[x][y].setText("12");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
    }

    private boolean checkTile(int x, int y){
        if (y==0 || y==12) {return false;}
        else if ((y==1 || y==11)&& (x<5 || x>7)){return false;}
        else if (((y==2 || y==10)&& (x<4 || x>8))){return false;}
        else if ((y==3 || y==9)&& (x<3 || x>9)){return false;}
        else if ((y==4 || y==8)&& (x<2 || x>10)){return false;}
        else if ((y==5 || y==7)&& (x<1 || x>11)){return false;}
        else {return true;}
    }

    private void buildSide(){
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JPanel otherPanel = new JPanel(new FlowLayout());
        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel infoPanel = new JPanel(new GridLayout(players.size()+1,3));
        gamePanel.setBackground(Color.red);
        otherPanel.setBackground(Color.red);
        infoPanel.setBackground(Color.red);

        JButton sim = new JButton("Click here to simulate a winner");
        sim.setFont(new Font("Calibre",Font.BOLD,20));
        sim.setOpaque(true);
        sim.setBackground(Color.white);
        sim.setForeground(Color.red);
        sim.addActionListener((ActionEvent e) -> {displayWinner();});
        otherPanel.add(sim);

        JButton dice = new JButton("Roll Dice");
        dice.setFont(new Font("Calibre",Font.BOLD,20));
        dice.setOpaque(true);
        dice.setBackground(Color.white);
        dice.setForeground(Color.red);
        gamePanel.add(dice);

        JButton endTurn = new JButton("End Turn");
        endTurn.setFont(new Font("Calibre",Font.BOLD,20));
        endTurn.setOpaque(true);
        endTurn.setBackground(Color.white);
        endTurn.setForeground(Color.red);
        gamePanel.add(endTurn);

        JLabel playersNames = new JLabel("Players");
        formatLabel(playersNames);
        infoPanel.add(playersNames);
        JLabel scores = new JLabel("Scores");
        formatLabel(scores);
        infoPanel.add(scores);

        for (Player player : players){
            JLabel playerName = new JLabel(player.getName());
            formatLabel(playerName);
            infoPanel.add(playerName);
            JLabel playerScore = new JLabel(Integer.toString(player.getScore()));
            formatLabel(playerScore);
            infoPanel.add(playerScore);
        }

        buttonPanel.add(otherPanel);
        buttonPanel.add(gamePanel);
        buttonPanel.add(infoPanel);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }
    
    private void formatLabel(JLabel label){
        label.setFont(new Font("Calibre",Font.BOLD,18));
        label.setBorder(BorderFactory.createLineBorder(Color.white));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
    }

    //Temp simulator until some missing classes are implemented. Actual logic is in Player
    private void displayWinner(){
        Player winner = players.get((int) Math.random()*players.size());
        winningDisplay winningmessage = new winningDisplay(this,winner);
    }
}
