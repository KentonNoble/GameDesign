/*
 *constructor for Creatures
 *called only by Creature Repo
 *Alter With Caution: will break game
 */

function Creature(I, N, A, D, AT) {

    if (!(this instanceof Creature)) {
        return new Creature(I, N, A, D, AT);
    }

    Object.defineProperties(this, {
        "ID"            :{ get : function(){return I } },
        "Name"          :{ get : function(){return N } },
        "Atk"           :{ get : function(){return A } },
        "Def"           :{ get : function(){return D } },
        "AttackType"    :{ get : function(){return AT } },
        "Cost"          :{ get : function(){
                                            var counter = 0, x;
                                            for (x = 0; x < 5; x += 1) {
                                                if (this.AttackType[2][x] === 1) {
                                                    counter += 1;
                                                }
                                            }
                                            return (((this.Atk * 2) + this.Def + (counter * 2)));
        }
        },
        "DamageGrid"    :{ get : function () {
                                                var damageGrid = [[],[],[],[],[]], x, y;



                                                for (x = 0; x < 5; x += 1) {
                                                    for (y = 0; y < 5; y += 1) {
                                                        damageGrid[x][y] = this.AttackType[x][y] * this.Atk;
                                                    }
                                                }
                                                return damageGrid;
                                                }
        }
    });

    var Involved,
        CoolDown,
        InstanceCount;

    Object.defineProperties(this,{
        "Involved"      :{ get : function(){return Involved} ,
                           set : function (InvolvedVariable) {
                                 Involved = InvolvedVariable
                         }
        },
        "CoolDown"      :{ get : function(){ return CoolDown},
                           set : function (CoolDownVariable) {
                           CoolDown = CoolDownVariable;
                          }
        },
        "InstanceCount":{ get : function(){ return InstanceCount},
                          set : function (IntanceVariable) {
                          InstanceCount = IntanceVariable;
                         }}

    });
}