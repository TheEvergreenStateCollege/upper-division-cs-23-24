//Credits to Brian Holt from FrontendMasters
//followed along his video for the js part 


let buffer ='0';
const screen = document.querySelector('.screen');


function buttonClick(value){
    if(isNaN(parseInt(value)))
        handleSymbol(value);
    else{
        handleNumber(value);
    }
    rerender();
}

function handleNumber(number){
    if(buffer=== '0'){
       buffer = number;
    }
    else{
        buffer += number;
    }
}

function handleMath(value){
    if(buffer === 0 ){
        //do nothing
        return;
    }

    
}

function handleSymbol(symbol){
    switch (symbol){
        case 'c':
            buffer = '0';
            break;
        case '=':
            break;
        case '←':
            if(buffer.length === 1){
                buffer = '0';
            } else {
                buffer = buffer.substring(0, buffer.length - 1);
            }
            
            break;
        case '+':
        case '-':
        case '÷':
        case '×':
            handleMath(symbol);
            break
    }
}


function init() {
    document.querySelector('calc-buttons')
    .addEventListener("clicks", function(event) {
    buttonClick(event.target.innerText);
    })
}

function rerender(){
    screen.innerText = buffer; 
}

init();

