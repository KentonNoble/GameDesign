/*
 *constructor for Characters
 *called only by Character Repo
 *Alter With Caution: will break game
 */

function CharacterMaker(I, N, H, CA) {
    if (!(this instanceof CharacterMaker)) {
        return new CharacterMaker(I, N, H, CA);
    }

    var ID = I;
    var name = N;
    var health = H;
    var creatureArray = CA;
    var creaturePositionGrid = new CreaturePositionGrid;

    //getters
    this.getID = function () {
        return ID;
    };
    this.getName = function () {
        return name;
    };
    this.getHealth = function () {
        return health;
    };
    this.getCreatureArray = function () {
        return creatureArray;
    };
    this.getCreaturePositionGrid= function () {
        return creaturePositionGrid;
    };

    //setters
    this.setHealth = function (x) {
        health = x;
    };

}

