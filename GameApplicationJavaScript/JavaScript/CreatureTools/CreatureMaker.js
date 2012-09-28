/*
 *constructor for Creatures
 *called only by Creature Repo
 *Alter With Caution: will break game
 */

function Creature(I, N, A, D, AT) {

    if (!(this instanceof Creature)) {
        return new Creature(I, N, A, D, AT);
    }

    var ID = I, name = N, atk = A, def = D, attack_type = AT, involved = true, cool_down = 1, instance_count = 0, creatureSprite;


    //Used to determine casting cost of creature
    //formula:(atk*2 + this.def + num attacks) *coolDown
    var cost = function () {
        var counter = 0, x;
        for (x = 0; x < 5; x += 1) {
            if (attack_type[2][x] === 1) {
                counter += 1;
            }
        }
        return (((atk * 2) + def + (counter * 2)) * cool_down);
    };

    //Damage grid is used in damage calculations
    var creature_damage_grid = function () {
        var damageGrid = [], z, x, y;

        for (z = 0; z < 5; z += 1) {
            damageGrid[z] = [];
        }

        for (x = 0; x < 5; x += 1) {
            for (y = 0; y < 5; y += 1) {
                damageGrid[x][y] = attack_type[x][y] * atk;
            }
        }
        return damageGrid;
    };

    //getters
    this.getID = function() {
        return ID;
    };
    this.getName = function() {
        return name;
    };
    this.getAtk = function() {
        return atk;
    };
    this.getDef = function() {
        return def;
    };
    this.getAttack_Type = function() {
        return attack_type;
    };
    this.getInvolved = function() {
        return involved;
    };
    this.getCreatureSprite = function() {
        return creatureSprite;
    };
    this.getCool_Down = function() {
        return cool_down;
    };
    this.getInstance_Count = function() {
        return instance_count;
    };
    this.getCost = function() {
        return cost;
    };
    this.getCreature_Damage_Grid = function() {
        return creature_damage_grid;
    };


    //setters(only those needed)
    this.setInvolved = function(x) {
        involved = x;
    };

    this.setCool_Down = function(x) {
        cool_down = x;
    };

    this.setInstanceCount = function(x) {
        instance_count = x;
    }

}