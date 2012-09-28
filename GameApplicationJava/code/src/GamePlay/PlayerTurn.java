package GamePlay;

import CombatTools.DamageCalculation;
import TeamBuilder.*;
import Creatures.*;

import java.util.Scanner;

public class PlayerTurn {
    private final TeamBuilder CurrentTeam;
    private final TeamBuilder OtherTeam;
    private final Scanner kb = new Scanner(System.in);
    private int summonCounter = 0;

    public PlayerTurn(TeamBuilder CurrentTeam, TeamBuilder OtherTeam) {
        this.CurrentTeam = CurrentTeam;
        this.OtherTeam = OtherTeam;

        String again = "";
        do {
            System.out.println("1: Summon 2: Move 3: DisplayDamageDealt 4:DisplayDamageReturned 5:SwitchInvolvement");

            int input = kb.nextInt();
            kb.nextLine();
            switch (input) {
                case 1:
                    SummonCreature();
                    System.out.println("continue with turn?");
                    again = kb.nextLine();
                    break;
                case 2:
                    MoveCreatures();
                    System.out.println("continue with turn?");
                    again = kb.nextLine();
                    break;
                case 3:
                    DisplayDamageCalculationsCurrent();
                    System.out.println("continue with turn?");
                    again = kb.nextLine();
                    break;
                case 4:
                    DisplayDamageCalculationsBackLash();
                    System.out.println("continue with turn?");
                    again = kb.nextLine();
                    break;
                case 5:
                    ChangeCreatureInvolvement();
                    System.out.println("continue with turn?");
                    again = kb.nextLine();
                    break;
                default:
                    EndTurn();
                    break;
            }
        }
        while (again.equalsIgnoreCase("Y"));
        EndTurn();
    }

    private void SummonCreature() {
        //if there has only been one summon this turn
        if (summonCounter == 0) {
            for (Creature x : CurrentTeam.getMagiCharacter().getCreatureCollection())
                System.out.println(x);
            int creature;
            int slot;
            int coolDown;

            System.out.println("Which creature?");
            creature = kb.nextInt();
            kb.nextLine();

            //if the creature does not have a coolDownTime and is not already out summon it normally
            if (CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getCoolDown() == 0 &&
                    CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getInstanceCount() == 0) {
                System.out.println("Which slot?");
                slot = kb.nextInt();
                kb.nextLine();
                CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollection()[creature], slot);
                CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].costGenerator());
                //mark there has been a summon
                summonCounter++;
                //mark that the creature has been summoned
                CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].setInstanceCount(1);
            }
            //if the creature is already out
            else if (CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getInstanceCount() > 0)
                System.out.println("That creature is already Summoned");

                //if the creature does have a cool down time, offer to summon at cost time coolDownTime.
            else if (CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getCoolDown() > 0) {
                coolDown = CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getCoolDown();
                System.out.println("Your creature has a cool down time of: " + coolDown + ", do you wish to summon it at cost times " + coolDown + "?");

                if (kb.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Very well ,Which slot?");
                    slot = kb.nextInt();
                    kb.nextLine();
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollection()[creature], slot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].costGenerator() *
                    CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].getCoolDown());
                    summonCounter++;
                    CurrentTeam.getMagiCharacter().getCreatureCollection()[creature].setInstanceCount(1);
                }
                //if they choose not to summon at extra cost
                else
                    System.out.println("Very Well");
            }

        }
        //if there has already been a summon this turn
        else
            System.out.println("Sorry,only 1 summon per turn");
    }

    private void MoveCreatures() {
        CurrentTeam.getTeamPositions().printPositionGrid();
        System.out.println("Which slot to move from?");
        int moveFrom = kb.nextInt();
        kb.nextLine();
        System.out.println("Which slot to move to?");
        int moveTo = kb.nextInt();
        kb.nextLine();
        CurrentTeam.getTeamPositions().SwapCreatureXtoY(moveFrom, moveTo);
    }

    private void ChangeCreatureInvolvement(){
        CurrentTeam.getTeamPositions().printPositionGrid();
        System.out.println("Which Creature do you want to activate/inactivate?");
        int activate = kb.nextInt();
        kb.nextLine();

        CurrentTeam.getTeamPositions().getPositionGrid()[activate].setInvolved(
        !CurrentTeam.getTeamPositions().getPositionGrid()[activate].getInvolved()
        );
    }


    private void DisplayDamageCalculationsCurrent() {
        CurrentTeam.updateInteractionGrid();
        DamageCalculation DamagePreview = new DamageCalculation(CurrentTeam, OtherTeam);
        DamagePreview.printDamageGrid();
        DamagePreview.printDamagePerColumn();
        DamagePreview.printDamagePerCreature();
    }

    private void DisplayDamageCalculationsBackLash() {
        CurrentTeam.updateInteractionGrid();
        DamageCalculation DamagePreview = new DamageCalculation(OtherTeam, CurrentTeam);
        DamagePreview.printDamageGrid();
        DamagePreview.printDamagePerColumn();
        DamagePreview.printDamagePerCreature();
    }

    private void LowerCoolDownCounters() {
        for (Creature x : CurrentTeam.getMagiCharacter().getCreatureCollection()) {
            if (x.getCoolDown() > 0)
                x.setCoolDown(x.getCoolDown() - 1);
        }
    }

    private void EndTurn(){
        CurrentTeam.updateInteractionGrid();
        new DamageCalculation(CurrentTeam, OtherTeam).dealDamageToMagi();
        new DamageCalculation(OtherTeam, CurrentTeam).dealDamageToMagi();

        int[] TurnEndDamage1 = new DamageCalculation(CurrentTeam, OtherTeam).CalculateDamagePerCreature();
        int[] TurnEndDamage2 = new DamageCalculation(OtherTeam, CurrentTeam).CalculateDamagePerCreature();

        for (int x = 0; x < 5; x++) {
            if (OtherTeam.getTeamPositions().getPositionGrid()[x] != null) {
                if (TurnEndDamage1[x] >= OtherTeam.getTeamPositions().getPositionGrid()[x].getDEF()) {
                    OtherTeam.getTeamPositions().getPositionGrid()[x].setCoolDown(3);
                    OtherTeam.getTeamPositions().getPositionGrid()[x].setInstanceCount(0);
                    OtherTeam.getTeamPositions().RemoveCreatureInSlotX(x);
                    System.out.println("PlayerTurnOPPKilled " +x);
                }
            }

            if (CurrentTeam.getTeamPositions().getPositionGrid()[x] != null) {
                if (TurnEndDamage2[x] >= CurrentTeam.getTeamPositions().getPositionGrid()[x].getDEF()) {
                    CurrentTeam.getTeamPositions().getPositionGrid()[x].setCoolDown(3);
                    CurrentTeam.getTeamPositions().getPositionGrid()[x].setInstanceCount(0);
                    CurrentTeam.getTeamPositions().RemoveCreatureInSlotX(x);
                    System.out.println("PlayerTurnOwnKilled " +x);
                }
            }
        }
    }
}

