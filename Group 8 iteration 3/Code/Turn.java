import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.lang.model.element.Element;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;
    private Board board;
    //Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g 1,2,3,4) indicating the order they move in. Values are the Player objects. 
    private HashMap<Integer, Player> turnOrder;
    private ArrayList<pieces> runners = new ArrayList<pieces>();

    private ArrayList<pieces> movementPieces = new ArrayList<pieces>();

    public Turn(ArrayList<Player> players, Dice dice,Board board){
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.setTurnOrder(new HashMap<Integer, Player>());
        currentTurn = 1;
        //players.forEach(e -> InvalidColumns.addAll(e.getColumns()));
        while (movementPieces.size() < 3){
            movementPieces.add(new pieces("Arrow", "White"));
        }
    }

    public void setTurnOrder(HashMap<Integer,Player> order){
        HashMap<Integer, Player> turnOrderSetter = new HashMap<Integer, Player>();
        if (order.size() != 0){
            turnOrder = order;
        } else {
            ArrayList<Integer> orderRolls = dice.getTurnOrderRolls(players.size());
            for (int i =0; i < orderRolls.size(); i++){
                int highest_remaining = Collections.max(orderRolls);
                int index = orderRolls.indexOf(highest_remaining);
                turnOrderSetter.put(i + 1,players.get(index));
                orderRolls.set(index, 0);
            }
            turnOrder = turnOrderSetter;
        }
    }

    public int getCurrentPlayerKey(){
        return currentTurn;
    }

    public void setCurrentPlayerkey(int key){
        currentTurn = key;
    }
    public HashMap<Integer, Player> getTurnOrder(){
        return turnOrder;
    }

    //Called when player rolls dice on their turn
    public ArrayList<ArrayList<Integer>> getDiceCombinations() {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> values = dice.makeTurnRoll();
        //Stores the dice pairs into combinations as ordered ArrayList. So [1,2,3,4] means the pairs 1,2 + 3,4
        
        if (runners == null || runners.size() < 3){
            combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
            combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
            combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));
            //return combinations;
        }
        else if(runners.size() == 3){
            ArrayList<Integer> valids = new ArrayList<>();
            runners.forEach(e -> valids.add(e.getColumn()));
            if(valids.contains(values.get(0)+values.get(1))||valids.contains(values.get(2)+values.get(3))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
                
            }
            if(valids.contains(values.get(0)+values.get(2))||valids.contains(values.get(1)+values.get(3))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(2), values.get(3), values.get(1))));
                
            }
            if(valids.contains(values.get(0)+values.get(3))||valids.contains(values.get(1)+values.get(2))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(3), values.get(1), values.get(2))));    
            }


        }
        return combinations;
    }

    //Called when a player selects their dice combination and moves their pieces appropriately
    public void movePiece(ArrayList<Integer> selected_combination){
        int col1 = selected_combination.get(0) + selected_combination.get(1); 
        int col2 = selected_combination.get(2) + selected_combination.get(3); 
        System.out.println("Combinations selected: "+col1+" "+col2);
        runners.forEach(e -> System.out.println("Runner location before change"+" "+e.getColumn()+" " +e.getRow()));
        ArrayList<Integer> cols= new ArrayList<Integer>(){{add(col1); add(col2);}};
        if (runners.size() == 0){
            if(col1 == col2){
                System.out.println("Case with no runners and same combination.");
                pieces runner = new pieces("Arrow", "White");
                runner.setLocation(board.getTile(col1-1,12-Math.abs(7-col1)-1));
                runners.add(runner);
            }else {
                System.out.println("Case with no runners and different combination.");
                pieces runner1 = new pieces("Arrow", "White");
                runner1.setLocation(board.getTile(col1-1,12-Math.abs(7-col1)));
                runners.add(runner1);
                pieces runner2 = new pieces("Arrow", "White");
                runner2.setLocation(board.getTile(col2-1,12-Math.abs(7-col2)));
                runners.add(runner2);
            }
    
        }
        else if(runners.size() == 3){
            System.out.println("Case with 3 runners");
            runners.forEach((e) -> {if(cols.contains(e.getRow()+1)){e.setLocation(board.getTile(e.getRow(),e.getColumn()-1));
                System.out.println("Column Match");cols.removeIf(r ->  r==e.getRow()+1);}});   
            runners.forEach((e) -> {if(cols.contains(e.getRow()+1)){e.setLocation(board.getTile(e.getRow(),e.getColumn()-1));
                    System.out.println("Column Match");cols.removeIf(r ->  r==e.getRow()+1);}});

                }
        else {

            System.out.println("Case with 3 runners");
            runners.forEach((e) -> {if(cols.contains(e.getRow()+1)){e.setLocation(board.getTile(e.getRow(),e.getColumn()-1));
                System.out.println("Column Match");cols.removeIf(r ->  r==e.getRow()+1);}});   
            runners.forEach((e) -> {if(cols.contains(e.getRow()+1)){e.setLocation(board.getTile(e.getRow(),e.getColumn()-1));
                    System.out.println("Column Match");cols.removeIf(r ->  r==e.getRow()+1);}});
            if (cols.size() == 1){
                    System.out.println("Case with 1 or 2 runners where the one column is new ");
                    pieces runner2 = new pieces("Arrow", "White");
                    runner2.setLocation(board.getTile(cols.get(0)-1,12-Math.abs(7-cols.get(0))));
                    runners.add(runner2);    
            }
            if (cols.size() == 2){
                        System.out.println("Case with 1 or 2 runners where the both column are new ");
                        pieces runner2 = new pieces("Arrow", "White");
                        runner2.setLocation(board.getTile(cols.get(0)-1,12-Math.abs(7-cols.get(0))));
                        runners.add(runner2);
                    
                        if (runners.size() < 3 ){
                            pieces runner3 = new pieces("Arrow", "White");
                            runner3.setLocation(board.getTile(cols.get(1)-1,12-Math.abs(7-cols.get(1))));
                            runners.add(runner3);
                        }
            }
        
        }
        runners.forEach(e -> System.out.println("Runner location after change"+" "+e.getColumn()+" " +e.getRow()));
        board.updateGameBoard(runners);
    }

  
    public void endTurn(){ 
        int num = turnOrder.size();
        Player current_player = turnOrder.get(currentTurn);
        ArrayList<pieces> player_Pieces = turnOrder.get(currentTurn).getPieces();
    runners.forEach(e -> {e.setAttributes(current_player.getColor(), current_player.getShape()); /*player_Pieces.add(e);*/});
        runners = new ArrayList<pieces>();
        //board.updateGameBoard(player_Pieces);
        if (currentTurn == turnOrder.size()){
            currentTurn = 1;
        } else {
            currentTurn++;
        }
    }
}
