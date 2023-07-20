import javax.swing.*;
import java.awt.*;

//Tiles present on board
public class Tile extends JPanel {
    private int x, y;
    private boolean endTile;

    public Tile(int x, int y, boolean gameTile) {
        this.x = x;
        this.y = y;
        setLayout(new GridLayout(1, 1));
        endTile = false;

        setOpaque(true);
        if (gameTile) {
            this.setBackground(Color.red);
        } else {
            this.setVisible(false);
        }
    }

    public boolean checkEndTile() {
        return endTile;
    }

    public void setEndTile() {
        endTile = true;
    }

    public String getPosition() {
        return x + "," + y;
    }

    public int getXValue() {
        return this.x;
    }

    public int getYValue() {
        return this.y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // UpperLeft part of game
        if ((getXValue() == 4 && getYValue() == 1) || (getXValue() == 3 && getYValue() == 2)
                || (getXValue() == 2 && getYValue() == 3) || (getXValue() == 1 && getYValue() == 4)
                || (getXValue() == 0 && getYValue() == 5) || (getXValue() == 5 && getYValue() == 0)) {

            super.paintComponent(g);
            int[] xPoints = { getWidth(), 0, getWidth() }; // x-coordinates of triangle vertices
            int[] yPoints = { getHeight(), getHeight(), 0 }; // y-coordinates of triangle vertices
            setBackground(new Color(149, 240, 252));
            g.setColor(Color.WHITE); // set color to white
            g.fillPolygon(xPoints, yPoints, 3); // fill triangle
        }
        // Upper right part of game
        else if ((getXValue() == 4 && getYValue() == 11) || (getXValue() == 3 && getYValue() == 10)
                || (getXValue() == 2 && getYValue() == 9) || (getXValue() == 1 && getYValue() == 8)
                || (getXValue() == 0 && getYValue() == 7) || (getXValue() == 5 && getYValue() == 12)) {

            super.paintComponent(g);
            int[] xPoints = { getWidth(), 0, 0 }; // x-coordinates of triangle vertices
            int[] yPoints = { getHeight(), getHeight(), 0 }; // y-coordinates of triangle vertices
            setBackground(new Color(149, 240, 252));
            g.setColor(Color.WHITE); // set color to white
            g.fillPolygon(xPoints, yPoints, 3); // fill triangle
        }
        // Lower left part of game
        else if ((getXValue() == 8 && getYValue() == 0) || (getXValue() == 9 && getYValue() == 1)
                || (getXValue() == 10 && getYValue() == 2) || (getXValue() == 11 && getYValue() == 3)
                || (getXValue() == 12 && getYValue() == 4)) {

            super.paintComponent(g);
            int[] xPoints = { getWidth(), getWidth(), 0 }; // x-coordinates of triangle vertices
            int[] yPoints = { getHeight(), 0, 0 }; // y-coordinates of triangle vertices
            setBackground(new Color(149, 240, 252));
            g.setColor(Color.WHITE); // set color to white
            g.fillPolygon(xPoints, yPoints, 3); // fill triangle
        }
        // Lower Right part of game
        else if ((getXValue() == 8 && getYValue() == 12) || (getXValue() == 9 && getYValue() == 11)
                || (getXValue() == 10 && getYValue() == 10) || (getXValue() == 11 && getYValue() == 9)
                || (getXValue() == 12 && getYValue() == 8)) {

            super.paintComponent(g);
            int[] xPoints = { 0, 0, getWidth() }; // x-coordinates of triangle vertices
            int[] yPoints = { 0, getHeight(), 0 }; // y-coordinates of triangle vertices
            setBackground(new Color(149, 240, 252));
            g.setColor(Color.WHITE); // set color to white
            g.fillPolygon(xPoints, yPoints, 3); // fill triangle
        } else {
            super.paintComponent(g);
            this.setBorder(BorderFactory.createLineBorder(Color.white));
        }
    }

    public int getColumn(){
        return y;
    }
    public int getRow(){
        return x;
    }
    public void setText(String text){
        JLabel label= new JLabel(text);
        this.add(label);
    }
}
