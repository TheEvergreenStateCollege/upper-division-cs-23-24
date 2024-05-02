let holdNum = 0;
let storedNum = null;
let lastType = "";

function num(a){    //add num to display and holdNum
  holdNum = (holdNum * 10) + a;
  document.getElementById("display").innerHTML = holdNum;
  console.log(holdNum, storedNum); //testing
}

function back(){
  holdNum = Math.floor(holdNum/10);
  document.getElementById("display").innerHTML = holdNum;
  console.log(holdNum, storedNum); //testing
}

function clearNums(){     //clears all numbers
  holdNum = 0;
  storedNum = null;
  document.getElementById("display").innerHTML = holdNum;
  console.log(holdNum, storedNum); //testing
}

function MathNum(type){
  lastType = type;
  
  if(storedNum == null){
    storedNum = holdNum;
    holdNum = 0;
  }
  
  else if(type === "add"){
    console.log(holdNum, '+', storedNum); //testing
    storedNum = storedNum + holdNum;
  }
  
  else if(type === "sub"){
    console.log(holdNum, '- ', storedNum); //testing
    storedNum = storedNum - holdNum;
  }
  
  else if(type === "mut"){
    console.log(holdNum, '* ', storedNum); //testing
    storedNum = storedNum * holdNum;
  }
  
  else if(type === "div"){
    console.log(holdNum, '/ ', storedNum); //testing
    storedNum = storedNum / holdNum;
  }
  
  holdNum = 0;
  document.getElementById("display").innerHTML = holdNum;
  console.log(holdNum, storedNum); //testing
}
  
 function solve(){
   if(holdNum != 0){  
      MathNum(lastType);
    }
    document.getElementById("display").innerHTML = storedNum;
   console.log("="); //testing
 }