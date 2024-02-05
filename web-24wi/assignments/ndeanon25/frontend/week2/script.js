// This is the running total of the calculation
let runningTotal = 0;

// This is the input from the user
let buffer = '0';

//This is the previous operator
let prevOperator;

//Assigns const screen to the .screen from the calculator
const screen = document.querySelector(".screen");

function buttonClick(value){
  if(isNaN(parseInt(value))) {
    handleSymbol(value);
  } else{
    handleNumber(value);
  }
  rerender();
}

function handleMath(value){
    if(buffer === '0') {
        return;
    }
    const intBuffer = parseInt(buffer);
    if (runningTotal === 0){
        runningTotal = intBuffer;
    } else{
        flushOperation(intBuffer);
    }

    prevOperator = value;
    buffer = '0';
}

function flushOperation(intBuffer){
    if(prevOperator === '+'){
        runningTotal += intBuffer;
    } else if(prevOperator === '-'){
        runningTotal -= intBuffer;
    }else if(prevOperator === 'X'){
        runningTotal *= intBuffer;
    }else if(prevOperator === '÷'){
        runningTotal /= intBuffer;
    }
}

function handleNumber(number){
  if(buffer === '0'){
    buffer = number;
  } else{
    buffer += number;
  }
}

function handleSymbol(symbol){
  switch(symbol){
    case 'C':
      buffer = '0';
      break;
    case '=':
      if(prevOperator === null){
        return;
      }
      flushOperation(parseInt(buffer));

      prevOperator = null;
      buffer = "" + runningTotal;
      runningTotal = 0;
      break;
    case '←':
      if(buffer.length === 1){
        buffer = '0';
      } else {
        buffer = buffer.substring(0, buffer.length-1);
      }
      break;
    case '+': 
    case '-': 
    case 'X':
    case '÷':
        handleMath(symbol);
        break;
      
  }
}

function init() {
  document
    .querySelector('.cal-buttons')
    .addEventListener("click", function(event) {
    buttonClick(event.target.innerText);
  });
}

function rerender() {
  screen.innerText = buffer;
}


init();