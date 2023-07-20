import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;
    private HashMap<Integer, Player> turnOrder;

    public Turn(ArrayList<Player> players, Dice dice){
        this.players = players;
        this.dice = dice;
        turnOrder = this.setTurnOrder();
        currentTurn = 1;
    }

    private HashMap<Integer, Player> setTurnOrder(){
        HashMap<Integer, Player> turnOrder = new HashMap<Integer, Player>();
        ArrayList<Integer> orderRolls = dice.getTurnOrderRolls(players.size());
        for (int i =0; i < orderRolls.size(); i++){
            int highest_remaining = Collections.max(orderRolls);
            int index = orderRolls.indexOf(highest_remaining);
            turnOrder.put(i + 1,players.get(index));
            orderRolls.set(index, 0);
        }
        return turnOrder;
    }

    public HashMap<Integer, Player> getTurnOrder(){
        return turnOrder;
    }

    //Called when player rolls dice on their turn
    public ArrayList<ArrayList<Integer>> getDiceCombinations() {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> values = dice.makeTurnRoll();
        //Stores the dice pairs into combinations as ordered ArrayList. So [1,2,3,4] means the pairs 1,2 + 3,4
        combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));
        return combinations;
    }

    //Called when a player selects their dice combination and moves their pieces appropriately
    public void movePiece(){

    }

    //Called when a player elects to end their turn. Once called will set all moved pieces to their new location.
    public void endTurn(){ //Arg will be movementPieces: ArrayList<MovementPieces> once movementPieces are implemented
        //move pieces
        //for each piece getPosition(): Tile
        //for each postion; tile
        /*
        if(tile.checkEndTile()){
            Player currentPlayer = turnOrder.get(currentTurn);
            currentPlayer.captureColumn(); //(Arg) Tile Position
        }

        if (currentTurn < players.size()){
            currentTurn ++;
        } else{
            currentTurn = 1;
        }
        */
    }
}
