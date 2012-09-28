
package GamePlay;

import CombatTools.DamageCalculation;
import TeamBuilder.TeamBuilder;
import java.util.Random;
public class AITurn {

    private final TeamBuilder CurrentTeam;
    private final TeamBuilder OtherTeam;
    private Random numberGenerator= new Random();
    private DamageCalculation AIDamageStatsAgainst;
    private DamageCalculation AIDamageStatsFor;

    private int OppMostVulnerableCreature;
    private int OppMostThreateningCreature;
    private int OppMostDefensiveCreature;
    private int SelfWeakSpot=0;
    private int OpponentWeakSpot=0;
    private int SelfDirectDamage=-1;
    private int OppDirectDamage=-1;
    private int Priority;

    public AITurn(TeamBuilder CurrentTeam, TeamBuilder OtherTeam) {
        this.CurrentTeam = CurrentTeam;
        this.OtherTeam= OtherTeam;

        AIDamageStatsAgainst = new DamageCalculation(OtherTeam, CurrentTeam);
        AIDamageStatsFor = new DamageCalculation(CurrentTeam, OtherTeam);

            EvaluateBoard();
            SummonCreature();
            MoveCreatures();
            EndTurn();
    }
    public int StarterPlace(){
        int StarterPlace=-1;

        for(int x=0;x<5;x++){

            StarterPlace = OtherTeam.getTeamPositions().getPositionGrid()[x]!= null?x:StarterPlace;
        }
        return StarterPlace;
    }
    private void EvaluateBoard(){

        if(StarterPlace()!=-1){
            OppMostVulnerableCreature=StarterPlace();
            OppMostThreateningCreature=StarterPlace();
            OppMostDefensiveCreature=StarterPlace();
            for(int x=0; x<5;x++){

                if (OtherTeam.getTeamPositions().getPositionGrid()[x]==null)
                        OppDirectDamage=x;

                if (AIDamageStatsFor.DamagePerColumm()[x]> AIDamageStatsFor.DamagePerColumm()[OpponentWeakSpot])
                        OpponentWeakSpot=x;

                if (OtherTeam.getTeamPositions().getPositionGrid()[x]!= null)
                        OppMostVulnerableCreature=OtherTeam.getTeamPositions().getPositionGrid()[x].getDEF() < OtherTeam.getTeamPositions().getPositionGrid()[OppMostVulnerableCreature].getDEF()?x:OppMostVulnerableCreature;

                if (OtherTeam.getTeamPositions().getPositionGrid()[x]!= null)
                        OppMostThreateningCreature= OtherTeam.getTeamPositions().getPositionGrid()[x].getATK()> OtherTeam.getTeamPositions().getPositionGrid()[OppMostThreateningCreature].getATK()?x:OppMostThreateningCreature;

                if (OtherTeam.getTeamPositions().getPositionGrid()[x]!= null)
                        OppMostDefensiveCreature= OtherTeam.getTeamPositions().getPositionGrid()[x].getDEF()> OtherTeam.getTeamPositions().getPositionGrid()[OppMostDefensiveCreature].getDEF()?x:OppMostDefensiveCreature;

                if (CurrentTeam.getTeamPositions().getPositionGrid()[x]==null)
                        SelfDirectDamage=x;

                if (AIDamageStatsAgainst.DamagePerColumm()[x]> AIDamageStatsAgainst.DamagePerColumm()[SelfWeakSpot])
                        SelfWeakSpot=x;
            }

            if (OtherTeam.getTeamPositions().getPositionGrid()[OppMostThreateningCreature].getATK()>=5)
                    Priority=1;

            else if(OtherTeam.getTeamPositions().getPositionGrid()[OppMostDefensiveCreature].getDEF()>= 15)
                    Priority=2;

            else if( OtherTeam.getTeamPositions().getPositionGrid()[OppMostVulnerableCreature].getDEF()<=2)
                    Priority=3;

            else if (AIDamageStatsAgainst.DamagePerColumm()[SelfWeakSpot]>=5)
                    Priority=4;

            else if (AIDamageStatsFor.DamagePerColumm()[OpponentWeakSpot]<=5)
                    Priority=5;

            else if (OppDirectDamage >= 0)
                    Priority=6;

            else if (SelfDirectDamage>= 0)
                    Priority=7;
        }
        else
            Priority=10;
    }
    
