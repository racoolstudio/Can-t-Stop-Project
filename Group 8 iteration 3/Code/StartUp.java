import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class StartUp extends JFrame {
    private String[] numberOptions;
    private JComboBox<String> dropDown;
    private Timer timer;
    private JLabel cantStop;
    private JPanel start;
    private FileManager file;

    public StartUp(){
        this.setLayout(new GridLayout(3,1));
        this.setSize(new Dimension(600,600));
        numberOptions = new String[]{" ","2","3","4"};
        file = new FileManager();
        buildPanels();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void buildPanels(){
        JPanel title = new JPanel(new GridBagLayout());
        title.setBackground(Color.red);
        JPanel select = new JPanel(new FlowLayout());
        select.setBackground(Color.red);
        start = new JPanel(new FlowLayout());
        start.setBackground(Color.red);

        cantStop = new JLabel("");
        cantStop.setFont(new Font("Calibre",Font.BOLD,50));
        cantStop.setOpaque(true);
        cantStop.setBackground(Color.red);
        cantStop.setForeground(Color.white);

        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Calibre",Font.BOLD,20));
        startGame.setOpaque(true);
        startGame.setBackground(Color.white);
        startGame.setForeground(Color.red);
        startGame.addActionListener(e -> setNumPlayers());


        JLabel numPlayers = new JLabel("Number of Players: ");
        numPlayers.setFont(new Font("Calibre",Font.BOLD,25));
        numPlayers.setOpaque(true);
        numPlayers.setBackground(Color.red);
        numPlayers.setForeground(Color.white);

        dropDown = new JComboBox<>(numberOptions);
        dropDown.setFont(new Font("Calibre",Font.BOLD,25));
        dropDown.setBackground(Color.white);

        ArrayList<String> titleList = new ArrayList<String>(Arrays.asList("C","a","n","'","t"," ","S","t","o","p","!"));
        ArrayList<String> displayList = new ArrayList<String>();
        timer = new Timer(100, (ActionEvent e) -> {renderTitle(titleList,displayList);});
        timer.start();
        getContentPane().add(title);
        getContentPane().add(select);
        getContentPane().add(start);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        title.add(cantStop, c);
        select.add(numPlayers);
        select.add(dropDown);
        start.add(startGame);

        if(file.isFile()){
            JButton loadGame = new JButton("Load Game");
            loadGame.setFont(new Font("Calibre",Font.BOLD,20));
            loadGame.setOpaque(true);
            loadGame.setBackground(Color.white);
            loadGame.setForeground(Color.red);
            loadGame.addActionListener(e -> loadSaveGame());
            start.add(loadGame);}


    }

    private void loadSaveGame() {
        FileManager file = new FileManager();
        setVisible(false);
        file.loadSave();
        }
        /*else{
            JLabel noGame = new JLabel("       No Saved Games       ");
            noGame.setFont(new Font("Calibre",Font.BOLD,25));
            noGame.setForeground(Color.white);
            start.add(noGame);

        }*/


    public void setNumPlayers() {
        if (!(dropDown.getSelectedItem()).equals(" ")){
            int numPlayers = Integer.parseInt((String) dropDown.getSelectedItem());
            ChoosePieces choose = new ChoosePieces(numPlayers);
            setVisible(false);}
    }

    public void renderTitle(ArrayList<String> title, ArrayList<String> displayed){
        if (title.size() == displayed.size()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            displayed.clear();
            cantStop.setText("");
        }
        else {
            displayed.add(title.get(displayed.size()));
            //System.out.println(String.join("",displayed));
            cantStop.setText(String.join("",displayed));
        }
    }

    public static void main(String[] argv){
        StartUp start = new StartUp();
    }
}
