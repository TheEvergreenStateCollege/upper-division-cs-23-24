let buffer = "0";
let runningTotal = 0;
let previousOperator =null;
const screen = document.querySelector('.screen');

function buttonClick(value){
    console.log(value);
  //NaN is not a number
    if (isNaN(parseInt(value))) {
    handleSymbol(value);
    } else {
        handleNumber(value);
    }
    rerender();
}

function handleNumber(number){
    if (buffer === "0"){
        buffer = number;
    } else {
        buffer == buffer + number; 
    }
    
}

function handleMath(value){
    if(buffer === "0"){
        //do nothing
        return;
    }

    const intBuffer = parseInt(buffer);
    if (runningTotal === 0){
        runningTotal = intBuffer;
    } else {
        flushOperation(intBuffer);
    }
    previousOperator = value;
    buffer = "0";
}


function flushOperation(intBuffer){
    if (previousOperator === '+'){
        runningTotal = runningTotal + intBuffer;
    } else if (previousOperator === '-'){
        runningTotal = runningTotal - intBuffer;
    } else if (previousOperator === 'x'){
        runningTotal = runningTotal * intBuffer;
    } else {
        runningTotal = runningTotal / intBuffer;
    }
}


function handleSymbol(symbol){
    //if (symbol === 'AC')
    
    switch (symbol){
        case 'AC':
            buffer = '0';
            break;
        case '=':
            if(previousOperator === null){
            return;
            }
            flushOperation(parseInt(buffer));
            previousOperator = null;
            buffer = "" + runningTotal;
            runningTotal = 0;
            break;
        case 'Del':
            if(buffer.length === 1){
                buffer = '0';
            } else {
              buffer = buffer.substring(0, buffer.length - 1);  
            }
            break;
        case '+':
        case '-':
        case '/':
        case 'x':
            handleMath(symbol);
            break;


    }
}


function init(){
    document.querySelector('.calc-buttons')
    .addEventListener("click", function(event){
        buttonClick(event.target.innerText);
    })
}
function rerender(){
    screen.innerText = buffer;
}

document.getElementsByClassName("calc-button");
const buttons = document.getElementsByClassName("calc-button");
buttonList = Array.prototype.slice.call(buttons);
buttonList.forEach((button) => button.addEventListener("click", (event) => buttonClick(event.target.textContent)));


