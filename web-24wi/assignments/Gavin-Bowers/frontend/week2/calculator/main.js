
const CalcData = {
    values: [], //This array stores the ints entered as ints and the operations as strings
    create: function () {
        const newCalcData = Object.create(this);
        return newCalcData;
    }
};

const cData = CalcData.create();

document.getElementById('calc-rows').addEventListener('click', function(event) {
    if (event.target && event.target.nodeName == 'BUTTON') {
        let value = event.target.id.slice(3);
        updateCalc(value);
        //console.log(cData.values);
    }
});

function updateCalc(value) {
    numericValue = parseInt(value);
    if (!Number.isNaN(numericValue)) { //If the value is a number
        addOperand(numericValue);
    } else {
        switch(value) {
            case 'add':
                addOperator(value);
                break;
            case 'subtract':
                addOperator(value);
                break;
            case 'multiply':
                addOperator(value);
                break;
            case 'divide':
                addOperator(value);
                break;
            case 'back':
                backSpace();
                break;
            case 'clear':
                clearCalc();
                break;
            case 'evaluate':
                evaluateCalc()
                break;
            default:
                console.log('Error: Invalid button ID for calculator');
        }
    }
}

function clearCalc() {
    cData.values = [];
    let outputText = document.getElementById('calc-output-text');
    outputText.textContent = '0';
}

function backSpace() {
    let latestValue = cData.values[cData.values.length-1];
    if (typeof latestValue == 'number') {
        let newValue = (latestValue / 10) >> 0; //Le epic bitwise integer division
        cData.values[cData.values.length-1] = newValue;
        let outputText = document.getElementById('calc-output-text');
        outputText.textContent = newValue;
    } else {
        cData.values.pop();
    }
}

function addOperand(value) { //Allows numbers to be added to subsequent decimal places
    let latestValue = cData.values[cData.values.length-1];
    let outputText = document.getElementById('calc-output-text');

    if (typeof latestValue == 'number') {
        let newValue = (latestValue * 10) + value;
        cData.values[cData.values.length-1] = newValue;
        outputText.textContent = newValue;
    } else {
        cData.values.push(value);
        outputText.textContent = value;
    }
}

function addOperator(value) { //Prevents invalid entering of Operators
    let latestValue = cData.values[cData.values.length-1];
    if (typeof latestValue == 'number') {
        cData.values.push(value);
    } else {
        //alert("You can't have two operators next to each other idiot!");
    }
}

function evaluateCalc() {
    let values = cData.values;
    let doEvaluate = false;
    if (values.length > 1) { //Do nothing if there is 0 or 1 values since there is nothing to evaluate
        let odd = values.length % 2 == 1;
        if (odd) { //Do nothing if there is a trailing operator
            doEvaluate = true;
        }
    }
    if (doEvaluate) {
        let currentValue = values[0];
        let operator = "";

        for (let i=1; i<values.length; i++) {
            let odd = (i+1) % 2 == 1 //the +1 is because this is counting from 1
            if (odd) { //Operands
                let newValue = performOperation(currentValue, values[i], operator);
                currentValue = newValue;
                operator = "";
            } else { //Operators 
                operator = values[i];
            }
        }
        cData.values = [currentValue];
        let outputText = document.getElementById('calc-output-text');
        outputText.textContent = currentValue;
    }
}

function performOperation(currentValue, operand, operator) {
    switch(operator) {
        case "add":
            return currentValue + operand;
        case "subtract":
            return currentValue - operand;
        case "multiply":
            return currentValue * operand;
        case "divide":
            return (currentValue / operand) >> 0; //Integer division
        default:
            console.log("Invalid Operator");
    }
}