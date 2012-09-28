import Characters.*;
import GamePlay.*;
import TeamBuilder.*;

public class Game {
    public static void main(String Args[]) {
        TeamBuilder team1 = new TeamBuilder(CharacterList._01());
        TeamBuilder team2 = new TeamBuilder(CharacterList._02());

        //run until a player has been defeated
        do {
            System.out.println("team1 magi health:" + team1.getMagiCharacter().getHealth());
            new PlayerTurn(team1, team2);

            System.out.println("team2 magi health:" + team2.getMagiCharacter().getHealth());
            new PlayerTurn(team2, team1);

        }
        while (team1.getMagiCharacter().getHealth() > 0 && team2.getMagiCharacter().getHealth() > 0);

    }


}
