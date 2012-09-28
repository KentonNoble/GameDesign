/*pivotal class, although modification is possible in conjunction with Characters*/
package Characters;

import Creatures.CreatureList;

public class CharacterList {

    /*list containing created characters*/
    private static final Characters[] charactersList =
            {_01(),
             _02()};

    /*Characters, format :name,health,creatures*/

    public static Characters _01() {
        return new Characters("Tony Jones", 80, CreatureList._02(), CreatureList._03(), CreatureList._06(), CreatureList._05());
    }
    public static Characters _02() {
        return new Characters("Mia",100, CreatureList._01(), CreatureList._03(),CreatureList._04(),CreatureList._05());
    }


    public static void printCharacterList() {
        for (Characters aCharactersList : charactersList) System.out.println(aCharactersList);
    }

}
