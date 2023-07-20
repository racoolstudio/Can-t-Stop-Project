public class AI_Player extends Player {
    AI_Player(String shape, String color, String name) {

        super(shape, color, name);
        this.AIPlayer = true;
    }

    // AI Logic
    // Most of the logic is done in turn and board class

}
