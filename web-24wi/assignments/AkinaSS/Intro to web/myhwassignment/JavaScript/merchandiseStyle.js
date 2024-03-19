//Declare variables
const openLI = "<li>";
const closeLI = "</li>";
const imgname = "product";  //filename
const imgext = ".jpg";  //file extension
const imgID = "' id='info"; //id
let img;  //assemble full image file name
const startIMG = "<img src='images/merchandise/"; //first part of image tag
const endIMG = "'>";  //second part of image tag
let imgTag; //assemble image tag
let listTag;  //assemble image tag within list
let listTags = [];  //empty array
let addCart = "Add to Cart";  //for product info box
let dismiss = "Close";  //for product info box

//Loop to create each image by assembling the parts together
for(let i=0; i<9; i++) {
    img = imgname + (i+1) + imgext; //example: product1.jpg
    imgTag = startIMG + img + imgID + (i+1) + endIMG; //example: <img src='images/merchandise/product1.jpg' id="info1">
    listTag = openLI + imgTag + closeLI;  //example: <li><img src='images/merchandise/product1.jpg'></li>
    //store each markup in an array
    listTags.push(listTag);
}

//display images
//join() adds all the elements of an array into a string, separated by the specified separator string.
//in this case, it removes comma between item
document.getElementById("products").innerHTML = listTags.join(" ");

/* Product Info Box */

// Product details
const prodNum = ['P-4160', 'P-9129', 'P-5963', 'P-2982', 'P-9170', 'P-8739', 'P-9487', 'P-7494', 'P-5551']; // Serial number
const prodPrice = [77, 50, 71, 65, 51, 46, 73, 48, 88]; // Price tag

//Defining functions call by user click on the product
const moreDetails = function(event) {

    //Find index number from id name
    let idname = event.target.id;
    let idnum = idname[4]-1;

    //Info box content
    document.getElementById("info_box").style.visibility = "visible";
    document.getElementById("info_num").innerHTML = "Item No. " + prodNum[idnum];
    document.getElementById("info_price").innerHTML = "Price $" + prodPrice[idnum];
    document.getElementById("addBtn").innerHTML = addCart;
    document.getElementById("closeInfo").innerHTML = dismiss;

    //Info box location
    switch(idnum) {
        case 0:
        document.getElementById("info_box").style.top = "20%";
        document.getElementById("info_box").style.left = "9%";
        break;
        case 1:
        document.getElementById("info_box").style.top = "20%";
        document.getElementById("info_box").style.left = "41%";
        break;
        case 2:
        document.getElementById("info_box").style.top = "20%";
        document.getElementById("info_box").style.left = "73%";
        break;
        case 3:
        document.getElementById("info_box").style.top = "50%";
        document.getElementById("info_box").style.left = "9%";
        break;
        case 4:
        document.getElementById("info_box").style.top = "50%";
        document.getElementById("info_box").style.left = "41%";
        break;
        case 5:
        document.getElementById("info_box").style.top = "50%";
        document.getElementById("info_box").style.left = "73%";
        break;
        case 6:
        document.getElementById("info_box").style.top = "80%";
        document.getElementById("info_box").style.left = "9%";
        break;
        case 7:
        document.getElementById("info_box").style.top = "80%";
        document.getElementById("info_box").style.left = "41%";
        break;
        case 8:
        document.getElementById("info_box").style.top = "80%";
        document.getElementById("info_box").style.left = "73%";
        break;
    }
}

//Gather all product images and store them in a variable
const prodImgs = document.getElementsByTagName("img");

//Listen for image click
for (let prodImg of prodImgs) {
    prodImg.addEventListener("click", moreDetails);
}

//Create close info box link
let closeinfo = document.getElementById("closeInfo");
closeinfo.addEventListener("click", hideInfo);

//Close info box
function hideInfo() {
    document.getElementById("info_box").style.visibility = "hidden";
}