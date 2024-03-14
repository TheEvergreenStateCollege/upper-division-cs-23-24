function MakeIDForm(){

    AddImage("twig", "twigMorphology", "https://crowspath.org/wp-content/uploads/labeledTwig-621x1024.jpg", "formHolder");
    
    //load in parts
    twigFormPart();
    BudFormPart();
    LeafScarFormPart();

    //makeHolder(id, parent);
    //makeElement(value, id, parent, type);
    //AddImage(className, id, image, parent);
    //imageCroper(imageid, Iwidth, Iheight, ILocation);
    //Qmaker(ID, parent, type, classType, List);


}

function twigFormPart(){
    //twig section---------------------------------------------------------

    makeHolder("TwigHolder", "formHolder", "formSection");
    makeElement("Twig", "TwigLabel", "TwigHolder", "h1");

    AddImage("twig", "twigAnatomy", "https://feralforaging.com/wp-content/uploads/2023/01/winter-tree-bud-identification-details-1024x576.jpg.webp", "TwigHolder");

    //arrangement
    QwithImage("arrangement alternate(buds arranged spirally on a stem) OR opposite arrangement(buds opposite eachother coming out of same node)","array", "TwigHolder", "twig", ["alternate", "opposite"], "https://naturallycuriouswithmaryholland.files.wordpress.com/2018/01/1-31-18-twigs-1.jpg?w=584");

    //twig color
    /*
    makeHolder("twigColorHolder", "TwigHolder");
    Qmaker("twigColor", "twigColorHolder", "checkbox", "twig", ["red", "orange", "yellow", "green", "purple", "brown", "gray", "white"]);
    */

    //twig shape
    QwithImage("twig shape: slender or stout","twigshape", "TwigHolder", "twig", ["slender", "stout"], "");
 

    //twig pubesent
    QwithImage("twig Pubescences(hairy) or glabrous(hairless)", "twigPubescences", "TwigHolder", "twig", ["pubescence", "glabrous"],"https://www.uwgb.edu/biodiversity-old/herbarium/trees/glabrous_pubescent02web_gf.jpg");

    //twig sheen
    QwithImage("twig Sheen","twigSheen", "TwigHolder", "twig", ["lustrous", "dull"], "");

    //twig others
    /*
    Qmaker("twigOther", "TwigHolder", "checkbox", "twig", ["keeled", "spur", "prickle"]);
    */

}

function BudFormPart(){
    //Bud section---------------------------------------------------------

    makeHolder("BudHolder", "formHolder", "formSection");
    makeElement("Bud", "BudLabel", "BudHolder", "h1");

    AddImage("Bud", "BudInfo", "https://www.uwgb.edu/biodiversity-old/herbarium/trees/buds01.jpg", "BudHolder");

    //bud color
    /*
    Qmaker("budColor", "BudHolder", "checkbox", "bud", ["red", "orange", "yellow", "green", "purple", "brown", "gray", "white"]);
    */
    //bud length 
    QwithImage("bud Length","budLength", "BudHolder", "bud",  ["1/8 inch", "1/4 inch", "3/8 inch", "1/2 inch", "3/4 inch"], "");

    //bud pubesent
    QwithImage("bud Pubescences(hairy) or glabrous(hairless)","budPubescences", "BudHolder", "bud", ["pubescence", "glabrous"], "https://feralforaging.com/wp-content/uploads/2023/01/hairy-and-smooth-tree-buds-1024x576.jpg.webp");

    //bud shape
    QwithImage("bud Shape: ovoid(egg shaped) or globose(spherical)","budShape", "BudHolder", "bud", ["ovoid", "globose"], "");

    //bud Type
    QwithImage("bud Types: stalked(bud grown on a stalk) or appressed(pressed to twig)","budType", "BudHolder", "bud", ["stalked", "appressed"], "");

}

function LeafScarFormPart(){
    //twig section---------------------------------------------------------
    makeHolder("LeafScarHolder", "formHolder", "formSection");
    makeElement("Leaf Scar", "LeafScarLabel", "LeafScarHolder", "h1");

    AddImage("LeafScar", "LeafScarInfo", "https://feralforaging.com/wp-content/uploads/2022/02/winter-tree-identificaiton-leaf-scar-bundle-scar.jpg.webp", "LeafScarHolder");

    //bundle scars
    QwithImage("bundle scars amount","bundlescars", "LeafScarHolder", "LeafScar", ["3", "5", "7", "many"], "");

    //Leaf Scar Shape
    QwithImage("Leaf Scar Shape","LeafScarShape", "LeafScarHolder", "LeafScar", ["triangular", "crescent", "shield", "V-shaped"], "");

    //LeafScar others
    /*
    Qmaker("LeafScarOther", "LeafScarHolder", "checkbox", "LeafScar", ["membrane", "corky", "shriveled", "stipule"]);
    */
}

function QwithImage(label, tmpName, parent, classType, List, imgAdress){

    mainbox = tmpName+"holder";
    leafBox = tmpName+"-Description";
    makeHolder(mainbox, parent, "box")
    makeHolder(leafBox+"-Holder", mainbox, "boxLeft");
    makeElement(label, leafBox,  leafBox+"-Holder", "h3");
    if(imgAdress !== ""){
        AddImage("FormImag", tmpName+"Img", imgAdress, leafBox+"-Holder");
    }
    Qmaker(tmpName, mainbox, "radio", classType, List);

}

function Qmaker(ID, parent, type, classType, List){
    MakeForm(ID, parent);
    for (let i=0; i < List.length; i++){
        formInput(type, ID, List[i], classType);
    }
}

