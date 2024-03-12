//controls info.html
//var TwigInfoVal= 0;



function loadInfoPage(){ //load info page with twig

    window.location.href="info.html"; //load page 

    //url = 'http://127.0.0.1:5500/info.html?name=' + encodeURIComponent(TwigInfoVal);
    //document.location.href = url;

   infoPage();

}


//Info page ------------------------------------------------------------------
function infoPage(){    

    let TwigNum =  localStorage.getItem("Num");

    console.log(TwigNum); 
    twig = twigArray[TwigNum];
    console.log(twig.CommonName);
    //console.log(twig); 

 
    //leftHolder
    makeHolder("leftHolder", "infoPage");

    //add common name 
    makeElement(twig.CommonName, "CommonName", "leftHolder", "h1");

    //add scientific name 
    makeElement(twig.scientificName, "scientificName", "leftHolder", "h1");

    //add images
    const imgHolder = document.createElement("div"); //make div
    imgHolder.setAttribute("Id", "imgHolder");          //add id
    document.getElementById("infoPage").appendChild(imgHolder); //adds it to html
    for (let i = 0; i < twig.image.length; i++) {
        AddImage("infoImg", "twigImg"+i, twig.image[i], "imgHolder");
    }

    //desctiption
    makeHolder("desctiptionHolder", "infoPage");
    makeElement(twig.twig, "twigDesctiption", "desctiptionHolder", "p");
    makeElement(twig.Buds, "budsDesctiption", "desctiptionHolder", "p");
    makeElement(twig.leafScars, "leafScarsDesctiption", "desctiptionHolder", "p");

    //keyCharacteristics
    makeHolder("keyCharacteristics", "leftHolder");

    twigKeyArray = KeepOnlyKey(twig); //clean value for key ideas 

    for (let i = 0; i < twigKeyArray.length; i++) {
        makeElement(twigKeyArray[i], "keyInfo-" + twigKeyArray[i], "keyCharacteristics", "p");
    }

} 

function KeepOnlyKey(tempTwig){     //used in infoPage  (find a better way to do this)
    delete tempTwig.scientificName;
    delete tempTwig.CommonName;
    delete tempTwig.image;
    delete tempTwig.herbariums;
    delete tempTwig.twig;
    delete tempTwig.Buds;
    delete tempTwig.leafScars;
    theString = JSON.stringify(tempTwig);
    theString = theString.replaceAll("{","");
    theString = theString.replaceAll("}","");
    theString = theString.replaceAll("[","");
    theString = theString.replaceAll('"',"");
    const myArray = theString.split("],");
    //console.log(myArray);
    return myArray;
}

function makeHolder(id, parent){    //used in infoPage
    const tmpHolder = document.createElement("div"); //make div
    tmpHolder.setAttribute("Id", id);          //add id
    document.getElementById(parent).appendChild(tmpHolder); //adds it to html
}

function makeElement(value, id, parent, type){  //used in infoPage
    const makeElement = document.createElement(type); //make type
    makeElement.setAttribute("Id", id);          //add id
    document.getElementById(parent).appendChild(makeElement); //adds it to html
    document.getElementById(id).innerHTML = value; //set to value
}
