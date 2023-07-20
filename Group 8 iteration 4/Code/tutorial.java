
import javax.swing.*;
import java.awt.*;

//Tutorial Display
public class tutorial extends JFrame{
    private JLabel description;
    private JLabel image ;
    private int currentPage =1;
    private JPanel panel;



    public tutorial(JFrame board){
        this.setSize(new Dimension(1400,700));
        this.setBackground(Color.RED);
        panel = new JPanel(new GridLayout(1,2));
        JPanel left = new JPanel(new GridBagLayout());
        JButton next = new JButton("next");
        JPanel right = new JPanel(new GridBagLayout());
        JButton prev = new JButton("prev");
        
        
  
        description = new JLabel();
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,20));
        description.setForeground(Color.RED);
        description.setBackground(Color.BLACK);

        image = new JLabel();
        image.setHorizontalAlignment(JLabel.CENTER);
        image.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,14));
        image.setForeground(Color.RED);
        image.setBackground(Color.BLACK);

        prev.setBackground(Color.white);
        prev.setForeground(Color.red);
        prev.setFont(new Font("Calibrie",Font.BOLD,25));

        next.setBackground(Color.white);
        next.setForeground(Color.red);
        next.setFont(new Font("Calibrie",Font.BOLD,25));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;

        panel.setBackground(Color.white);
        
        page1();
        
        
        left.add(prev);
        prev.addActionListener(e -> prev());
        right.add(next);
        next.addActionListener(e -> next());

        

        panel.add(image);
        
        panel.add(description);

        panel.setBorder(BorderFactory.createLineBorder(Color.red,20));
        this.getContentPane().add(panel,BorderLayout.CENTER);
        left.add(prev, c);
        right.add(next, c);
        left.setBackground(Color.red);
        right.setBackground(Color.red);



        this.getContentPane().add(left,BorderLayout.WEST);
        this.getContentPane().add(right,BorderLayout.EAST);
        setLocationRelativeTo(null);
        this.setVisible(true);
        

        
    }
    private void page1(){
        description.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,20));
        description.setText("<html>1. Select the number of players. <br/> 2. Load a previously saved game. <br/> 3. Start a new Game with selected number of players. </html>");
        ImageIcon image3 = new ImageIcon("display1_image.png");
        Image image2 = image3.getImage().getScaledInstance(600,600,4);
        image.setIcon(new ImageIcon(image2));
    }

    private void page2(){
        description.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,20));
        description.setText("<html>1. Go Back to main menu. <br/> 2. Begin Game (Note: you can only do this after you have entered the details in player fields). <br/> 3. Enter the player name and piece details for each player. Make sure all the values are unique for all players </html>");
        ImageIcon image3 = new ImageIcon("display2_image.png");
        Image image2 = image3.getImage().getScaledInstance(600,600,4);
        image.setIcon(new ImageIcon(image2));

    }

    private void page3(){
        image.setText("");
        description.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,20));
        description.setText("<html>1. a) New game: starts a new game, all unsaved game progress will be lost. <br/>   1. b)Save Game: saves the current game progress including player name, player pieces(6), captured columns(4),runners(5) and player scores(3).<br/>   1. c) Quit: quits the game and deletes all unsaved game progress. <br/>"+ 
        "2. This is the dice area where you can roll a dice and choose dice combinations. You can also choose to end your turn. <br/> 3. This is the player names of each player accompanied by their score, where the score is the number of columns capture.A white background indicate the current player turn.<br/> 4. A captured column denoted by gray tiles and player piece at the top of the column to indicate the player that captured it.<br/>5. A Runner(more information on next page)<br/>6. A player piece(more information on next page)"+
        " </html>");
        ImageIcon image3 = new ImageIcon("display3_image.png");
        Image image2 = image3.getImage().getScaledInstance(600,600,4);
        image.setIcon(new ImageIcon(image2));

    }
    private void page4(){
        description.setFont(new Font("Arial", Font.BOLD + Font.ITALIC,14));
        description.setText("<html><p>Once you play a white cube in a column, it stays in that column until your turn ends. You cannot move a white cube from one column to another, nor can you remove it from the board during your turn. As the game progresses, you’ll often find that you can only legally make one move, rather than two. This is OK. However, you must always play to as many columns as your roll allows; you can’t choose to ignore dice that allow a legal move. If your roll allows it, you can play twice in a single column. For example, if you roll 2, 4, 4, 6, you could make two moves in column 8 (4+4 and 2+6), if available. </p>"+ 
         "<p>After every dice roll, you are met with the option to roll again or end your turn.</p> <h3>Ending a turn:</h3> <p>If you choose to end your turn, the runners are replaced with your own pieces. If you already have a piece on any of these columns, just move them up to their new positions. (Yes, several players’ pieces can occupy the same space.) Then pass the dice and the white cubes to the player on your left; it is now their turn.</p>"+
         "<h3>Going bust:</h3> <p>To continue your turn, you must place or advance at least one white cube on the board, according to the rules above, after rolling the four dice. If you cannot legally place or advance any white cubes using your roll, you have gone bust. You do not get to advance any of your cubes this turn and your turn is over.</p>"+
         "<h3>Claiming a column:</h3> <p>If you have moved a runner to the top of a column (on the numbered square) when you choose to end your turn, then you have claimed that column. Put your own piece on that space and all other players pieces are removed. For the rest of the game, no player can place a piece in that column. That goes for you, too! That column’s number has effectively ceased to exist. Play accordingly. If you end your turn with three columns claimed, you have won the game.</p>"+
         "</html>");
        image.setIcon(null);
        image.setText("<html><h3>Goal:</h3><p>The goal of this game is to “claim” three of the game board’s numbered columns. You claim a column if you can move one of your runners (white arrows) from the bottom of that column to the top, according to the rules of the game. As soon as any player claims three columns, they win the game.</p>"+
        "<h3>Setup:</h3><p>Following the setup menu, each player must select their piece shape, color, and enter their name. Note that each criteria must be unique for the game to begin. Prior to starting, dices are rolled for each player and turn order will be shown before starting. When a players name has a white background, then it is their turn to play.</p>"+
        "<h3>On your turn:</h3><p>Each turn you are allowed three runners. Your goal during your turn is to advance as far as you can up one, two, or three columns. You’ll use the white cubes to mark your progress during your turn. After every roll you’ll need to decide whether to stop and keep your progress, or try to advance even further at the risk of losing your progress.</p>"+
        "<h3>Rolling:</h3><p> Selecting the Roll Dice button will roll a combination for your turn. Roll all four dice, then combine them into two pairs, any way you wish. Example:If you roll 3, 4, 2, 6, then you can make these combinations: - 3+4 and 2+6 (7 and 8) - 3+2 and 4+6 (5 and 10) - 3+6 and 4+2 (9 and 6)</p>"+
        "<p>Having thus chosen two sums between 2 and 12, advance a white cube in those columns, according to these rules: - If you have already placed a white cube in that column (on an earlier roll during this same turn), advance it one space. - If one of the three white cubes is not on the board yet, place it in that column: - If you have a cube of your color in that column (from a previous turn), place the white cube one space ahead of it. - Otherwise, place the white cube in the bottom space of that column. - Other players’ cubes, if already on a space, can share that space with a white cube.</p></html>");

    }
    private void prev(){
        if (currentPage == 1){
            
        }else if(currentPage == 2){
            currentPage = 1;
            page1();
        }else if(currentPage == 3){
            currentPage = 2;
            page2();

        }else if(currentPage == 4){
            currentPage = 3;
            page3();

        }

    }
    private void next(){
        if (currentPage == 1){
            currentPage = 2;
            page2();
            
        }else if(currentPage == 2){
            currentPage = 3;
            page3();

        }else if(currentPage == 3){
            currentPage = 4;
            page4();

        }else if(currentPage == 4){

        }
        
    }
    
}