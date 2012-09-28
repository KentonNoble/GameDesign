package TeamBuilder;

import BoardLayout.*;
import Characters.*;

public class TeamBuilder {
    private final Characters MagiCharacter;
    private final CreaturePositionGrid teamPositions;
    private InteractionGrid TeamInteractionGrid;

    public TeamBuilder(Characters MagiCharacter) {
        this.MagiCharacter = MagiCharacter;
        this.teamPositions = new CreaturePositionGrid();
        this.TeamInteractionGrid = new InteractionGrid(teamPositions);
    }

    public CreaturePositionGrid getTeamPositions() {
        return teamPositions;
    }

    public InteractionGrid getTeamInteractionGrid() {
        return TeamInteractionGrid;
    }

    public Characters getMagiCharacter() {
        return MagiCharacter;
    }

    public void updateInteractionGrid() {
        this.TeamInteractionGrid = new InteractionGrid(teamPositions);
    }
}
