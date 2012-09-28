/*
 *Creature list contains pre-defined values to be passed into Creature Maker
 *Alter With Caution: Changing structure will break game
 *                    Additions are welcome
 *                    AttackType is treated as 'new AttackType().PATTERN()'
 *                    format (ID,name,atk,def,AttackType)
 */

function CreatureRepo() {

    if (!(this instanceof CreatureRepo)) {
        return new CreatureRepo();
    }

    this.TIMBER_HYREN = function () {
        return new Creature(1, "Timber Hyren", 3, 12,new AttackType().FIRE_BREATHING());
    };

    this.LEAF_HYREN = function () {
        return new Creature(2, "Leaf Hyren", 4, 10, new AttackType().FLANKER());
    };

    this.THUNDER_HYREN = function () {
        return new Creature(3, "Thunder Hyren", 1, 17,new AttackType().FOG());
    };

    this.FUROCK = function () {
        return new Creature(4, "Furok", 3, 9, new AttackType().BULL_DOZER());
    };


    this.TUSK_BWISP = function () {
        return new Creature(5, "Tusk Bwisp", 7, 1, new AttackType().DIRECT());
    };

    this.BISIWOG = function () {
        return new Creature(6, "Bisiwog", 2, 10, new AttackType().BULL_DOZER());
    };
}