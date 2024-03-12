//controls twigs.html

//card Maker ------------------------------------------------------------------
function makeCards(){
    //get location-------
    const body = document.getElementsByTagName("body")[0];
    const cardAmount = twigArray.length;

    //make cards-----------------------------------------------
    for (let i=0; i < cardAmount; i++){
 
        //make card
        const twigCard = document.createElement("div"); //make div
        twigCard.setAttribute("class", "card");          //add class
        twigCard.setAttribute("Id", "card-" + twigArray[i].scientificName);  //label each box
        twigCard.setAttribute("onclick", "load("+i+")");    //add clickable load info
        document.getElementById("cardHolder").appendChild(twigCard); //adds it to html
      
        //add common name 
        cardTitle("h1", "CommonName", "card-" + twigArray[i].scientificName, "LabelCommon-" + twigArray[i].CommonName, twigArray[i].CommonName);

        //add scientific name 
        cardTitle("h2", "ScientificName", "card-" + twigArray[i].scientificName, "LabelScientific-" + twigArray[i].scientificName, twigArray[i].scientificName);

        //add image
        AddImage("twigImg", "twigImg-" + twigArray[i].scientificName, twigArray[i].image[0], "card-" + twigArray[i].scientificName);
    }
}

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

function AddImage(className, id, image, parent){    //used in makeCards and infoPage
    const makeImage = document.createElement("img"); //make img
    makeImage.setAttribute("class", className);          //add class
    makeImage.setAttribute("Id", id);    //label
    makeImage.setAttribute("src", image);    //set img
    document.getElementById(parent).appendChild(makeImage); //adds it to html

}
