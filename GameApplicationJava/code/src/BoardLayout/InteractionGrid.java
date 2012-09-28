/*pivotal class, ALTER WITH EXTREME CAUTION, will break game*/
package BoardLayout;

public class InteractionGrid {
    private final CreaturePositionGrid creaturePositions;
    private final int[][] creatureInteractionGrid = new int[5][5];
    private final int[][] creatureInteractionGridInvolved = new int[5][5];

    /*accepts a teams position grid which contains creatures*/
    public InteractionGrid(CreaturePositionGrid creaturePositions) {
        this.creaturePositions = creaturePositions;
        createInteractionGrid();
        createInteractionGridInvolved();
    }

    /*scans all creature position slots and when a creature is found it pulls
    the creatures involvement pattern based on its current position
    (Used mostly for user information purposes)*/
    private void createInteractionGrid() {
        for (int x = 0; x < 5; x++) {
            if (creaturePositions.getPositionGrid()[x] != null)
                creatureInteractionGrid[x] = getInteractionFromSlotX(x);
        }
    }

    /*scans all creature position slots and when a creature is found And that
    creature is Set to INVOLVED status it pulls the creatures involvement pattern
    based on its current position.
    (Used for advanced damage calculations)*/
    private void createInteractionGridInvolved() {
        for (int x = 0; x < 5; x++) {
            if (creaturePositions.getPositionGrid()[x] != null &&
                    creaturePositions.getPositionGrid()[x].getInvolved())
                creatureInteractionGridInvolved[x] = getInteractionFromSlotX(x);
        }
    }

    /*Used by both CreatureInteractionGrid and CreatureInteractionGridInvolved,
    Pulls the involvement pattern for a creature based on its location*/
    public int[] getInteractionFromSlotX(int x) {
        return creaturePositions.getPositionGrid()[x].getAttackType()[x];
    }

    /*Used for AITurn, allows for the AI to evaluate whether a creature is interacting with
    all possible positions*/
    public int getSumOfInteractionsFromSlotX(int x) {
        int sum = 0;
        for (int y : getInteractionFromSlotX(x)) {
            sum += y;
        }
        return sum;
    }

    /*return The Base Interaction Grid */
    public int[][] getCreatureInteractionGrid() {
        return creatureInteractionGrid.clone();
    }

    /*return the Interaction Grid Using only Involved Creatures*/
    public int[][] getCreatureInteractionGridInvolved() {
        return creatureInteractionGridInvolved.clone();
    }

    /*Not currently used, left intact for clarity and potential future usefullness*/
    public int[] getInteractionPerColumn() {
        int[] InteractionPerColumn = new int[5];
        int counter = 0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                counter += creatureInteractionGrid[x][y];
            }
            InteractionPerColumn[y] = counter;
            counter = 0;
        }
        return InteractionPerColumn.clone();
    }

    /*Used to determine if a team has active blockers by DealDamageToMagi() in DamageCalculation*/
    public int[] getInteractionPerColumnInvolved() {
        int[] InteractionPerColumn = new int[5];
        int counter = 0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                counter += creatureInteractionGrid[x][y];
            }
            InteractionPerColumn[y] = counter;
            counter = 0;
        }
        return InteractionPerColumn.clone();
    }

    public void printInteractionGrid() {
        String positionGridPrint = "";
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                positionGridPrint += creatureInteractionGrid[x][y];
            }
            positionGridPrint += "\n";
        }
        System.out.println(positionGridPrint);
    }
}