function formInput(type, name, value, classType){

    ID = value+'-'+classType;

    holder = value+'-'+classType+'-holder';

    //make holder
    const tmpHolder = document.createElement("div"); //make div
    tmpHolder.setAttribute("Id", holder);          //add id
    tmpHolder.setAttribute("class", "holder");          //add class
    document.getElementById(name).appendChild(tmpHolder); //adds it to html

    //make input
    const makeInput = document.createElement("input"); //make input
    makeInput.setAttribute("type", type);          //add type
    makeInput.setAttribute("name", name);          //add type
    makeInput.setAttribute("value", value);        //value type
    makeInput.setAttribute("id", ID);  //set ID
    document.getElementById(holder).appendChild(makeInput); //adds it to html

    //make label
    const makeLable = document.createElement("label"); //make Lable
    makeLable.setAttribute("id", ID+"-label");          //set ID
    makeLable.setAttribute("class", "Q");          //set class
    makeLable.setAttribute("for", ID);          //set for
    document.getElementById(holder).appendChild(makeLable); //adds it to html
    document.getElementById(ID+"-label").innerHTML = value; //set to value

}

function MakeForm(ID, parent){
    const MakeForm = document.createElement("form"); //make Form
    MakeForm.setAttribute("id", ID);          //set ID
    MakeForm.setAttribute("class", "formClass");          //set class
    document.getElementById(parent).appendChild(MakeForm); //adds it to html
}

function imageCroper(imageid, Iwidth, Iheight, ILocation){   //crops web image
    document.getElementById(imageid).style.width = Iwidth;
    document.getElementById(imageid).style.height = Iheight;
    document.getElementById(imageid).style.objectFit = "none";
    document.getElementById(imageid).style.objectPosition = ILocation;
}

//-------------finding-----------------------------

function identify(){

   
    //add key tracker 
    for (let i=0; i < twigArray.length; i++){
        twigArray[i].key = 0;
    }

    //check arrangment 
    if(checkVale("arrangement") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].arrangement);
        }
        lookThrougTwigs(twigArray, tmpArray, "arrangement");
    }

    //twig----------------------------------------
    //check twigShape 
    if(checkVale("twigShape") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].twig);
        }

        lookThrougTwigs(twigArray, tmpArray, "twigShape");
    }

    //check twigPubescences 
    if(checkVale("twigPubescences") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].twig);
        }

        lookThrougTwigs(twigArray, tmpArray, "twigPubescences");
    }

    //check twigSheen 
    if(checkVale("twigSheen") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].twig);
        }

        lookThrougTwigs(twigArray, tmpArray, "twigSheen");
    }

    //bud----------------------------------------------

    //check budLength 
    if(checkVale("budLength") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].bud);
        }
        lookThrougTwigs(twigArray, tmpArray, "budLength");
    }

    //check budPubescences 
    if(checkVale("budPubescences") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].bud);
        }
        lookThrougTwigs(twigArray, tmpArray, "budPubescences");
    }

    //check budShape 
    if(checkVale("budShape") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].bud);
        }
        lookThrougTwigs(twigArray, tmpArray, "budShape");
    }

    //check budType 
    if(checkVale("budType") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].bud);
        }
        lookThrougTwigs(twigArray, tmpArray, "budType");
    }

    //leaf scar--------------------------------

    //check bundlescars 
    if(checkVale("bundlescars") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].leafscars);
        }
        lookThrougTwigs(twigArray, tmpArray, "bundlescars");
    }

    //check LeafScarShape 
    if(checkVale("LeafScarShape") !== undefined){
        tmpArray = [];
        for (let i=0; i < twigArray.length; i++){
            tmpArray.push(twigArray[i].leafscars);
        }
        lookThrougTwigs(twigArray, tmpArray, "LeafScarShape");
    }

    //sort
    console.log(twigArray);

    keyload(twigArray);
    
}

function lookThrougTwigs(Array, valueArray, name){
    checked = checkVale(name); //gets answer
    count = 0;
    for (let i=0; i < Array.length; i++){
        if (testFind(valueArray[i], checked) == true){
            count ++;
            twigArray[i].key = twigArray[i].key + 1; //incurment key
        }
    }

    console.log(count);
    return twigArray;
}

//gets Q asnser 
function checkVale(getting){
    var hold = document.getElementsByName(getting);
    for (i = 0; i < hold.length; i++) {
        if (hold[i].checked){
            console.log(hold[i].value);
            return hold[i].value;
        }
    }
}

//checks if a value is in matching
function testFind(text, value){
    text = text.toString();
    test = text.search(value);
    if (test > -1){
        return true;
    }
}

//gets twigs in key order
function keyload(array){ 

    twigArray.sort((a, b) => parseFloat(b.key) - parseFloat(a.key)); //sort highest key first

    tmpArrayhold = [];  //
    for (let i=0; i < array.length; i++){
        tmpArrayhold.push(array[i].genus + ' '+ array[i].species);
    }

    tmpArrayholdAmount = [];  //match amount
    for (let i=0; i < array.length; i++){
        tmpArrayholdAmount.push(twigArray[i].key);
    }

    console.log(tmpArrayholdAmount);
    console.log(tmpArrayhold);
    localStorage.setItem("key", tmpArrayhold);  //save twigs in order
    localStorage.setItem("match", tmpArrayholdAmount);  //save twig match amount
    localStorage.setItem("ordered", "yes"); //marked ordered 
    window.location.href="twigs.html"; //load page 
}