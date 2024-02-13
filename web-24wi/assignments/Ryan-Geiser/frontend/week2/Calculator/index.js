// Calculator Program

const DISPLAY = document.getElementById("display");

function appendToDisplay(input) {
    DISPLAY.value += input;
}

function clearDisplay() {
    DISPLAY.value = "";
}

function calculate() {
    try {
        DISPLAY.value = eval(DISPLAY.value);
    }
    catch(error) {
        DISPLAY.value = "Error";
    }
}