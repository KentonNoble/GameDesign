package CombatTools;

import BoardLayout.InteractionGrid;
import TeamBuilder.TeamBuilder;

public class DamageCalculation {
    private final TeamBuilder AttackingTeam;
    private final InteractionGrid AttackingTeamInteractions;

    private final TeamBuilder DefendingTeam;

    public DamageCalculation(TeamBuilder AttackingTeam, TeamBuilder DefendingTeam) {
        this.AttackingTeam = AttackingTeam;
        AttackingTeam.updateInteractionGrid();
        this.AttackingTeamInteractions = AttackingTeam.getTeamInteractionGrid();

        this.DefendingTeam = DefendingTeam;
    }

    private int[][] DamageGrid() {
        int[][] DamageGrid = new int[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (AttackingTeam.getTeamPositions().getPositionGrid()[x] != null ){
                    DamageGrid[x][y] = (AttackingTeamInteractions.getCreatureInteractionGridInvolved()[x][y]) *
                            (AttackingTeam.getTeamPositions().getPositionGrid()[x].getATK());
                }
            }
        }
        return DamageGrid;
    }

    public int[] CalculateDamagePerCreature() {
        int creatureDamageTaken = 0;
        int[] creatureDamageTakenArray = new int[5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (DefendingTeam.getTeamPositions().getPositionGrid()[x] != null &&
                    DefendingTeam.getTeamPositions().getPositionGrid()[x].getInvolved()) {
                    creatureDamageTaken += (DefendingTeam.getTeamInteractionGrid().getInteractionFromSlotX(x))[y] *
                            (DamagePerColumm()[y]);
                }
            }
            creatureDamageTakenArray[x] = creatureDamageTaken;
            creatureDamageTaken = 0;
        }
        return creatureDamageTakenArray;
    }
   /*
    public int[] CalculateDamagePerCreature() {
        int[] creatureDamageTakenArray = new int[5];
        for (int x = 0; x < 5; x++) {
            if (DefendingTeam.getTeamPositions().getPositionGrid()[x] != null)
                creatureDamageTakenArray[x] = DamagePerColumm()[x];
        }
        return creatureDamageTakenArray;
    }
    */
    public int[] DamagePerColumm() {
        int[][] DamageGridUsed = DamageGrid().clone();
        int[] DamagePerColumn = new int[5];
        int counter = 0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                counter += DamageGridUsed[x][y];
            }
            DamagePerColumn[y] = counter;
            counter = 0;
        }
        return DamagePerColumn;
    }

    public void dealDamageToMagi() {
        for (int x = 0; x < 5; x++) {
            if (DefendingTeam.getTeamInteractionGrid().getInteractionPerColumnInvolved()[x] == 0) {
                DefendingTeam.getMagiCharacter().subtractHealth(DamagePerColumm()[x]);
            }
        }
    }

    public void printDamagePerColumn() {
        for (int x : DamagePerColumm())
            System.out.print(x + " ");
        System.out.println("\n");
    }

    public void printDamagePerCreature() {
        for (int x : CalculateDamagePerCreature()) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    public void printDamageGrid() {
        String printable = "";
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                printable += DamageGrid()[x][y];
            }
            printable += "\n";
        }
        System.out.println(printable);
    }

}
