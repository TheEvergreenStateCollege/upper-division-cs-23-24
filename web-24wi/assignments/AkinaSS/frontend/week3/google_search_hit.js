document.body.onload = addElement;

const searchHit_1 = document.getElementsByClassName("hlcw0c");
const searchHit_2 = document.getElementsByClassName("g eejeod");
const searchHit_3 = document.getElementsByClassName("N54PNb BToiNc cvP2Ce");

const searchHits = [searchHit_1, searchHit_2, searchHit_3];

const body = document.getElementsByTagName("body")[0];
const newDiv = document.createElement("div");
  
  // Write a loop
  // index into searchHits array
  // create a new div
  // set the innerHTML to searchHits[i]

for (const element of searchHits) {
    newDiv.appendChild(element);
}

//test function
for (let i = 0; i < searchHits.length; i++) {
    console.log(i);
    //in case if it isn't working, release the statement below
    //newDiv.appendChild(i);
}

document.body.insertBefore(newDiv, body);