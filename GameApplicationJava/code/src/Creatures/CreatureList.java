package Creatures;

//collection of all currently created creatures
public class CreatureList {

    private static final Creature[] creatureList =
            {       _01(),
                    _02(),
                    _03(),
                    _04(),
                    _05(),
                    _06()};

    public static Creature _01() {
        return new Creature("TimberHyren", 3, 12, AttackType.FireBreathing());
    }

    public static Creature _02() {
        return new Creature("LeafHyren", 4, 10, AttackType.Flanker());
    }

    public static Creature _03() {
        return new Creature("Furok", 3, 9, AttackType.BullDozer());
    }

    public static Creature _04() {
        return new Creature("ThunderHyren", 1, 17, AttackType.Fog());
    }

    public static Creature _05() {
        return new Creature("TuskBwisp", 7, 1, AttackType.Direct());
    }

    public static Creature _06() {
        return new Creature("Bisiwog", 2, 10, AttackType.BullDozer());
    }

    public static void printCreatureList() {
        for (Creature aCreatureList : creatureList) System.out.println(aCreatureList);
    }
}
