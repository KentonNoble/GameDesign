package Characters;

import Creatures.Creature;

public class Characters {
    private final String Name;
    private int Health;
    private final Creature[] CreatureCollection;

    /*Characters, format :name,health,creatures*/
    public Characters(String Name, int Health, Creature... CreatureCollection) {
        this.Name = Name;
        this.Health = Health;
        this.CreatureCollection = CreatureCollection.clone();
    }

    public Characters(Characters x) {
        this.Name = x.Name;
        this.Health = x.Health;
        this.CreatureCollection = x.CreatureCollection.clone();
    }

    /*return characters current health*/
    public int getHealth() {
        return Health;
    }

    /*set characters health
    (used when character summons, or takes damage)*/
    public void subtractHealth(int x) {
        Health = Health - x;
    }


    /*return characters creatures in an array in default order
    used when ATK, DEF are negligible*/
    public Creature[] getCreatureCollection() {
        return CreatureCollection.clone();
    }

    /*return characters creatures in an array sorted in descending DEFENSE
    used by the AI to respond to situations*/
    public Creature[] getCreatureCollectionDefensivelySorted(){
        Creature temp;
        Creature[] CreatureCollectionDefensivelySorted = CreatureCollection;

        for (int y=0;y<CreatureCollectionDefensivelySorted.length;y++){
            for (int x=0; x<CreatureCollectionDefensivelySorted.length-1;x++){
                if (CreatureCollectionDefensivelySorted[x].getDEF()<CreatureCollectionDefensivelySorted[x+1].getDEF()){
                    temp=CreatureCollectionDefensivelySorted[x];
                    CreatureCollectionDefensivelySorted[x]=CreatureCollectionDefensivelySorted[x+1];
                    CreatureCollectionDefensivelySorted[x+1]=temp;
                }
            }
         }
    return CreatureCollectionDefensivelySorted;
    }

    /*return characters creatures in an array sorted in descending ATTACK
    used by the AI to respond to situations*/
    public Creature[] getCreatureCollectionOffensivelySorted(){
        Creature temp;
        Creature[] CreatureCollectionOffensivelySorted = CreatureCollection;
        for (int y=0;y<CreatureCollectionOffensivelySorted.length;y++){
            for (int x=0; x<CreatureCollectionOffensivelySorted.length-1;x++){
                if (CreatureCollectionOffensivelySorted[x].getATK()<CreatureCollectionOffensivelySorted[x+1].getATK()){
                    temp=CreatureCollectionOffensivelySorted[x];
                    CreatureCollectionOffensivelySorted[x]=CreatureCollectionOffensivelySorted[x+1];
                    CreatureCollectionOffensivelySorted[x+1]=temp;
                }
            }
        }
        return CreatureCollectionOffensivelySorted;
    }


    public void printCreatureCollection(Creature[] x){
        for(Creature y : x)
            System.out.println(y);
    }

}