    private int SummonSpot(){
        int SummonSpot;
        int counter=0;
        int[] SummonSpotGrid=new int[5];
        for(int x=0;x<5;x++){
            if(CurrentTeam.getTeamPositions().getPositionGrid()[x]==null) {
                SummonSpotGrid[counter]=x;
                counter++;
            }
        }
        SummonSpot=SummonSpotGrid[numberGenerator.nextInt(counter)];
        System.out.println(SummonSpot);
        return SummonSpot;
    }


    private void SummonCreature() {
        int SummonSpot=SummonSpot();
        EvaluateBoard();

        Random numberGenerator = new Random();
        int RandomCreatureNumber=numberGenerator.nextInt(CurrentTeam.getMagiCharacter().getCreatureCollection().length/2);
        System.out.println(Priority);

        if (Priority==10){

            if(SummonSpot()!=-1){
                CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[0],SummonSpot);
                CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[0].costGenerator());
            }
        }

        else if(Priority==1 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[0],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[0].costGenerator());
                    }

        else if(Priority==2 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[0],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[0].costGenerator());
                    }


        else if (Priority==3 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollection()[RandomCreatureNumber],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollection()[RandomCreatureNumber].costGenerator());
        }

        else if (Priority==4 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[RandomCreatureNumber],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[RandomCreatureNumber].costGenerator());
        }

        else if (Priority==5 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[RandomCreatureNumber],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[RandomCreatureNumber].costGenerator());
        }

        else if (Priority==6 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[RandomCreatureNumber],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionOffensivelySorted()[RandomCreatureNumber].costGenerator());
        }

        else if(Priority==7 &&SummonSpot!=-1){
                    CurrentTeam.getTeamPositions().putCreatureInSlotX(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[RandomCreatureNumber],SummonSpot);
                    CurrentTeam.getMagiCharacter().subtractHealth(CurrentTeam.getMagiCharacter().getCreatureCollectionDefensivelySorted()[RandomCreatureNumber].costGenerator());
        }
    }


    private void MoveCreatures() {

        for(int x=0;x<5;x++){
            int counter=0;
            int most=-1;

            if(CurrentTeam.getTeamPositions().getPositionGrid()[x]!=null){
                for (int y=0;y<5;y++){
                    for (int z : CurrentTeam.getTeamPositions().getPositionGrid()[x].getAttackLineX(y)){
                        counter+=z;
                    }

                    if (counter > CurrentTeam.getTeamInteractionGrid().getSumOfInteractionsFromSlotX(x)){
                    most=y;
                    }
                    counter=0;
                }
                if(most!=-1 &&
                    CurrentTeam.getTeamPositions().getPositionGrid()[most]==null)
                        CurrentTeam.getTeamPositions().SwapCreatureXtoY(x,most);

                else if(most!=-1 &&
                        CurrentTeam.getTeamPositions().getPositionGrid()[most].getATK()<
                        CurrentTeam.getTeamPositions().getPositionGrid()[x].getATK())
                            CurrentTeam.getTeamPositions().SwapCreatureXtoY(x,most);
            }
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
                    System.out.println("AITurnOPPKilled " +x);
                }
            }

            if (CurrentTeam.getTeamPositions().getPositionGrid()[x] != null) {
                if (TurnEndDamage2[x] >= CurrentTeam.getTeamPositions().getPositionGrid()[x].getDEF()) {
                    CurrentTeam.getTeamPositions().getPositionGrid()[x].setCoolDown(3);
                    CurrentTeam.getTeamPositions().getPositionGrid()[x].setInstanceCount(0);
                    CurrentTeam.getTeamPositions().RemoveCreatureInSlotX(x);
                    System.out.println("AITurnOwnKilled " +x);
                }
            }
        }
    }

}

