let buffer = "0";
let runningTotal = 0;
let previousoperator = null;
const screen = document.querySelector('.screen');

function buttonClick(value) {
    console.log(value);
    if (isNaN(parseInt(value))) {
        handleSymbol(value);
    } else {
        handleNumber(value);
    }
    rerender();
}

function handleNumber(number) {
    if (buffer === "0") {
        buffer = number;
    } else {
        buffer += number;
    }
}

function handleMath(value) {
    if (buffer === "0") {
        return;
    }
    const intBuffer = parseInt(buffer);
    if (runningTotal === 0) {
        runningTotal = intBuffer;
    } else {
        flushOperation(intBuffer);
    }
    previousoperator = value;
    buffer = "0";
}

function flushOperation(intBuffer) {
    if (previousoperator === '+') {
        runningTotal += intBuffer;
    } else if (previousoperator === '-') {
        runningTotal -= intBuffer;
    } else if (previousoperator === 'x') {
        runningTotal *= intBuffer;
    } else {
        runningTotal /= intBuffer;
    }
}

function handleSymbol(symbol) {
    switch (symbol) {
        case "AC":
            buffer = "0";
            break;
        case '=':
            if (previousoperator === null) {
                return;
            }
            flushOperation(parseInt(buffer));
            previousoperator = null;
            buffer = String(runningTotal);
            runningTotal = 0;
            break;
        case "Del":
            if (buffer.length === 1) {
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

function init() {
    document.querySelector('.calc-buttons').addEventListener("click", function(event) {
        buttonClick(event.target.innerText);
    });
}

function rerender() {
    screen.innerText = buffer;
}

init();

