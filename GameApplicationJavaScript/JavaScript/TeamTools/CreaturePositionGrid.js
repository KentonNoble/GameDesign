function CreaturePositionGrid() {

    if (!(this instanceof CreaturePositionGrid)) {
        return new CreaturePositionGrid();
    }

    var creaturePositionGrid = new Array(5);
    var temp;

    this.putCreatureInSlotX = function (x, y) {
        if (creaturePositionGrid[x] == null){
            creaturePositionGrid[x] = y;
        }
    };

    this.removeCreatureInSlotX = function (x) {
        if (creaturePositionGrid[x] != null)
            creaturePositionGrid[x] = null;
    };

    this.swapCreatureXtoY = function (x, y) {
        temp = creaturePositionGrid[y];
        creaturePositionGrid[y] = creaturePositionGrid[x];
        creaturePositionGrid[x] = temp;
    };

    //return a copy of position grid, used by InteractionGrid.
    this.getPositionGrid = function() {
        return creaturePositionGrid;
    }
}