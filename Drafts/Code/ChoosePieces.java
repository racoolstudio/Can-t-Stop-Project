import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChoosePieces extends JFrame {
    private int numPlayers;
    private JPanel selection;
    private final String[] shapes, colors, ai_;
    private ArrayList<JComboBox<String>> shapeLst, colorLst, aiLst;
    private ArrayList<JTextField> nameLst;
    private JMenuBar menuBar;
    private ArrayList<Player> playerLst;
    private JLabel dupeWarning;

    public ChoosePieces(int numPlayers) {
        this.numPlayers = numPlayers;
        this.setLayout(new BorderLayout());
        this.shapes = new String[] { " ", "Circle", "Square", "Triangle", "Star" };
        this.colors = new String[] { " ", "Blue", "Yellow", "Green", "Pink", "Orange", "Purple" };
        this.ai_ = new String[] { "Player", "AI Easy"};
        shapeLst = new ArrayList<>();
        colorLst = new ArrayList<>();
        aiLst = new ArrayList<>();
        nameLst = new ArrayList<>();
        playerLst = new ArrayList<>();
        JPanel header = new JPanel(new FlowLayout());
        header.setBackground(Color.red);

        JButton back = new JButton("<- Back");
        back.setFont(new Font("Calibre", Font.BOLD, 20));
        back.setOpaque(true);
        back.setBackground(Color.white);
        back.setForeground(Color.red);
        back.addActionListener(e -> back());

        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Calibre", Font.BOLD, 20));
        startGame.setOpaque(true);
        startGame.setBackground(Color.white);
        startGame.setForeground(Color.red);

        dupeWarning = new JLabel("Note: Each Shape, Color, Name must be unique for game to begin",
                SwingConstants.CENTER);
        dupeWarning.setFont(new Font("Calibre", Font.BOLD, 15));
        dupeWarning.setOpaque(true);
        dupeWarning.setBackground(Color.white);
        dupeWarning.setForeground(Color.red);
        header.add(back);
        header.add(startGame);
        startGame.addActionListener(e -> checkValidChoices());
        menuBar = new JMenuBar();
        JMenuItem tutorial = new JMenuItem("Tutorial");
        tutorial.addActionListener((e) -> tutorial());
        menuBar.add(tutorial);
        selection = new JPanel();
        setFrameSize();
        buildChoicePanel();

        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(selection, BorderLayout.CENTER);
        getContentPane().add(dupeWarning, BorderLayout.SOUTH);
        this.setJMenuBar(menuBar);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void back() {
        setVisible(false);
        new StartUp();
    }

    private void formatLabel(JLabel label) {
        label.setFont(new Font("Calibre", Font.BOLD, 25));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
    }

    //Creates panel where user inputs their settings
    private void buildChoicePanel() {
        for (int i = 0; i < numPlayers; i++) {
            JPanel background = new JPanel(new BorderLayout());
            JPanel choices = new JPanel(new GridLayout(4, 1));
            JPanel player = new JPanel(new FlowLayout());
            JLabel playerNumber = new JLabel("Player ".concat(Integer.toString(i + 1)));

            playerNumber.setFont(new Font("Calibre", Font.BOLD, 25));
            playerNumber.setOpaque(true);
            playerNumber.setBackground(Color.white);
            playerNumber.setForeground(Color.red);
            playerNumber.setBorder(BorderFactory.createLineBorder(Color.white));
            player.add(playerNumber);
            player.setBackground(Color.white);

            choices.setBorder(BorderFactory.createLineBorder(Color.white));
            JLabel color = new JLabel("Color: ");
            JLabel shape = new JLabel("Shape: ");
            JLabel name = new JLabel("Name: ");
            JLabel ai = new JLabel("");
            formatLabel(color);
            formatLabel(shape);
            formatLabel(name);
            formatLabel(ai);

            background.add(choices, BorderLayout.CENTER);
            background.add(player, BorderLayout.NORTH);
            selection.add(background);

            JComboBox<String> chooseShape = new JComboBox<>(shapes);
            chooseShape.setFont(new Font("Calibre", Font.BOLD, 20));
            chooseShape.setBackground(Color.white);
            shapeLst.add(chooseShape);

            JComboBox<String> chooseColor = new JComboBox<>(colors);
            chooseColor.setFont(new Font("Calibre", Font.BOLD, 20));
            chooseColor.setBackground(Color.white);
            colorLst.add(chooseColor);

            JTextField chooseName = new JTextField("Type your name here");
            chooseName.setFont(new Font("Calibre", Font.PLAIN, 20));
            chooseName.setBackground(Color.white);
            nameLst.add(chooseName);

            JComboBox<String> chooseAi = new JComboBox<>(ai_);
            chooseAi.setFont(new Font("Calibre", Font.BOLD, 20));
            chooseAi.setBackground(Color.white);
            aiLst.add(chooseAi);

            JRadioButton notAi = new JRadioButton("Player");
            notAi.setBackground(Color.red);
            notAi.setForeground(Color.white);
            notAi.setFont(new Font("Calibre", Font.BOLD, 20));

            JRadioButton aiEasy = new JRadioButton("AI Easy");
            aiEasy.setBackground(Color.red);
            aiEasy.setForeground(Color.white);
            aiEasy.setFont(new Font("Calibre", Font.BOLD, 20));

            JRadioButton aiHard = new JRadioButton("AI Hard");
            aiHard.setBackground(Color.red);
            aiHard.setForeground(Color.white);
            aiHard.setFont(new Font("Calibre", Font.BOLD, 20));

            JPanel panel = new JPanel(new FlowLayout());
            panel.setBackground(Color.red);
            choices.add(panel);
            panel.add(shape);
            panel.add(chooseShape);

            JPanel panel2 = new JPanel(new FlowLayout());
            panel2.setBackground(Color.red);
            choices.add(panel2);
            panel2.add(color);
            panel2.add(chooseColor);

            JPanel panel3 = new JPanel(new FlowLayout());
            panel3.setBackground(Color.red);
            choices.add(panel3);
            panel3.add(name);
            panel3.add(chooseName);

            JPanel panel4 = new JPanel(new FlowLayout());
            panel4.setBackground(Color.red);
            choices.add(panel4);
            panel4.add(chooseAi);

        }
    }

    private void setFrameSize() {
        if (numPlayers == 2) {
            this.setSize(new Dimension(800, 500));
            selection.setLayout(new GridLayout(1, 2));
        } else if (numPlayers == 3) {
            this.setSize(new Dimension(1200, 500));
            selection.setLayout(new GridLayout(1, 3));
        } else if (numPlayers == 4) {
            this.setSize(800, 900);
            selection.setLayout(new GridLayout(2, 2));
        }
    }

    private boolean checkDuplicates() {
        Set<String> setOfShapes = new HashSet<>();
        Set<String> setOfColors = new HashSet<>();
        Set<String> setOfNames = new HashSet<>();
        for (JComboBox<String> shape : shapeLst) {
            setOfShapes.add((String) shape.getSelectedItem());
        }
        for (JComboBox<String> color : colorLst) {
            setOfColors.add((String) color.getSelectedItem());
        }
        for (JTextField name : nameLst) {
            setOfNames.add(name.getText());
        }
        return setOfShapes.size() == shapeLst.size() && setOfColors.size() == colorLst.size()
                && setOfNames.size() == nameLst.size();
    }

    private boolean noBlanks() {
        for (JComboBox<String> shape : shapeLst) {
            if (shape.getSelectedItem().equals(" ")) {
                return false;
            }
        }
        for (JComboBox<String> color : colorLst) {
            if (color.getSelectedItem().equals(" ")) {
                return false;
            }
        }
        for (JTextField name : nameLst) {
            if (name.getText().equals("Type your name here")) {
                return false;
            }
        }
        return true;
    }

    private void createPlayer(int playerNumber) {
        String playerShape = (String) shapeLst.get(playerNumber).getSelectedItem();
        String playerColor = (String) colorLst.get(playerNumber).getSelectedItem();

        String playerType = (String) aiLst.get(playerNumber).getSelectedItem();
        String playerName = nameLst.get(playerNumber).getText();
        if (playerType.equals("Player")) {
            playerLst.add(new Player(playerShape, playerColor, playerName));
        } else {
            playerLst.add(new AI_Player(playerShape, playerColor, playerName));
        }
    }

    private void checkValidChoices() {
        if (checkDuplicates() && noBlanks()) {
            for (int i = 0; i < numPlayers; i++) {
                createPlayer(i);
            }
            setVisible(false);
            Game game = new Game(playerLst);
            game.displayTurnOrder();
        } else {
            dupeWarning
                    .setText("Make sure you have selected unique choices. (Name, Shape and Color must be different)");
            JOptionPane.showMessageDialog(null,
                    "Make sure you have selected unique choices. (Name, Shape and Color must be different)",
                    "Game Failed", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void tutorial() {
        new tutorial(null);
    }
}
