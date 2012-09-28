/*
 *Character list contains pre-defined values to be passed into Character Maker
 *Alter With Caution: Changing structure will break game
 *                    Additions are welcome
 *                    Format: (ID,name, health, creatureList)
 */
function CharacterRepo() {
    if (!(this instanceof CharacterRepo)) {
        return new CharacterRepo();
    }

    this.TONY_JONES = function () {
        return CharacterMaker(1, "Tony Jones", 150, [
            CreatureRepo().TIMBER_HYREN(),
            CreatureRepo().LEAF_HYREN(),
            CreatureRepo().THUNDER_HYREN()])
    };

    this.MIA = function () {
        return CharacterMaker(2, "Mia", 200, [
            CreatureRepo().FUROCK(),
            CreatureRepo().TUSK_BWISP(),
            CreatureRepo().BISIWOG()])
    };
}
