function inputHandler(element1, element2){
    var summonSpotDesignation = document.getElementById("homeCreatureSlotContainer");

    element1.isSummonSpotDesignation = (element1.parentNode === summonSpotDesignation);
    element2.isSummonSpotDesignation = (element2.parentNode === summonSpotDesignation);

    if(element1.isSummonSpotDesignation && (element1 === element2)){
        //creature
        alert("creatureActivationToggle");
    }

    else if (element1.isSummonSpotDesignation && element2.isSummonSpotDesignation){
        //define creature swap
        alert("creatureSwapToggle");
    }

    if (!element1.isSummonSpotDesignation && element2.isSummonSpotDesignation){
        //define creatureSummon
        alert("creatureSummonToggle");
    }
}