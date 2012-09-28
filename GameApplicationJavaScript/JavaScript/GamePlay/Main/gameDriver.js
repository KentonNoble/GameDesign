function gameDriver(){
    var player1 = CharacterRepo().TONY_JONES();
    var player2 = CharacterRepo().MIA();


    while (player1.getHealth()>0 && player2.getHealth()>0){
        if(player1.getHealth()>0){
            playerTurnInterface();
        }

        if(player2.getHealth()>0){
            aiTurnInterface;
        }
    }
}
gameDriver();