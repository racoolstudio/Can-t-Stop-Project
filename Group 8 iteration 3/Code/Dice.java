import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Dice {
    private Random rand;

    public Dice(){
        rand = new Random();
    }

    //todo Dice Swing interfacing so that we see dice images on the board

    
    public ArrayList<Integer> makeTurnRoll(){
        ArrayList<Integer> turnRolls = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            turnRolls.add(roll());
        }
        return turnRolls;
    }

    public ArrayList<Integer> getTurnOrderRolls(int numOfPlayers){
        ArrayList<Integer> turnOrderRolls = new ArrayList<Integer>();
        for (int i=0; i<numOfPlayers; i++) {
            turnOrderRolls.add(roll() + roll());
        }

        //Logic for duplicate rolls being rerolled still needs to be implemented
        return turnOrderRolls;
    }

    private int roll(){
        return (rand.nextInt(6) + 1);
    }
}
