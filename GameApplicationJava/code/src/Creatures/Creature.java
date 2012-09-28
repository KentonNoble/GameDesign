package Creatures;

//creature creator
public class Creature {
    //declare creature variables
    private String Name;
    private int ATK;
    private int DEF;
    private int[][] AttackType = new int[5][5];
    private int Cost;
    private int CoolDown = 0;
    private int InstanceCount = 0;
    private boolean Involved;

    //creature constructor
    public Creature(String Name, int ATK, int DEF, int[][] AttackType) {
        this.Name = Name;
        this.ATK = ATK;
        this.DEF = DEF;
        this.AttackType = AttackType;
        this.Cost = costGenerator();
        this.Involved=true;
    }

    //creature Cloner constructor
    public Creature(Creature x) {
        this.Name = x.Name;
        this.ATK = x.ATK;
        this.DEF = x.DEF;
        this.AttackType = x.AttackType;
        this.Cost = costGenerator();
        this.Involved=true;
    }

    //determine creature cost
    public int costGenerator() {
        int ATKCost = (this.ATK * 2);
        int DEFCost = (this.DEF);
        int counter = 0;
        for (int x = 0; x < 5; x++) {
            if (this.AttackType[2][x] == 1)
                counter++;
        }
        int RANGECost = (counter * 2);
        return (ATKCost + DEFCost + RANGECost)/2;
    }

    //return defensive copy of attack type
    public int[][] getAttackType() {
        return AttackType.clone();
    }

    //return defensive copy of attack type
    public int[] getAttackLineX(int x) {
        return AttackType[x].clone();
    }

    //return creature name
    public String getName() {
        return Name;
    }

    //return creature ATK
    public int getATK() {
        return ATK;
    }

    //return creature DEF
    public int getDEF() {
        return DEF;
    }

    //return creature InvolvementStatus ()
    public boolean getInvolved(){
        return Involved;
    }

    //return creature coolDown Status
    public int getCoolDown() {
        return CoolDown;
    }

    public int getInstanceCount() {
        return InstanceCount;
    }

    public void setInvolved(boolean x){
        Involved = x;
    }

    public void setCoolDown(int x) {
        CoolDown = x;
    }
    public void setInstanceCount(int x) {
        InstanceCount = x;
    }

    //to string method
    public String toString() {
        String positionGridPrint = "";
        for (int[] aAttackType : AttackType) {
            for (int anAAttackType : aAttackType) {
                positionGridPrint += anAAttackType;
            }
            positionGridPrint += "\n";
        }
        return Name + "\n" + Cost + "\n" + ATK + "\n" + DEF + "\n" + positionGridPrint;
    }
}
