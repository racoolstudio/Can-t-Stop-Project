import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {
    private ArrayList<Player> players;
    private JPanel gridPanel;
    ArrayList<String> lines;

    public FileManager() {

    }

    // writeSave saves in following Format

    /*
     * currentTurn (single integer)
     * turnOrder (0,firstPlayer:1,secondPlayer:...)
     * player1 (name:shape:color:score:)
     * player1PieceLocations (x,y:x,y:....)
     * player2 (name:shape:color:score)
     * player2PieceLocations (x,y:x,y:....)
     * player3 (name:shape:color:score:)
     * player3PieceLocations (x,y:x,y:....)
     * player4 (name:shape:color:score:)
     * player4PieceLocations (x,y:x,y:....)
     */

    public void writeSave(Turn turn, ArrayList<Player> players) {
        this.players = players;
        try {
            String userDirectory = System.getProperty("user.dir");
            String fileDir = userDirectory + "\\CantStopSave.txt";
            File saveFile = new File(fileDir);
            FileWriter saveWriter = new FileWriter(fileDir);
            saveWriter.write(turn.getCurrentPlayerKey() + System.getProperty("line.separator"));
            String turnOrderString = "";
            for (int i = 1; i < players.size() + 1; i++) {
                turnOrderString += i + "," + turn.getTurnOrder().get(i).getName() + ":";
            }
            saveWriter.write(turnOrderString + System.getProperty("line.separator"));
            for (Player player : players) {
                String saveData = player.getName() + ":" + player.getShape() + ":" + player.getColor() + ":"
                        + player.getScore() + ":" + String.valueOf(player.getAI()) + System.getProperty("line.separator");
                for (pieces piece : player.getPieces()) {
                    if (piece != null) {
                        saveData += piece.getTile().getPosition() + ":";
                    } else {
                        saveData += "n:";
                    }
                }
                saveData += System.getProperty("line.separator");
                if (player.getColumns().size() > 0) {
                    for (int column : player.getColumns()) {
                        saveData += column + ":";
                    }
                } else {
                    saveData += "c:";
                }
                saveWriter.write(saveData + System.getProperty("line.separator"));
            }
            saveWriter.close();
            saveFile.createNewFile();

        } catch (IOException e) {
            System.out.println("File IO Error Occurred");
        }
    }

    public boolean isFile() {
        String userDirectory = System.getProperty("user.dir");
        File saveFile = new File(userDirectory + "\\CantStopSave.txt");
        if (saveFile.exists()) {
            try {
                Scanner myReader = new Scanner(saveFile);
                if (myReader.hasNextLine()) {
                    myReader.close();
                    return true;
                }
                myReader.close();
            } catch (IOException e) {
                System.out.println("IOException reading file");
            }
        }
        return false;
    }

    //Gets the data to display to the user from a save file if one exists
    public void loadSave() {
        String userDirectory = System.getProperty("user.dir");
        File saveFile = new File(userDirectory + "\\CantStopSave.txt");
        ArrayList<String> scores = new ArrayList<>();
        if (saveFile.exists()) {
            try {
                lines = new ArrayList<>();
                Scanner myReader = new Scanner(saveFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    lines.add(data);
                }
                myReader.close();
            } catch (IOException e) {
                System.out.println("IOException reading file");
            }
            players = new ArrayList<>();
            String[] p1 = lines.get(2).split(":");
            String[] p2 = lines.get(5).split(":");
            if (Boolean.parseBoolean(p1[4])){
                players.add(new AI_Player(p1[1], p1[2], p1[0]));
            }else{
                players.add(new Player(p1[1], p1[2], p1[0]));
            }
            scores.add(p1[3]);
            if (Boolean.parseBoolean(p2[4])){
                players.add(new AI_Player(p2[1], p2[2], p2[0]));
            }else{
                players.add(new Player(p2[1], p2[2], p2[0]));
            }
            scores.add(p2[3]);
            if (lines.size() > 8) {
                String[] p3 = lines.get(8).split(":");
                if (Boolean.parseBoolean(p2[4])){
                    players.add(new AI_Player(p3[1], p3[2], p3[0]));
                }else{
                    players.add(new Player(p3[1], p3[2], p3[0]));
                }
                scores.add(p3[3]);
            }
            if (lines.size() > 11) {
                String[] p4 = lines.get(11).split(":");
                if (Boolean.parseBoolean(p2[4])){
                    players.add(new AI_Player(p4[1], p4[2], p4[0]));
                }else{
                    players.add(new Player(p4[1], p4[2], p4[0]));
                }
                scores.add(p4[3]);
            }
            fileWindow(players, scores);
        } else {
            players = new ArrayList<>();
            fileWindow(players, scores);
        }
    }

    //Display for saved game information
    private void fileWindow(ArrayList<Player> playerLst, ArrayList<String> scores) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 200 + 100 * playerLst.size()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel gameSummary = new JLabel("Game Summary");
        gameSummary.setFont(new Font("Calibre", Font.BOLD, 25));
        gameSummary.setOpaque(true);
        gameSummary.setBackground(Color.white);
        gameSummary.setForeground(Color.red);
        gridPanel = new JPanel(new GridLayout(playerLst.size() + 1, 4));

        buildHeader();
        for (int i = 0; i < playerLst.size(); i++) {
            readInfo(playerLst.get(i), scores.get(i));
        }
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.red);
        JButton loadGame = new JButton("Load Game");
        loadGame.setFont(new Font("Calibre", Font.BOLD, 20));
        loadGame.setOpaque(true);
        loadGame.setBackground(Color.white);
        loadGame.setForeground(Color.red);
        loadGame.addActionListener(e -> loadGame(frame));

        JButton back = new JButton("<- Back");
        back.setFont(new Font("Calibre", Font.BOLD, 20));
        back.setOpaque(true);
        back.setBackground(Color.white);
        back.setForeground(Color.red);
        back.addActionListener(e -> newGame(frame));
        buttonPanel.add(back);
        buttonPanel.add(loadGame);

        panel.add(gameSummary, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.setBackground(Color.red);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void newGame(JFrame frame) {
        new StartUp();
        frame.setVisible(false);
    }

    //Logic which reads the save file and converts the data
    private void loadGame(JFrame frame) {
        Game game = new Game(players);
        ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations = new ArrayList<>();
        ArrayList<ArrayList<Integer>> capturedColumns = new ArrayList<>();
        ArrayList<String[]> tempPlayerPieces = new ArrayList<>();
        ArrayList<String[]> tempCapturedColumns = new ArrayList<>();
        tempPlayerPieces.add(lines.get(3).split(":"));
        tempCapturedColumns.add(lines.get(4).split(":"));
        tempPlayerPieces.add(lines.get(6).split(":"));
        tempCapturedColumns.add(lines.get(7).split(":"));
        if (lines.size() > 8) {
            tempPlayerPieces.add(lines.get(9).split(":"));
            tempCapturedColumns.add(lines.get(10).split(":"));
        }
        if (lines.size() > 11) {
            tempPlayerPieces.add(lines.get(12).split(":"));
            tempCapturedColumns.add(lines.get(13).split(":"));
        }
        for (String[] playerPieces : tempPlayerPieces) {
            ArrayList<ArrayList<Integer>> individualPieceLocations = new ArrayList<>();
            for (int i = 0; i < playerPieces.length; i++) {
                if (!playerPieces[i].equals("n")) {
                    String[] strLoc = playerPieces[i].split(",");
                    ArrayList<Integer> intLoc = new ArrayList<>();
                    for (int j = 0; j < strLoc.length; j++) {
                        intLoc.add(Integer.parseInt(strLoc[j]));
                    }
                    individualPieceLocations.add(intLoc);
                } else {
                    individualPieceLocations.add(null);
                }
            }
            pieceLocations.add(individualPieceLocations);
        }

        for (String[] columns : tempCapturedColumns) {
            ArrayList<Integer> cols = new ArrayList<>();
            if (columns[0].equals("c")) {
                capturedColumns.add(null);
            } else {
                for (int i = 0; i < columns.length; i++) {
                    cols.add(Integer.parseInt(columns[i]));
                }
                capturedColumns.add(cols);
            }
        }

        HashMap<Integer, Player> playOrder = new HashMap<>();
        String[] playOrderLst = lines.get(1).split(":");
        ArrayList<String[]> tempPlayParser = new ArrayList<>();
        for (String item : playOrderLst) {
            tempPlayParser.add(item.split(","));
        }
        for (int i = 0; i < tempPlayParser.size(); i++) {
            for (Player player : players) {
                if (player.getName().equals(tempPlayParser.get(i)[1])) {
                    playOrder.put(i + 1, player);
                }
            }
        }

        game.loadGame(pieceLocations, Integer.parseInt(lines.get(0)), playOrder, capturedColumns);

        frame.dispose();
    }

    private void buildHeader() {
        JLabel names = new JLabel("Player Name");
        JLabel colors = new JLabel("Colors");
        JLabel shapes = new JLabel("Shapes");
        JLabel scores = new JLabel("Scores");
        formatHeaders(names);
        formatHeaders(colors);
        formatHeaders(shapes);
        formatHeaders(scores);
    }

    private void formatHeaders(JLabel label) {
        label.setFont(new Font("Calibre", Font.BOLD, 25));
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setForeground(Color.red);
        label.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        gridPanel.add(label);
    }

    private void readInfo(Player player, String playerScore) {
        JLabel name = new JLabel(player.getName());
        JLabel color = new JLabel(player.getColor());
        JLabel shape = new JLabel(player.getShape());
        JLabel score = new JLabel(playerScore);
        formatLabel(name);
        formatLabel(color);
        formatLabel(shape);
        formatLabel(score);
    }

    private void formatLabel(JLabel label) {
        label.setFont(new Font("Calibre", Font.BOLD, 25));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        gridPanel.add(label);
    }
}