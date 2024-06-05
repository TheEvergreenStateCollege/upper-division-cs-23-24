//has function used in mutable pages


function AddImage(className, id, image, parent){    //used in makeCards and infoPage
    const makeImage = document.createElement("img"); //make img
    makeImage.setAttribute("class", className);          //add class
    makeImage.setAttribute("Id", id);    //label
    makeImage.setAttribute("src", image);    //set img
    document.getElementById(parent).appendChild(makeImage); //adds it to html
}

function makeHolder(id, parent, classN=""){    //used in infoPage
    const tmpHolder = document.createElement("div"); //make div
    tmpHolder.setAttribute("Id", id);          //add id
    tmpHolder.setAttribute("class", classN);          //add class
    document.getElementById(parent).appendChild(tmpHolder); //adds it to html
}

function makeElement(value, id, parent, type){  //used in infoPage
    const makeElement = document.createElement(type); //make type
    makeElement.setAttribute("Id", id);          //add id
    document.getElementById(parent).appendChild(makeElement); //adds it to html
    document.getElementById(id).innerHTML = value; //set to value
}