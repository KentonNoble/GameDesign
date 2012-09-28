function playerTurnInterface() {
    var arrayOfUnsummonedCreatureSlots = document.getElementsByClassName("unsummonedCreatureSlot");
    var arrayOfHomeCreatureSlot = document.getElementsByClassName("homeCreatureSlot");

    var counter1 = arrayOfUnsummonedCreatureSlots.length,
        counter2 = arrayOfHomeCreatureSlot.length,
        i,
        patient,
        firstAction;


    for(i=0; i< counter1+counter2;i++){
        patient = (i<counter1)? arrayOfUnsummonedCreatureSlots[i]:arrayOfHomeCreatureSlot[i-counter1];
        patient.onclick = function(){
            if (!firstAction){
                firstAction = this;
            }
            else{
                inputHandler(firstAction,this);
                firstAction = null;
            }
        };
    }
}
