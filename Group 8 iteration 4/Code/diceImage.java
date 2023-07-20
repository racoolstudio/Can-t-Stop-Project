import javax.swing.*;
import java.awt.*;


//Creates the dice faces which appear when dice are rolled
public class diceImage extends JButton{
    int number;

    public diceImage(int number){
        this.number = number;
        this.setPreferredSize(new Dimension(60,60));
        this.setBackground(Color.RED);
        this.setForeground(Color.BLACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D CustomShape = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        CustomShape.setRenderingHints(hints);
        switch(this.number){
            case 1:
                CustomShape.fillOval(25,25,10,10);
            break;
                
            case 2:    
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
            break; 

            case 3:
                CustomShape.fillOval(25,25,10,10);
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
            break;    
                
            case 4:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);    
            break;

            case 5:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);
                CustomShape.fillOval(25,25,10,10);
            break;

            case 6:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,25,10,10); 
                CustomShape.fillOval(40,25,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);    
            break;

            }
    }
}
