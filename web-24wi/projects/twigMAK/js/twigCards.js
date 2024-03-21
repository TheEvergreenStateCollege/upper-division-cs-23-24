//controls twigs.html

//card Maker ------------------------------------------------------------------
function makeCards(twigArraytmp){
    //get location-------
    const body = document.getElementsByTagName("body")[0];

    //make cards-----------------------------------------------
    for (let i=0; i < twigArraytmp.length; i++){

        scientificName = twigArraytmp[i].genus + " " + twigArraytmp[i].species;
 
        //make card
        const twigCard = document.createElement("div"); //make div
        twigCard.setAttribute("class", "card");          //add class
        twigCard.setAttribute("Id", "card-" + scientificName);  //label each box
        twigCard.setAttribute("onclick", "load("+i+")");    //add clickable load info
        document.getElementById("cardHolder").appendChild(twigCard); //adds it to html
      
        //add common name 
        cardTitle("h1", "CommonName", "card-" + scientificName, "LabelCommon-" + twigArraytmp[i].CommonName, twigArraytmp[i].CommonName);

        //add scientific name 
        cardTitle("h2", "ScientificName", "card-" + scientificName, "LabelScientific-" + scientificName, scientificName);

        //add image
        AddImage("twigImg", "twigImg-" + scientificName, twigArraytmp[i].image[0], "card-" + scientificName);

        /*//add key 
        if(twigArraytmp[i].key !== undefined){
            cardTitle("p", "key", "card-" + scientificName, "Labelkey-" + scientificName, twigArraytmp[i].key);
        }*/
    }

    console.log("cards made");

    //check if come from ID
    console.log(localStorage.getItem("ordered"));
    if(localStorage.getItem("ordered") == "yes"){
        //localStorage.setItem("ordered", "no");
        loadkeytwigs();
    }
}

//save twig selected and go to info page 
function load(i){
    
    localStorage.setItem("Num", i);
    //var TwigNum = i;
    loadInfoPage();
}

function cardTitle(htmltype, className, cardName, id, value){   //used in makeCards
    const tempTitle = document.createElement(htmltype); //make htmltype
    tempTitle.setAttribute("class", className);         //add class
    tempTitle.setAttribute("Id", id);                   //label
    document.getElementById(cardName).appendChild(tempTitle); //adds it to html
    document.getElementById(id).innerHTML = value;      //set to value
}

//load values for ID and split into array
function loadkeytwigs(){ 

    console.log("loading in order");
    orderString =  localStorage.getItem("key");
    const orderArray = orderString.split(",");

    console.log("loading in match amount");
    orderString =  localStorage.getItem("match");
    const matchAmountArray = orderString.split(",");

    console.log("loading in match Types");
    orderString =  localStorage.getItem("matchType");
    const matchTypeArray = orderString.split(",");

    console.log(orderArray, matchAmountArray, matchTypeArray);
    orderCardsbyKey(orderArray, matchAmountArray, matchTypeArray);

}

//card orderer  for ID------------------------------------------------------------------
function orderCardsbyKey(orderArray, matchAmountArray, matchTypeArray){

    console.log("ordering cards");

    //order cards
    for (let y=0; y < orderArray.length; y++){
        for (let i=0; i < twigArray.length; i++){
            scientificName = twigArray[i].genus + " " + twigArray[i].species;

            if(orderArray[y] == scientificName){
                //console.log(' matches='+matchAmountArray[y] +' '+ orderArray[y]+' '+scientificName + y);
                cardTitle('p', "matches", "card-"+scientificName, "match"+y, "matches = "+matchAmountArray[y]); //adds match amount to card
                //cardTitle('p', "matchetype", "card-"+scientificName, "matchetype"+y, matchTypeArray[y]+" <br>"); //adds match Type to card
                document.getElementById("card-"+scientificName).style.order = y;    //set order
            }
        }
    }
}