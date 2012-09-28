/*
 *2d Arrays which contain the attack structure of each position
 *called only by Creature Maker
 *Alter With Caution: will break game
 */
function AttackType() {
    //if AttackType called without 'new', treat as though it was


    if (!(this instanceof AttackType)) {
        return new AttackType();
    }

    this.BLANK = function () {

        return [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0]
        ];
    };


    this.BULL_DOZER = function () {
        return [
            [1, 1, 0, 0, 0],
            [1, 1, 1, 0, 0],
            [0, 1, 1, 1, 0],
            [0, 0, 1, 1, 1],
            [0, 0, 0, 1, 1]
        ];
    };

    this.DIRECT = function () {
        return [
            [1, 0, 0, 0, 0],
            [0, 1, 0, 0, 0],
            [0, 0, 1, 0, 0],
            [0, 0, 0, 1, 0],
            [0, 0, 0, 0, 1]
        ];
    };

    this.FIRE_BREATHING = function () {
        return [
            [1, 0, 1, 0, 0],
            [0, 1, 0, 1, 0],
            [1, 0, 1, 0, 1],
            [0, 1, 0, 1, 0],
            [0, 0, 1, 0, 1]
        ];
    };

    this.FLANKER = function () {
        return [
            [0, 0, 1, 0, 0],
            [0, 0, 0, 1, 0],
            [1, 0, 0, 0, 1],
            [0, 1, 0, 0, 0],
            [0, 0, 1, 0, 0]
        ];
    };

    this.FOG = function () {

        return [
            [1, 1, 1, 0, 0],
            [1, 1, 1, 1, 0],
            [1, 1, 1, 1, 1],
            [0, 1, 1, 1, 1],
            [0, 0, 1, 1, 1]
        ];
    };
}