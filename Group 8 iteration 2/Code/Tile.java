import javax.swing.*;
import java.awt.*;
import java.awt.GradientPaint;



public class Tile extends JButton {
    private int x, y;
    private boolean endTile;

    public Tile(int x, int y, boolean gameTile){
        this.x = x;
        this.y = y;
        endTile = false;

        setOpaque(true);
        if (gameTile){
            this.setBackground(Color.red);
            this.setBorder(BorderFactory.createLineBorder(Color.white));
        }
        else {
            this.setVisible(false);
            }
    }

    public boolean checkEndTile(){
        return endTile;
    }

    public void setEndTile(){
        endTile = true;
    }
}
