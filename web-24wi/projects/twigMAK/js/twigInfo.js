function loadInfoPage(){ //load info page with twig

    window.location.href="info.html"; //load page on same page
    //window.open("info.html", '_blank').focus(); //load page on new page

   infoPage();

}

//Info page ------------------------------------------------------------------
function infoPage(){    

    let TwigNum =  localStorage.getItem("Num");

    console.log(TwigNum); 
    twig = twigArray[TwigNum];
    console.log(twig.CommonName);
    //console.log(twig); 

    scientificName = twig.genus + " " + twig.species;

    //leftHolder
    makeHolder("leftHolder", "infoPage");

    //add common name 
    makeElement(twig.CommonName, "CommonName", "leftHolder", "h1");

    //add scientific name 
    makeElement(scientificName, "scientificName", "leftHolder", "h1");

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
    makeElement(twig.bud, "budsDesctiption", "desctiptionHolder", "p");
    makeElement(twig.leafscars, "leafScarsDesctiption", "desctiptionHolder", "p");

    //keyCharacteristics
    makeHolder("keyCharacteristics", "leftHolder");
    makeElement(twig.family, "familyDesctiption", "leftHolder", "p");
    makeElement(twig.genus, "genusDesctiption", "leftHolder", "p");
    makeElement(twig.species, "speciesDesctiption", "leftHolder", "p");
    //makeElement(twig.arrangement, "arrangementDesctiption", "leftHolder", "p");

} 