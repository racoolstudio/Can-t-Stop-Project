import javax.swing.*;
import java.awt.*;



public class pieces extends JButton{
    private String shape;
    private String color;
    private Tile location;
    public pieces(String shape, String color){

        //this.setPreferredSize(new Dimension(120,120));
        
        this.shape = shape;
        this.color = color;
        location = new Tile(-1,-1, false);
        this.setText(color);
        this.setBackground(Color.RED);
        this.setForeground(Color.getColor(color,Color.BLACK));
        setColor();    
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D CustomShape = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        CustomShape.setRenderingHints(hints);
        switch(this.shape){
            case "Square":
                CustomShape.fillRect(0,0, this.getWidth(), this.getHeight());
            break;
                
            case "Triangle":    
                int[] XCoords1 = {0, this.getWidth()/2, this.getWidth()}; 
                int[] YCoords1 = {this.getHeight(), 0, this.getHeight()}; 
                CustomShape.fillPolygon(XCoords1, YCoords1, 3);    
                break; 

            case "Circle":
                CustomShape.fillOval(0,0,this.getWidth()-2,this.getHeight()-2);
                break;    
                
            case "Star":
                int[] XCoords2 = {0,                  6*this.getWidth()/14,     this.getWidth()/2, 8*this.getWidth()/14,  this.getWidth(),    8*this.getWidth()/14, this.getWidth()/2, 6*this.getWidth()/14,};
                int[] YCoords2 = {this.getHeight()/2,8*this.getHeight()/14,  this.getHeight(),  8*this.getHeight()/14, this.getHeight()/2, 6*this.getHeight()/14,   0,                 6*this.getHeight()/14};
                CustomShape.fillPolygon(XCoords2, YCoords2, 8);
                break;

            case "Arrow":
                //System.out.println("ArrowGenerat");
                int[] XCoords3 = {this.getWidth()/6, this.getWidth()/2, 5*this.getWidth()/6,4*this.getWidth()/6,4*this.getWidth()/6,2*this.getWidth()/6,2*this.getWidth()/6};
                int[] YCoords3 = {2*this.getHeight()/3, 0, 2*this.getHeight()/3,2*this.getHeight()/3,this.getHeight(),this.getHeight(),2*this.getHeight()/3 }; 
                //int[] XCoords4 = {(1/6)*this.getWidth(), this.getWidth()/2, (5/6)*this.getWidth(), };
                //int[] YCoords4 = {(2/3)*this.getHeight(), 0, (2/3)*this.getHeight()}; 
                CustomShape.fillPolygon(XCoords3, YCoords3, 7);
                break;
        }            
    }

    private void setColor(){
        switch(this.color){
            case "Blue":
                this.setForeground(Color.BLUE);
                break;
            case "Yellow":
                this.setForeground(Color.YELLOW); 
                break;
            case "Green":
                this.setForeground(Color.GREEN);
                break;
            case "Pink":
                this.setForeground(Color.PINK);
                break;
            case "Orange":
                this.setForeground(Color.ORANGE);
                break;
            case "Purple":
                this.setForeground(new Color(255,0,255));
                break;
            case "White":
                this.setForeground(Color.WHITE);
            

        }
    }


    public void setLocation (Tile tile){
        location = tile;
    }
    public void setAttributes(String color, String shape){
        this.color = color;
        this.shape = shape;
        paintComponent(getGraphics());
        setColor();
    }

    public Tile getTile () {
        return location;
    }
    public int getRow(){
        return location.getRow();
    }
    public int getColumn(){
        return location.getColumn();
    }
}