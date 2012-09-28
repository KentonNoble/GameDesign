/*pivotal class, ALTER WITH EXTREME CAUTION, will break game*/
package BoardLayout;
import Creatures.Creature;

//create a grid to hold creature positions
public class CreaturePositionGrid {
    private final Creature[] position = new Creature[5];

    //put a creature in specified slot, used for summon() in PlayerTurn
    public void putCreatureInSlotX(Creature x, int y) {
        if (position[y] == null)
            position[y] = x;
    }

    //remove creature (used in EntTurn() in PlayerTurn, triggered when creature dies)
    public void RemoveCreatureInSlotX(int x) {
        if (position[x] != null)
            position[x] = null;
    }

    //swap creatures in x, used by move() in PlayerTurn
    public void SwapCreatureXtoY(int x, int y) {
        Creature temp = position[y];
        position[y] = position[x];
        position[x] = temp;
    }



    //return a defensive copy of position grid, used by InteractionGrid.
    public Creature[] getPositionGrid() {
        return position.clone();
    }

    public void printPositionGrid() {
        for (int x = 0; x < 5; x++) {
            if (position[x] != null)
                System.out.println(x + ": " + position[x].getName());
        }
    }
}
