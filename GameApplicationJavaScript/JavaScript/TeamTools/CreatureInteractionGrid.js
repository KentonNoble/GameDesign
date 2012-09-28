function CreatureInteractionGrid(positionGrid) {

    if (!(this instanceof CreatureInteractionGrid)) {
        return new CreatureInteractionGrid(positionGrid);
    }

    var creatureInteractionGrid = new Array(5);
    var creatureInteractionGridInvolved = new Array(5);
    var creaturePositionGrid = positionGrid;


    this.getInteractionFromSlotX = function(x) {
        return creaturePositionGrid[x].getAttack_Type()[x];
    };


    this.createInteractionGrid = function() {
        var x;
        for (x = 0; x < 5; x++) {
            if (creaturePositionGrid[x] != null)
                creatureInteractionGrid[x] = creaturePositionGrid[x].getInteractionFromSlotX(x);
        }
    };


    this.createInteractionGridInvolved = function()  {
        var x;
        for (x = 0; x < 5; x++) {
            if (creaturePositionGrid[x] != null && creaturePositionGrid[x].getInvolved())
                creatureInteractionGridInvolved[x] = creaturePositionGrid[x].getInteractionFromSlotX(x);
        }
    };

    this.getInteractionPerColumn = function() {
        var InteractionPerColumn = new Array(5);
        var counter = 0;
        var y, x;

        for (y = 0; y < 5; y++) {
            for (x = 0; x < 5; x++) {
                counter += creatureInteractionGrid[x][y];
            }
            InteractionPerColumn[y] = counter;
            counter = 0;
        }
        return InteractionPerColumn;
    };
}
